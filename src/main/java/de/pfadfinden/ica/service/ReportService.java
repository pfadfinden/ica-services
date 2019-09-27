package de.pfadfinden.ica.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.pfadfinden.ica.IcaConnection;
import de.pfadfinden.ica.execption.IcaApiException;
import de.pfadfinden.ica.execption.IcaException;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Services zur Ausfuehrung von Standardreports der Mitgliederverwaltung
 * in den Zielformaten PDF und XLS.
 *
 * @see <a href="https://meinbdp.de/x/HgfoAw">MeinBdP: Auswertungen/Reports ausführen</a>
 * @see <a href="https://meinbdp.de/x/IAfoAw">MeinBdP: Verfügbare Reports</a>
 */
public class ReportService {

    static private final Gson gson = new Gson();
    private IcaConnection icaConnection;
    private final Logger logger = LoggerFactory.getLogger(ReportService.class);

    private static final String URL_PDFREPORT = "nami/grp-reports/filtered-for-grpadmin/run-all";
    private static final String URL_ONETIMTEDOWNLOAD = "OneTimeDownload/otd";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public ReportService(IcaConnection icaConnection) {
        Objects.requireNonNull(icaConnection);
        this.icaConnection = icaConnection;
    }

    /**
     * Fuehre Report aus und erhalte XLS oder PDF Dokument als Byte-Array.
     *
     * @param  gruppierungId ID der Gruppierung zu der Report ausgefuehrt wird
     * @param  reportId ID des Reports der ausgefuehrt werden soll
     * @param  reportParams Beliebiges Objekt das serialisiert und als Reportparameter ubergeben werden soll
     * @return Root Gruppierung des authentifzierten Benutzers
     * @throws IcaApiException bei Kommunikationsfehler mit API
     */
    public byte[] getReport(int reportId, int gruppierungId, Object reportParams) throws IcaException {

        // Request report first
        this.requestReport(reportId,gruppierungId,reportParams);

        // Download requested report
        return this.downloadReport();
    }

    private void requestReport(int reportId, int gruppierungId, Object reportParams) throws IcaApiException {

        HttpUrl httpUrl = icaConnection.getUrlBuilder()
                .addPathSegments(URL_PDFREPORT)
                .addQueryParameter("id",Integer.toString(reportId))
                .addQueryParameter("crtGruppierung",Integer.toString(gruppierungId))
                .build();
        logger.debug("Request ICA report generation URI: {}", httpUrl);

        RequestBody body = RequestBody.create(JSON, gson.toJson(reportParams));

        Request request = new Request.Builder()
                .url(httpUrl)
                .post(body)
                .addHeader("Accept","application/json")
                .build();

        Type type = new TypeToken<String>() {}.getType();
        icaConnection.executeApiRequest(request, type);
    }

    private byte[] downloadReport() throws IcaApiException {

        HttpUrl downloadUrl = icaConnection.getUrlBuilder(false)
                .addPathSegments(URL_ONETIMTEDOWNLOAD)
                .build();
        logger.debug("Request ICA report download URI: {}", downloadUrl);

        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();

        try (Response response = icaConnection.getHttpClient().newCall(request).execute()) {
            if(response.body() == null) throw new IcaException();
            byte[] resultDoc = response.body().bytes();
            if (resultDoc.length < 100) {
                logger.error("Ica returns report of {} bytes length. This seems to be too small.", resultDoc.length);
                throw new IcaException();
            }
            return resultDoc;
        } catch (IOException | IcaException e) {
            throw new IcaApiException(e);
        }
    }
}
