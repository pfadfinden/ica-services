package de.pfadfinden.ica.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.pfadfinden.ica.IcaConnector;
import de.pfadfinden.ica.IcaURIBuilder;
import de.pfadfinden.ica.execption.IcaException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;

public class ReportService {

    private IcaConnector icaConnector;
    private static Log log = LogFactory.getLog(ReportService.class);

    public ReportService(IcaConnector icaConnector) {
        this.icaConnector = icaConnector;
    }

    public byte[] getReport(int reportId, int gruppierungId, Object reportParams) throws IOException, IcaException {

        IcaURIBuilder builder = icaConnector.getURIBuilder(IcaURIBuilder.URL_PDFREPORT);
        builder.addParameter("id", Integer.toString(reportId));
        builder.addParameter("crtGruppierung", Integer.toString(gruppierungId));

        Gson gson = new Gson();

        StringEntity postEntity = new StringEntity(gson.toJson(reportParams), ContentType.APPLICATION_JSON);
        HttpPost httpPost = new HttpPost(builder.build());
        httpPost.setEntity(postEntity);
        httpPost.addHeader("Accept", "application/json");

        log.info("Prepare Report URI: " + httpPost.getURI());
        log.info("Request body: " + EntityUtils.toString(postEntity));

        Type type = new TypeToken<String>() {
        }.getType();
        icaConnector.executeApiRequest(httpPost, type);

        URI downloadUri = icaConnector.getURIBuilder(IcaURIBuilder.URL_ONETIMTEDOWNLOAD, false).build();
        log.info("Request Report URI: " + downloadUri);

        HttpGet httpget = new HttpGet(downloadUri);
        try (
                CloseableHttpResponse response = icaConnector.getCloseableHttpClient().execute(httpget)
        ) {
            HttpEntity entity = response.getEntity();
            byte[] report = EntityUtils.toByteArray(entity);
            return report;
        }
    }
}
