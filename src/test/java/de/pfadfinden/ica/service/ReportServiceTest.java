package de.pfadfinden.ica.service;

import de.pfadfinden.ica.IcaConnector;
import de.pfadfinden.ica.IcaServer;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ReportServiceTest {

    private IcaConnector icaConnector;
    private ReportService reportService;
    private Properties properties = new Properties();

    @Before
    public void setUp() throws Exception {
        InputStream is = ClassLoader.getSystemResourceAsStream("unittest.properties");
        properties.load(is);

        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
                properties.getProperty("icausername"), properties.getProperty("icapassword"));
        this.icaConnector = new IcaConnector(IcaServer.BDP_QA, credentials);
        this.reportService = new ReportService(icaConnector);
    }

    @After
    public void tearDown() throws Exception {
        this.icaConnector.close();
    }

    @Test
    public void getReport() throws Exception {
        HashMap<String, Object> reportParams = new HashMap<>();
        reportParams.put("A_Mitgliedsnummer", 11111);
        reportService.getReport(105, 1, reportParams);
    }

}