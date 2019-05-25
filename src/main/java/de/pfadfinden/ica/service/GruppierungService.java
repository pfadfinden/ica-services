package de.pfadfinden.ica.service;

import com.google.gson.reflect.TypeToken;
import de.pfadfinden.ica.IcaConnection;
import de.pfadfinden.ica.execption.IcaApiException;
import de.pfadfinden.ica.model.IcaGruppierung;
import de.pfadfinden.ica.model.IcaGruppierungDetail;
import okhttp3.HttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * Services zu Gruppierungen, wie z.B. Landesverbaende, Bezirke und Staemme
 */
public class GruppierungService {

    private IcaConnection icaConnection;
    private final Logger logger = LoggerFactory.getLogger(GruppierungService.class);

    private static final String URL_GRP = "nami/gruppierungen/filtered-for-navigation/gruppierung/node";
    private static final String URL_GRP_DETAIL = "nami/gruppierungen-for-grpadmin/parentgruppierung" +
            "/parentGruppierung/0";

    public GruppierungService(IcaConnection icaConnection) {
        Objects.requireNonNull(icaConnection);
        this.icaConnection = icaConnection;
    }

    /**
     * Finde die Basisgruppierung des authentifizierten Benutzers.
     *
     * @return Root {@link IcaGruppierung} des authentifzierten Benutzers
     * @throws IcaApiException bei Kommunikationsfehler mit API
     */
    public IcaGruppierung getRootGruppierung() throws IcaApiException {

        HttpUrl httpUrl = icaConnection.getUrlBuilder()
                .addPathSegments(URL_GRP)
                .addPathSegment("root")
                .addQueryParameter("node","root")
                .build();

        Type type = new TypeToken<Collection<IcaGruppierung>>() {}.getType();
        Collection<IcaGruppierung> icaGruppierung = icaConnection.executeApiRequest(httpUrl, type);
        if(icaGruppierung == null || icaGruppierung.size() != 1)
            throw new IcaApiException("Result for " + "rootGruppierung != 1");
        IcaGruppierung rootGruppierung = icaGruppierung.iterator().next();
        logger.debug("rootGruppierung #{} '{}'",rootGruppierung.getId(),rootGruppierung.getGruppierungsname());
        return rootGruppierung;
    }

    /**
     * Finde unmittelbar untergeordneten Kind-Gruppierungen zu einer Gruppierung.
     * Methode geht nicht rekursiv vor, nutze {@link #getGruppierungen(int)} um auch Kindeskinder zu finden.
     *
     * @param  gruppierungId ID der Gruppierung
     * @return Liste von {@link IcaGruppierung} zu Gruppierung
     * @throws IcaApiException bei Kommunikationsfehler mit API
     */
    public Collection<IcaGruppierung> getChildGruppierungen(int gruppierungId) throws IcaApiException {
        logger.debug("Calling getChildGruppierungen({})",gruppierungId);

        HttpUrl httpUrl = icaConnection.getUrlBuilder()
                .addPathSegments(URL_GRP)
                .addQueryParameter("node",Integer.toString(gruppierungId))
                .build();

        Type type = new TypeToken<Collection<IcaGruppierung>>() {}.getType();
        return icaConnection.executeApiRequest(httpUrl, type);
    }

    /**
     * Finde alle Gruppierungen. Methode geht ressourcenlastig durch ganzen Gruppierungsbaum.
     *
     * @return alle {@link IcaGruppierung}
     * @throws IcaApiException bei Kommunikationsfehler mit API
     */
    public Collection<IcaGruppierung> getAllGruppierungen() throws IcaApiException {

        Collection<IcaGruppierung> gruppierungen = new ArrayList<>();

        IcaGruppierung rootGruppierung = this.getRootGruppierung();
        logger.info("Root Gruppierung: {}.",rootGruppierung.getDescriptor());
        gruppierungen.add(rootGruppierung);
        gruppierungen.addAll(this.getGruppierungen(rootGruppierung.getId()));
        return gruppierungen;
    }

    /**
     * Finde alle untergeorndete Gruppierungen einschließlich Kindeskinder.
     * Um nur unmittelbare Kindgruppierungen zu finden, nutze {@link #getChildGruppierungen(int)}.
     *
     * @param  gruppierungId ID der Gruppierung
     * @return {@link IcaGruppierung} einschließlich Kindeskinder zu Gruppierung
     * @throws IcaApiException bei Kommunikationsfehler mit API
     */
    public Collection<IcaGruppierung> getGruppierungen(int gruppierungId) throws IcaApiException {

        Collection<IcaGruppierung> gruppierungen = new ArrayList<>();

        Collection<IcaGruppierung> childGruppierungen = this.getChildGruppierungen(gruppierungId);
        for(IcaGruppierung childGruppierung : childGruppierungen){
            logger.debug("ChildGruppierung #{} ({})",childGruppierung.getId(),childGruppierung.getDescriptor());

            // Falls Gruppierung mit zzz beginnt, ist sie deaktiviert und wird ignoriert.
            if(childGruppierung.isZzz()) continue;

            gruppierungen.add(childGruppierung);

            // Nur wenn Gruppierungsnummer auf 00 endet, tiefere Ebene in Baum vorhanden
            String gruppierungsNummer = childGruppierung.getGruppierungsnummer();
            if(gruppierungsNummer.substring(gruppierungsNummer.length() - 2).equals("00")){
                gruppierungen.addAll(this.getGruppierungen(childGruppierung.getId()));
            }
        }
        return gruppierungen;
    }

    /**
     * Details zu einer Gruppierung.
     *
     * @param  gruppierungId ID der Gruppierung
     * @return Optional Details zu einer Gruppierung als {@link IcaGruppierung}
     * @throws IcaApiException bei Kommunikationsfehler mit API
     */
    public Optional<IcaGruppierungDetail> getGruppierungDetail(int gruppierungId) throws IcaApiException {

        HttpUrl httpUrl = icaConnection.getUrlBuilder()
                .addPathSegments(URL_GRP_DETAIL)
                .addPathSegment(String.valueOf(gruppierungId))
                .build();

        Type type = new TypeToken<IcaGruppierungDetail>() {}.getType();
        Optional<IcaGruppierungDetail> icaGruppierungDetail = Optional.ofNullable(
                icaConnection.executeApiRequest(httpUrl, type)
        );
        logger.debug("Return IcaGruppierungDetail {}",icaGruppierungDetail);
        return icaGruppierungDetail;
    }

}
