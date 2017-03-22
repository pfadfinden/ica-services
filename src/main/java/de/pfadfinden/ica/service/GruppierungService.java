package de.pfadfinden.ica.service;

import com.google.gson.reflect.TypeToken;
import de.pfadfinden.ica.IcaConnector;
import de.pfadfinden.ica.IcaURIBuilder;
import de.pfadfinden.ica.execption.IcaApiException;
import de.pfadfinden.ica.model.IcaGruppierung;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.Collection;

public class GruppierungService {

    private IcaConnector icaConnector;
    private final Logger logger = LoggerFactory.getLogger(GruppierungService.class);

    public GruppierungService(IcaConnector icaConnector) {
        this.icaConnector = icaConnector;
    }

    public Collection<IcaGruppierung> getChildGruppierungen(int id) throws IOException, URISyntaxException,
            IcaApiException {
        logger.debug("Lookup childGruppierungen for #{}",id);

        IcaURIBuilder builder = icaConnector.getURIBuilder(IcaURIBuilder.URL_GRP);
        builder.addParameter("node", Integer.toString(id));

        HttpGet httpGet = new HttpGet(builder.build());
        Type type = new TypeToken<Collection<IcaGruppierung>>() {
        }.getType();
        return icaConnector.executeApiRequest(httpGet, type);
    }

    public IcaGruppierung getRootGruppierung() throws IOException, URISyntaxException, IcaApiException {

        IcaURIBuilder builder = icaConnector.getURIBuilder(IcaURIBuilder.URL_GRP);
        builder.appendPath("root");
        builder.addParameter("node", "root");

        HttpGet httpGet = new HttpGet(builder.build());

        Type type = new TypeToken<Collection<IcaGruppierung>>() {
        }.getType();
        Collection<IcaGruppierung> icaGruppierung = icaConnector.executeApiRequest(httpGet, type);
        IcaGruppierung rootGruppierung = icaGruppierung.iterator().next();
        logger.debug("rootGruppierung #{} '{}'",rootGruppierung.getId(),rootGruppierung.getGruppierungsname());
        return rootGruppierung;
    }

}
