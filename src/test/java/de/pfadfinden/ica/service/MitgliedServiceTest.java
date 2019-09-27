package de.pfadfinden.ica.service;

import de.pfadfinden.ica.IcaConnection;
import de.pfadfinden.ica.IcaServer;
import de.pfadfinden.ica.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class MitgliedServiceTest {

    private IcaConnection icaConnector;
    private MitgliedService mitgliedService;
    private Properties properties = new Properties();

    @BeforeEach
    public void setUp() throws Exception {
        InputStream is = ClassLoader.getSystemResourceAsStream("unittest.properties");
        properties.load(is);

        this.icaConnector = new IcaConnection(IcaServer.BDP_QA, properties.getProperty("icausername"),
                properties.getProperty("icapassword"));
        this.mitgliedService = new MitgliedService(icaConnector);
    }

    @Test
    public void getMitgliedByIdEmptyResult() throws Exception {
        Optional<IcaMitglied> mitglied = mitgliedService.getMitgliedById(999);
        assertFalse(mitglied.isPresent());
    }

    @Test
    public void getMitgliedByIdOneResult() throws Exception {
        Optional<IcaMitglied> mitglied = mitgliedService.getMitgliedById(11111);
        assertTrue(mitglied.isPresent());
        mitglied.ifPresent(
                icaMitglied -> {
                    assertEquals("Uteiumetzgeö", icaMitglied.getNachname());
                    assertEquals(IcaMitgliedStatus.AKTIV, icaMitglied.getStatus());
                }
        );
    }

    @Test
    public void getMitgliedBySearchEmptyResult() throws Exception {
        IcaSearchedValues searchedValues = new IcaSearchedValues();
        searchedValues.setMitgliedsNummber("999");
        ArrayList<IcaMitgliedListElement> mitglieder = mitgliedService.getMitgliedBySearch(searchedValues, 1, 0, 100);
        assertNotNull(mitglieder);
        assertEquals(0, mitglieder.size());
    }

    @Test
    public void getMitgliedBySearchBuilderEmptyResult() throws Exception {
        IcaSearchedValues.Builder builder = new IcaSearchedValues.Builder().withMitgliedsNummber("999");
        ArrayList<IcaMitgliedListElement> mitglieder = mitgliedService.getMitgliedBySearch(builder.build(), 1, 0, 100);
        assertNotNull(mitglieder);
        assertEquals(0, mitglieder.size());
    }

    @Test
    public void getMitgliedBySearchOneResult() throws Exception {
        IcaSearchedValues searchedValues = new IcaSearchedValues();
        searchedValues.setMitgliedsNummber("11111");
        searchedValues.setMglStatusId(IcaSearchedValuesStatus.AKTIV);

        ArrayList<IcaMitgliedListElement> mitglieder = mitgliedService.getMitgliedBySearch(searchedValues, 1, 0, 100);
        assertEquals(1, mitglieder.size());
        System.out.println(mitglieder.get(0).getStatus());
     //   assertThat(mitglieder.get(0), instanceOf(IcaMitgliedListElement.class));
    }

}
