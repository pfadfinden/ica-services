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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/**
 * Services zu Mitgliedern
 */
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
        Objects.requireNonNull(icaConnection);
        this.icaConnection = icaConnection;
    }

    /**
     * Finde ein Mitglied.
     *
     * @param id ID des Mitglieds
     * @return Optional von einem {@link IcaMitglied}
     * @throws IcaApiException bei Kommunikationsfehler mit API
     */
    public Optional<IcaMitglied> getMitgliedById(int id) throws IcaApiException {
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

    /**
     * Finde alle Mitglieder die den Suchparametern entsprechen.
     * Die Funktion entspricht der Oberflaeche <a href="https://meinbdp.de/x/5IQtB">Suche verwenden</a>.
     *
     * @param  icaSearchedValues Mitglieder Suchparameter {@link IcaSearchedValues}
     * @param  limit Maximale Ergebnisanzahl
     * @param  page Anzahl von Ergebnissen je Seite
     * @param  start Erstes Ergebnis in Aufruf
     * @return Liste von {@link IcaMitgliedListElement}
     * @throws IcaApiException bei Kommunikationsfehler mit API
     * @see <a href="https://meinbdp.de/x/5IQtB">MeinBdP: Suche verwenden</a>
     */
    public ArrayList<IcaMitgliedListElement> getMitgliedBySearch(IcaSearchedValues icaSearchedValues,
                                                                 Integer page, Integer start, Integer limit) throws IcaApiException {
        logger.debug("Lookup IcaMitglied by searchedValues: {}",icaSearchedValues);

        Objects.requireNonNull(icaSearchedValues);
        Objects.requireNonNull(page);
        Objects.requireNonNull(start);
        Objects.requireNonNull(limit);

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
