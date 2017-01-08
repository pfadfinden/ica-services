package de.pfadfinden.ica.service;

import de.pfadfinden.ica.IcaConnector;
import de.pfadfinden.ica.IcaServer;
import de.pfadfinden.ica.model.IcaMitglied;
import de.pfadfinden.ica.model.IcaMitgliedListElement;
import de.pfadfinden.ica.model.IcaSearchedValues;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.assertEquals;


public class MitgliedServiceTest {

    private IcaConnector icaConnector;
    private MitgliedService mitgliedService;
    private Properties properties = new Properties();

    @Before
    public void setUp() throws Exception {
        InputStream is = ClassLoader.getSystemResourceAsStream("unittest.properties");
        properties.load(is);

        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
                properties.getProperty("icausername"), properties.getProperty("icapassword"));
        this.icaConnector = new IcaConnector(IcaServer.BDP_QA,credentials);
        this.mitgliedService = new MitgliedService(icaConnector);
    }

    @After
    public void tearDown() throws Exception {
        this.icaConnector.close();
    }

    @Test
    public void getMitgliedById() throws Exception {
        Optional<IcaMitglied> mitglied = mitgliedService.getMitgliedById(11111);
        mitglied.ifPresent(
                icaMitglied -> assertEquals(icaMitglied.getNachname(),"Uteiumetzgeö")
        );
    }

    @Test
    public void getMitgliedBySearch() throws Exception {
        IcaSearchedValues searchedValues = new IcaSearchedValues();
        searchedValues.setMitgliedsNummber("11111");

        Optional<Collection<IcaMitgliedListElement>> mitglieder = mitgliedService.getMitgliedBySearch(searchedValues,1,0,100);

        mitglieder.ifPresent(
                icaMitglieder -> assertEquals(icaMitglieder.size(),1)
        );

    }

}