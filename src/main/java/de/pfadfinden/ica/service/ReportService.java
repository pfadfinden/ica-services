package de.pfadfinden.ica.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.pfadfinden.ica.IcaConnector;
import de.pfadfinden.ica.IcaURIBuilder;
import de.pfadfinden.ica.execption.IcaException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.net.URI;

public class ReportService {

    static private final Gson gson = new Gson();
    private IcaConnector icaConnector;
    private final Logger logger = LoggerFactory.getLogger(ReportService.class);

    public ReportService(IcaConnector icaConnector) {
        this.icaConnector = icaConnector;
    }

    public byte[] getReport(int reportId, int gruppierungId, Object reportParams) throws IcaException {

        IcaURIBuilder builder = icaConnector.getURIBuilder(IcaURIBuilder.URL_PDFREPORT);
        builder.addParameter("id", Integer.toString(reportId));
        builder.addParameter("crtGruppierung", Integer.toString(gruppierungId));

        StringEntity postEntity = new StringEntity(gson.toJson(reportParams), ContentType.APPLICATION_JSON);
        HttpPost httpPost = new HttpPost(builder.build());
        httpPost.setEntity(postEntity);
        httpPost.addHeader("Accept", "application/json");

        logger.debug("Request ICA report generation URI: {}", httpPost.getURI());

        Type type = new TypeToken<String>() {}.getType();
        icaConnector.executeApiRequest(httpPost, type);

        URI downloadUri = icaConnector.getURIBuilder(IcaURIBuilder.URL_ONETIMTEDOWNLOAD, false).build();
        logger.debug("Request ICA report download URI: {}", downloadUri);

        HttpGet httpget = new HttpGet(downloadUri);
        try (
                CloseableHttpResponse response = icaConnector.getCloseableHttpClient().execute(httpget)
        ) {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toByteArray(entity);
        } catch (Exception e) {
            throw new IcaException(e);
        }
    }
}
