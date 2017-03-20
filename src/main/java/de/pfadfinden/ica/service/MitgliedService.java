package de.pfadfinden.ica.service;

import com.google.gson.reflect.TypeToken;
import de.pfadfinden.ica.IcaConnector;
import de.pfadfinden.ica.IcaURIBuilder;
import de.pfadfinden.ica.execption.IcaApiException;
import de.pfadfinden.ica.model.IcaMitglied;
import de.pfadfinden.ica.model.IcaMitgliedListElement;
import de.pfadfinden.ica.model.IcaSearchedValues;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;

public class MitgliedService {

    private IcaConnector icaConnector;

    public MitgliedService(IcaConnector icaConnector){
        this.icaConnector = icaConnector;
    }

    public Optional<IcaMitglied> getMitgliedById(int id) throws IOException, URISyntaxException, IcaApiException {

        IcaURIBuilder builder = icaConnector.getURIBuilder(IcaURIBuilder.URL_MITGLIED);
        builder.appendPath(Integer.toString(id));

        HttpGet httpGet = new HttpGet(builder.build());
        Type type = new TypeToken<IcaMitglied>(){}.getType();
        IcaMitglied icaMitglied = icaConnector.executeApiRequest(httpGet,type);
        return Optional.ofNullable(icaMitglied);
    }

    public ArrayList<IcaMitgliedListElement> getMitgliedBySearch(IcaSearchedValues icaSearchedValues,
                                                                            Integer page, Integer start, Integer limit)
            throws URISyntaxException, IOException, IcaApiException {

        IcaURIBuilder builder = icaConnector.getURIBuilder(IcaURIBuilder.URL_SEARCH);
        builder.setParameter("page", page.toString());
        builder.setParameter("start", start.toString());
        builder.setParameter("limit", limit.toString());
        builder.setParameter("searchedValues",icaConnector.toJson(icaSearchedValues));

        HttpGet httpGet = new HttpGet(builder.build());

        Type type = new TypeToken<ArrayList<IcaMitgliedListElement>>() {}.getType();
        return icaConnector.executeApiRequest(httpGet,type);
    }

}
