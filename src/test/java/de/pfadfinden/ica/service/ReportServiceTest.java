package de.pfadfinden.ica.service;

import de.pfadfinden.ica.IcaConnector;
import de.pfadfinden.ica.IcaServer;
import org.junit.jupiter.api.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ReportServiceTest {

    private IcaConnector icaConnector;
    private ReportService reportService;
    private Properties properties = new Properties();

    @BeforeEach
    public void setUp() throws Exception {
        InputStream is = ClassLoader.getSystemResourceAsStream("unittest.properties");
        properties.load(is);

        this.icaConnector = new IcaConnector(IcaServer.BDP_QA,
                properties.getProperty("icausername"), properties.getProperty("icapassword"));
        this.reportService = new ReportService(icaConnector);
    }

    @AfterEach
    public void tearDown() throws Exception {
        this.icaConnector.close();
    }

    @Test
    public void getReport() throws Exception {
        HashMap<String, Object> reportParams = new HashMap<>();
        reportParams.put("A_Mitgliedsnummer", 11111);
        reportParams.put("X_RequestId", "12345");
        reportService.getReport(105, 1, reportParams);
    }
/*
    @Test(expected=IcaException.class)
    public void getReportParamMissing() throws Exception {
        HashMap<String, Object> reportParams = new HashMap<>();
        reportParams.put("X_RequestId", "12345");
        reportService.getReport(105, 1, reportParams);
    }
*/

/*
    @Test(expected=IcaException.class)
    public void getReportOneWrongId() throws Exception {
        HashMap<String, Object> reportParams = new HashMap<>();
        reportParams.put("X_RequestId", "12345");
        reportService.getReport(999, 1, reportParams);
    }
*/

}