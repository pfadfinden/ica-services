package de.pfadfinden.ica.service;

import de.pfadfinden.ica.IcaConnection;
import de.pfadfinden.ica.IcaServer;
import de.pfadfinden.ica.execption.IcaApiException;
import de.pfadfinden.ica.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class GruppierungServiceTest {

    private GruppierungService gruppierungService;
    private Properties properties = new Properties();

    @BeforeEach
    public void setUp() throws Exception {
        InputStream is = ClassLoader.getSystemResourceAsStream("unittest.properties");
        properties.load(is);

        IcaConnection icaConnector = new IcaConnection(IcaServer.BDP_QA, properties.getProperty("icausername"),
                properties.getProperty("icapassword"));
        this.gruppierungService = new GruppierungService(icaConnector);
    }

    @AfterEach
    public void tearDown() throws Exception {
//        this.icaConnector.close();
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
    public void getGruppierungen() throws Exception {
        Collection<IcaGruppierung> icaGruppierungenSSH = gruppierungService.getGruppierungen(55);
        assertEquals(26, icaGruppierungenSSH.size());

        Collection<IcaGruppierung> icaGruppierungMeckpom = gruppierungService.getGruppierungen(48);
        assertEquals(1, icaGruppierungMeckpom.size());

        Collection<IcaGruppierung> icaGruppierungHessen = gruppierungService.getGruppierungen(46);
        assertEquals(47, icaGruppierungHessen.size());

        Collection<IcaGruppierung> icaGruppierungMuenchen = gruppierungService.getGruppierungen(31);
        assertEquals(15, icaGruppierungMuenchen.size());
    }

    @Test
    public void getAllGruppierungen() throws Exception {
        Collection<IcaGruppierung> icaGruppierung = gruppierungService.getAllGruppierungen();
        assertEquals(311, icaGruppierung.size());
    }

    @Test
    void getGruppierungDetail() throws IcaApiException {
        Optional<IcaGruppierungDetail> icaGruppierungDetail = gruppierungService.getGruppierungDetail(1);
        assertNotNull(icaGruppierungDetail.get());
        assertEquals("BdP",icaGruppierungDetail.get().getName());
        assertEquals(IcaEbene.BUND,icaGruppierungDetail.get().getEbene());
        assertEquals(IcaGruppierungStatus.AKTIV,icaGruppierungDetail.get().getStatus());
        assertEquals("Sparkasse Grebenstein",icaGruppierungDetail.get().getKontoverbindung().getInstitut());
    }

    @Test
    void getGruppierungDetailEmptyResult() throws IcaApiException {
        Optional<IcaGruppierungDetail> icaGruppierungDetail =
                gruppierungService.getGruppierungDetail(9999999);
        assertFalse(icaGruppierungDetail.isPresent());
    }
}