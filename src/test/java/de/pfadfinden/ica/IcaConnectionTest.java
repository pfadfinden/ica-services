package de.pfadfinden.ica;

import de.pfadfinden.ica.execption.IcaAuthenticationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class IcaConnectionTest {

    @Test
    void icaConnectionConstructor() {
        Assertions.assertThrows(NullPointerException.class, () ->
                new IcaConnection(null,"Test","Test"));
        Assertions.assertThrows(NullPointerException.class, () ->
                new IcaConnection(IcaServer.BDP_QA,null,"Test"));
        Assertions.assertThrows(NullPointerException.class, () ->
                new IcaConnection(IcaServer.BDP_QA,"Test",null));
    }

    @Test
    void icaConnectionAuthentication() throws IOException {
        Assertions.assertThrows(IcaAuthenticationException.class, () ->
                new IcaConnection(IcaServer.BDP_QA,"Invalid","Invalid"));

        Assertions.assertThrows(IcaAuthenticationException.class, () ->
                new IcaConnection(IcaServer.BDP_QA,"Invalid","Invalid"));

        InputStream is = ClassLoader.getSystemResourceAsStream("unittest.properties");
        Properties properties = new Properties();
        properties.load(is);

        Assertions.assertDoesNotThrow(() -> new IcaConnection(
                IcaServer.BDP_QA,properties.getProperty("icausername"),properties.getProperty("icapassword")));
    }

}