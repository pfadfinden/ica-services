package de.pfadfinden.ica.service;

import de.pfadfinden.ica.IcaConnector;
import de.pfadfinden.ica.IcaServer;
import de.pfadfinden.ica.model.IcaGruppierung;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import static org.junit.Assert.*;

public class GruppierungServiceTest {

    private IcaConnector icaConnector;
    private GruppierungService gruppierungService;
    private Properties properties = new Properties();

    @Before
    public void setUp() throws Exception {
        InputStream is = ClassLoader.getSystemResourceAsStream("unittest.properties");
        properties.load(is);

        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
                properties.getProperty("icausername"), properties.getProperty("icapassword"));
        this.icaConnector = new IcaConnector(IcaServer.BDP_QA,credentials);
        this.gruppierungService = new GruppierungService(icaConnector);
    }

    @Test
    public void getChildGruppierungen() throws Exception {
        Collection<IcaGruppierung> icaGruppierung = gruppierungService.getChildGruppierungen(1);
        assertEquals(icaGruppierung.size(),14);
    }

    @Test
    public void getRootGruppierung() throws Exception {
        IcaGruppierung icaGruppierung = gruppierungService.getRootGruppierung();
        assertNotNull(icaGruppierung);
    }
}