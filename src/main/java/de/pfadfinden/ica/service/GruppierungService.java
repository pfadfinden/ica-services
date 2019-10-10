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
import java.util.*;

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
     * Finde unmittelbar untergeordnete Kind-Gruppierungen zu einer Gruppierung.
     *
     * @param  gruppierungId ID der Gruppierung
     * @return Liste von {@link IcaGruppierung} zu Gruppierung
     * @throws IcaApiException bei Kommunikationsfehler mit API
     */
    public Collection<IcaGruppierung> getChildGruppierungen(int gruppierungId) throws IcaApiException {
        return this.getChildGruppierungen(gruppierungId,true);
    }

    /**
     * Finde unmittelbar untergeordnete Kind-Gruppierungen zu einer Gruppierung.
     *
     * @param  gruppierungId ID der Gruppierung
     * @param  inclDisabled Deaktivierte Gruppierungen (zzz) einschliessen
     * @return Liste von {@link IcaGruppierung} zu Gruppierung
     * @throws IcaApiException bei Kommunikationsfehler mit API
     */
    public Collection<IcaGruppierung> getChildGruppierungen(int gruppierungId, boolean inclDisabled) throws IcaApiException {
        HttpUrl httpUrl = icaConnection.getUrlBuilder()
                .addPathSegments(URL_GRP)
                .addQueryParameter("node",Integer.toString(gruppierungId))
                .build();

        Type type = new TypeToken<Collection<IcaGruppierung>>() {}.getType();
        Collection<IcaGruppierung> icaGruppierungen = icaConnection.executeApiRequest(httpUrl, type);

        icaGruppierungen.removeIf(icaGruppierung -> icaGruppierung.isZzz() && !inclDisabled);
        return icaGruppierungen;
    }

    /**
     * Finde alle Gruppierungen auf die der Benutzer Zugriff hat.
     * Methode geht ressourcenlastig durch ganzen Gruppierungsbaum.
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
        return this.getGruppierungen(gruppierungId,false);
    }

    /**
     * Finde alle untergeorndete Gruppierungen einschließlich Kindeskinder.
     * Um nur unmittelbare Kindgruppierungen zu finden, nutze {@link #getChildGruppierungen(int)}.
     * Rueckgabe als flache Collection, nutze {@link #getGruppierungenTree(IcaGruppierung,boolean)} fuer Baumstruktur.
     *
     * @param  gruppierungId ID der Gruppierung
     * @param  inclDisabled Inkludiere deaktivierte Gruppierungen
     * @return Collection<IcaGruppierung> einschließlich Kindeskinder zu Gruppierung
     * @throws IcaApiException bei Kommunikationsfehler mit API
     */
    public Collection<IcaGruppierung> getGruppierungen(int gruppierungId, boolean inclDisabled) throws IcaApiException {

        Collection<IcaGruppierung> gruppierungen = new ArrayList<>();

        Collection<IcaGruppierung> childGruppierungen = this.getChildGruppierungen(gruppierungId);
        for(IcaGruppierung childGruppierung : childGruppierungen){

            // Falls Gruppierung mit zzz beginnt, ist sie deaktiviert und wird ignoriert.
            if(!inclDisabled && childGruppierung.isZzz()) continue;

            logger.debug("ChildGruppierung #{} ({})",childGruppierung.getId(),childGruppierung.getDescriptor());
            gruppierungen.add(childGruppierung);

            // Nur wenn Gruppierungsnummer auf 00 endet, tiefere Ebene in Baum vorhanden
            String gruppierungsNummer = childGruppierung.getGruppierungsnummer();
            if(gruppierungsNummer.substring(gruppierungsNummer.length() - 2).equals("00")){
                gruppierungen.addAll(this.getGruppierungen(childGruppierung.getId(),inclDisabled));
            }
        }
        return gruppierungen;
    }

    /**
     * Finde alle untergeorndete Gruppierungen einschließlich Kindeskinder.
     * Rueckgabe erfolgt in hierarchischer Baumstruktur.
     *
     * @param  icaGruppierung Basisgruppierung
     * @param  inclDisabled Inkludiere deaktivierte Gruppierungen
     * @return {@link IcaGruppierung} einschließlich Kindeskinder zu Gruppierung in Baumstruktur
     * @throws IcaApiException bei Kommunikationsfehler mit API
     */
    public IcaGruppierung getGruppierungenTree(IcaGruppierung icaGruppierung, boolean inclDisabled) throws IcaApiException {
        Objects.requireNonNull(icaGruppierung);
        Collection<IcaGruppierung> childGruppierungen = this.getChildGruppierungen(icaGruppierung.getId(),inclDisabled);
        for(IcaGruppierung childGruppierung : childGruppierungen){
            // Wenn Gruppierung keine Kinder zulaesst, nicht tiefer iterieren
            if(!childGruppierung.isChildrenAllowed()) continue;
            this.getGruppierungenTree(childGruppierung, inclDisabled);
        }
        icaGruppierung.setChildren(childGruppierungen);
        return icaGruppierung;
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
