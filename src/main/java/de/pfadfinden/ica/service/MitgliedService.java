package de.pfadfinden.ica.service;

import com.google.gson.reflect.TypeToken;
import de.pfadfinden.ica.IcaConnection;
import de.pfadfinden.ica.execption.IcaApiException;
import de.pfadfinden.ica.model.IcaMitglied;
import de.pfadfinden.ica.model.IcaMitgliedListElement;
import de.pfadfinden.ica.model.IcaSearchedValues;
import okhttp3.HttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;

public class MitgliedService {

    private IcaConnection icaConnection;
    private final Logger logger = LoggerFactory.getLogger(MitgliedService.class);

    /**
     * URL, mit der der Datensatz eines Mitglieds (identifiziert durch seine ID)
     * abgefragt wird.
     *
     * Am Ende der URL müsste eigentlich die GruppierungsID angegeben sein.
     * Scheinbar kann man aber auch immer "0" angeben und bekommt trotzdem jedes Mitglied geliefert
     */
    private static final String URL_MITGLIED = "nami/mitglied/filtered-for-navigation/gruppierung/gruppierung/0";

    /**
     * URL, um eine Suchanfrage an NaMi zu senden.
     */
    private static final String URL_SEARCH = "nami/search-multi/result-list";

    public MitgliedService(IcaConnection icaConnection) {
        this.icaConnection = icaConnection;
    }

    public Optional<IcaMitglied> getMitgliedById(int id) throws IOException, URISyntaxException, IcaApiException {
        logger.debug("Lookup IcaMitglied by id: #{}",id);

        HttpUrl httpUrl = icaConnection.getUrlBuilder()
                .addPathSegments(URL_MITGLIED)
                .addPathSegment(Integer.toString(id))
                .build();

        Type type = new TypeToken<IcaMitglied>() {}.getType();
        IcaMitglied icaMitglied = icaConnection.executeApiRequest(httpUrl, type);
        logger.debug("Return IcaMitglied {}",icaMitglied);
        return Optional.ofNullable(icaMitglied);
    }

    public ArrayList<IcaMitgliedListElement> getMitgliedBySearch(IcaSearchedValues icaSearchedValues,
                                                                 Integer page, Integer start, Integer limit) throws IcaApiException {
        logger.debug("Lookup IcaMitglied by searchedValues: {}",icaSearchedValues);

        HttpUrl httpUrl = icaConnection.getUrlBuilder()
                .addPathSegments(URL_SEARCH)
                .addQueryParameter("page",page.toString())
                .addQueryParameter("start",start.toString())
                .addQueryParameter("limit",limit.toString())
                .addQueryParameter("searchedValues",icaConnection.toJson(icaSearchedValues))
                .build();

        Type type = new TypeToken<ArrayList<IcaMitgliedListElement>>() {}.getType();
        return icaConnection.executeApiRequest(httpUrl, type);
    }

}
