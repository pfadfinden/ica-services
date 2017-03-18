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
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;

import static com.github.npathai.hamcrestopt.OptionalMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

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
        this.icaConnector = new IcaConnector(IcaServer.BDP_QA, credentials);
        this.mitgliedService = new MitgliedService(icaConnector);
    }

    @After
    public void tearDown() throws Exception {
        this.icaConnector.close();
    }

    @Test
    public void getMitgliedByIdEmptyResult() throws Exception {
        Optional<IcaMitglied> mitglied = mitgliedService.getMitgliedById(999);
        assertThat(mitglied, isEmpty());
    }

    @Test
    public void getMitgliedByIdOneResult() throws Exception {
        Optional<IcaMitglied> mitglied = mitgliedService.getMitgliedById(11111);
        assertThat(mitglied, isPresent());
        mitglied.ifPresent(
                icaMitglied -> assertThat(icaMitglied.getNachname(), is("Uteiumetzgeö"))
        );
    }

    @Test
    public void getMitgliedBySearchEmptyResult() throws Exception {
        IcaSearchedValues searchedValues = new IcaSearchedValues();
        searchedValues.setMitgliedsNummber("999");
        ArrayList<IcaMitgliedListElement> mitglieder = mitgliedService.getMitgliedBySearch(searchedValues, 1, 0, 100);
        assertThat(mitglieder, is(notNullValue()));
        assertThat(mitglieder.size(), is(0));
    }

    @Test
    public void getMitgliedBySearchOneResult() throws Exception {
        IcaSearchedValues searchedValues = new IcaSearchedValues();
        searchedValues.setMitgliedsNummber("11111");

        ArrayList<IcaMitgliedListElement> mitglieder = mitgliedService.getMitgliedBySearch(searchedValues, 1, 0, 100);
        assertThat(mitglieder.size(), is(1));
        assertThat(mitglieder.get(0), instanceOf(IcaMitgliedListElement.class));
    }

}