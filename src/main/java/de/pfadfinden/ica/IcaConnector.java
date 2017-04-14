package de.pfadfinden.ica;

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

import de.pfadfinden.ica.service.GruppierungService;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class IcaConnector implements Closeable {

    private CloseableHttpClient closeableHttpClient;
    private Gson gson;

    private final Logger logger = LoggerFactory.getLogger(GruppierungService.class);

    private boolean isAuthenticated = false;

    private IcaServer icaServer;

    public IcaConnector(IcaServer icaServer, String username, String password) throws
            IcaAuthenticationException {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateConverter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeConverter())
                .create();

        this.icaServer = icaServer;

        this.closeableHttpClient = HttpClients.custom()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        try {
            authenticate(username, password);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void authenticate(String username, String password) throws URISyntaxException, IOException,
            IcaAuthenticationException {
        if (isAuthenticated) return;

        URI uri = IcaURIBuilder.getLoginURIBuilder(this.icaServer).build();

        HttpPost httpPost = new HttpPost(uri);
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("username", username));
        nvps.add(new BasicNameValuePair("password", password));
        nvps.add(new BasicNameValuePair("Login", "API"));
        nvps.add(new BasicNameValuePair("redirectTo", "./pages/loggedin.jsp"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));

        try (
                CloseableHttpResponse response = closeableHttpClient.execute(httpPost)
        ) {
            HttpEntity responseEntity = response.getEntity();
            String resultData = EntityUtils.toString(responseEntity);

            Type type = new TypeToken<IcaApiResponse<Object>>() {
            }.getType();
            IcaApiResponse<Object> resp = gson.fromJson(resultData, type);

            if (response.getStatusLine().getStatusCode() == 200) {
                isAuthenticated = true;
                logger.debug("Security: Authenticated to ICA using API token: " + resp.getApiSessionToken());
            } else {
                logger.warn("Security: Authentication on ICA failed.");
                throw new IcaAuthenticationException("Authentication on ICA failed.");
            }
        }
    }

    public void close() throws IOException {
        URI uri = IcaURIBuilder.getLogoutURIBuilder(this.icaServer).build();
        HttpGet httpGet = new HttpGet(uri);
        closeableHttpClient.execute(httpGet).close();
        isAuthenticated = false;
        closeableHttpClient.close();
        logger.debug("Security: Session terminated and client closed.");
    }

    public <T> T executeApiRequest(HttpUriRequest request, Type resultType) throws IcaApiException {
        try (
                CloseableHttpResponse response = closeableHttpClient.execute(request)
        ) {
            HttpEntity responseEntity = response.getEntity();

            Reader respReader = new InputStreamReader(responseEntity.getContent());

            Type typeWithResponse = IcaResponse.getType(resultType);
            Type typeWithApiResponse = IcaApiResponse.getType(typeWithResponse);

            IcaApiResponse<IcaResponse<T>> result = gson.fromJson(respReader, typeWithApiResponse);

            if (result == null || result.getStatusCode() != 0) {
                logger.error("Ica API returns error: RequestURI {} RequestType {} API ResultStatusCode {} " +
                                "API Reponse isSuccess {}",
                        request.getURI(),
                        request.getMethod(),
                        result.getStatusCode()
                );
                throw new IcaApiException(result);
            }

            return result.getResponse().getData();
        } catch (Exception e) {
            throw new IcaApiException(e);
        }
    }

    public CloseableHttpClient getCloseableHttpClient() {
        return this.closeableHttpClient;
    }

    public String toJson(Object o) {
        return gson.toJson(o);
    }

    public IcaURIBuilder getURIBuilder(String path) {
        return getURIBuilder(path, true);
    }

    public IcaURIBuilder getURIBuilder(String path, boolean restUrl) {
        return new IcaURIBuilder(icaServer, path, restUrl);
    }
}
