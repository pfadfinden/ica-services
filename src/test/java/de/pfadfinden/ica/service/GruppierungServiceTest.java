package de.pfadfinden.ica.service;

import de.pfadfinden.ica.IcaConnector;
import de.pfadfinden.ica.IcaServer;
import de.pfadfinden.ica.model.IcaGruppierung;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class GruppierungServiceTest {

    private IcaConnector icaConnector;
    private GruppierungService gruppierungService;
    private Properties properties = new Properties();

    private final Logger logger = LoggerFactory.getLogger(GruppierungService.class);

    @BeforeEach
    public void setUp() throws Exception {
        InputStream is = ClassLoader.getSystemResourceAsStream("unittest.properties");
        properties.load(is);

        this.icaConnector = new IcaConnector(IcaServer.BDP_QA, properties.getProperty("icausername"),
                properties.getProperty("icapassword"));
        this.gruppierungService = new GruppierungService(icaConnector);
    }

    @AfterEach
    public void tearDown() throws Exception {
        this.icaConnector.close();
    }

    @Test
    public void getChildGruppierungen() throws Exception {
        Collection<IcaGruppierung> icaGruppierung = gruppierungService.getChildGruppierungen(1);
        assertNotNull(icaGruppierung);
        assertEquals(14, icaGruppierung.size());
    }

    @Test
    public void getRootGruppierung() throws Exception {
        IcaGruppierung icaGruppierung = gruppierungService.getRootGruppierung();
        assertNotNull(icaGruppierung);
    }

    @Test
    public void getGruppierungenBezirkMuenchen() throws Exception {
        Collection<IcaGruppierung> icaGruppierung = gruppierungService.getGruppierungen(31);
        assertEquals(15, icaGruppierung.size());

    }

    @Test
    public void getGruppierungenHessen() throws Exception {
        Collection<IcaGruppierung> icaGruppierung = gruppierungService.getGruppierungen(46);
        assertEquals(49, icaGruppierung.size());
    }

    @Test
    public void getGruppierungenMeckpom() throws Exception {
        Collection<IcaGruppierung> icaGruppierung = gruppierungService.getGruppierungen(48);
        assertEquals(3, icaGruppierung.size());
    }

    @Test
    public void getAllGruppierungen() throws Exception {
        Collection<IcaGruppierung> icaGruppierung = gruppierungService.getAllGruppierungen();
        assertEquals(311, icaGruppierung.size());
    }
}