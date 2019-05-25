package de.pfadfinden.ica;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.pfadfinden.ica.converter.LocalDateConverter;
import de.pfadfinden.ica.converter.LocalDateTimeConverter;
import de.pfadfinden.ica.converter.LocalTimeConverter;
import de.pfadfinden.ica.execption.IcaApiException;
import de.pfadfinden.ica.execption.IcaAuthenticationException;
import de.pfadfinden.ica.model.IcaApiResponse;
import de.pfadfinden.ica.model.IcaResponse;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Objects;

import static com.google.common.base.Strings.emptyToNull;

public class IcaConnection implements Closeable {

    private OkHttpClient okHttpClient;

    private Gson gson;

    private final Logger logger = LoggerFactory.getLogger(IcaConnection.class);
    private final RateLimiter apiRateLimiter = RateLimiter.create(3.0); // rate is "3 permits per second"

    private CookieJar cookieJar = new MemoryCookieJar();

    private IcaServer icaServer;

    private static final String URL_STARTUP = "rest/nami/auth/manual/sessionStartup";

    public IcaConnection(IcaServer icaServer, String username, String password) throws IcaAuthenticationException {

        Objects.requireNonNull(icaServer,"IcaServer is null");
        Objects.requireNonNull(emptyToNull(username),"username null or empty");
        Objects.requireNonNull(emptyToNull(password),"password null or empty");

        this.init(icaServer);

        try {
            authenticate(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IcaConnection(IcaServer icaServer, String session) {

        Objects.requireNonNull(icaServer);
        Objects.requireNonNull(session);

        this.init(icaServer);

        Cookie cookie = new Cookie.Builder()
                .domain(icaServer.getHost())
                .path("/ica")
                .name("JSESSIONID")
                .value(session)
                .secure()
                .build();

        // ToDo: httpURL!
        this.cookieJar.saveFromResponse(null, Collections.singletonList(cookie));
    }

    private void init(IcaServer icaServer){
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateConverter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeConverter())
                .create();

        this.icaServer = icaServer;

        this.okHttpClient = new OkHttpClient.Builder()
                .cookieJar(this.cookieJar)
                .build();
    }

    private void authenticate(String username, String password) throws IOException, IcaAuthenticationException {
        this.apiRateLimiter.acquire(); // may wait

        HttpUrl httpUrl = this.getUrlBuilder(false)
                .addPathSegments(URL_STARTUP)
                .build();

        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("Login", "API")
                .add("redirectTo",  "./pages/loggedin.jsp")
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .post(formBody)
                .build();

        logger.debug("Authenticating on ICA via URI '{}'.",httpUrl);

        try (
                Response response = okHttpClient.newCall(request).execute()
        ) {
            if (response.code() == 200 && response.body() != null) {
                String resultData = response.body().string();
                IcaApiResponse<Object> resp = gson.fromJson(resultData, new TypeToken<IcaApiResponse<Object>>() {}.getType());
                logger.debug("Security: Authenticated to ICA using API token: {}.",resp.getApiSessionToken());
                if(resp.getStatusCode() != 0) throw new IcaAuthenticationException("Authentication on ICA failed.");
            } else {
                logger.warn("Security: Authentication on ICA failed with response code {}.",response.code());
                throw new IcaAuthenticationException("Authentication on ICA failed.");
            }
        }
    }

    public <T> T executeApiRequest(HttpUrl httpUrl, Type resultType) throws IcaApiException {

        Objects.requireNonNull(httpUrl);
        Objects.requireNonNull(resultType);

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        return this.executeApiRequest(request,resultType);
    }

    public <T> T executeApiRequest(Request request, Type resultType) throws IcaApiException {

        Objects.requireNonNull(request);
        Objects.requireNonNull(resultType);

        this.apiRateLimiter.acquire(); // may wait

        logger.info("Executing ICA API request. URI: '{}' Method: '{}' ResultType: '{}'",
                request.url(),
                request.method(),
                resultType.getTypeName()
        );

        try (
                Response response = okHttpClient.newCall(request).execute()
        ) {
            if(response.code() != 200){
                logger.error("ICA API returns error: RequestURI {} RequestType {} API ResultStatusCode {}",
                        request.url(),
                        request.method(),
                        response.code()
                );
            }

            Type typeWithResponse = IcaResponse.getType(resultType);
            Type typeWithApiResponse = IcaApiResponse.getType(typeWithResponse);

            Objects.requireNonNull(response.body(),"ICA API returned empty response body.");
            String resultData = response.body().string();

            logger.debug("ICA API Response code '{}' with body: {}",response.code(),resultData);
            IcaApiResponse<IcaResponse<T>> result = gson.fromJson(resultData, typeWithApiResponse);

            if (result == null || result.getResponse() == null || !result.getResponse().getSuccess() || result.getStatusCode() != 0) {
                logger.error("Ica API returns error: RequestURI {} RequestType {} API ResultStatusCode {} Body: '{}'",
                        request.url(),
                        request.method(),
                        response.code(),
                        resultData
                );
                throw new IcaApiException(result);
            }

            return result.getResponse().getData();
        } catch (IOException e) {
            throw new IcaApiException(e);
        }
    }

    public HttpUrl.Builder getUrlBuilder(){
        return this.getUrlBuilder(true);
    }

    public HttpUrl.Builder getUrlBuilder(Boolean apiEndpoint){
        HttpUrl.Builder builder = new HttpUrl.Builder()
                .host(icaServer.getHost())
                .addPathSegment(icaServer.getDeployment())
                .scheme("https");
        if(apiEndpoint) builder.addPathSegments("rest/api/1/2/service");
        return builder;
    }

    public OkHttpClient getCloseableHttpClient() {
        return this.okHttpClient;
    }

    public String toJson(Object o) {
        return gson.toJson(o);
    }

    @Override
    public void close() {

    }
}
