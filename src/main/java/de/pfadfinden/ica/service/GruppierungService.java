package de.pfadfinden.ica.service;

import com.google.gson.reflect.TypeToken;
import de.pfadfinden.ica.IcaConnection;
import de.pfadfinden.ica.execption.IcaApiException;
import de.pfadfinden.ica.model.IcaGruppierung;
import de.pfadfinden.ica.model.IcaGruppierungDetail;
import okhttp3.HttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

public class GruppierungService {

    private IcaConnection icaConnection;
    private final Logger logger = LoggerFactory.getLogger(GruppierungService.class);

    private static final String URL_GRP = "nami/gruppierungen/filtered-for-navigation/gruppierung/node";
    private static final String URL_GRP_DETAIL = "nami/gruppierungen-for-grpadmin/parentgruppierung" +
            "/parentGruppierung/0";

    public GruppierungService(IcaConnection icaConnection) {
        this.icaConnection = icaConnection;
    }

    /**
     * Liefert genau die Root Gruppierung des Benutzers.
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
     * Liefert die unmittelbare untergeordneten Kind-Gruppierungen
     * einer Gruppierung. Geht nicht rekursiv vor, keine Kindeskinder.
     */
    public Collection<IcaGruppierung> getChildGruppierungen(int id) throws IcaApiException {
        logger.debug("Calling getChildGruppierungen({})",id);

        HttpUrl httpUrl = icaConnection.getUrlBuilder()
                .addPathSegments(URL_GRP)
                .addQueryParameter("node",Integer.toString(id))
                .build();

        Type type = new TypeToken<Collection<IcaGruppierung>>() {}.getType();
        return icaConnection.executeApiRequest(httpUrl, type);
    }

    /**
     * Finde alle Gruppierungen. Methode geht
     * ressourcenlastig durch ganzen Gruppierungsbaum.
     */
    public Collection<IcaGruppierung> getAllGruppierungen()
            throws IOException, URISyntaxException, IcaApiException {

        Collection<IcaGruppierung> gruppierungen = new ArrayList<>();

        IcaGruppierung rootGruppierung = this.getRootGruppierung();
        logger.info("Root Gruppierung: {}.",rootGruppierung.getDescriptor());
        gruppierungen.add(rootGruppierung);
        gruppierungen.addAll(this.getGruppierungen(rootGruppierung.getId()));
        return gruppierungen;
    }

    /**
     * Finde alle Gruppierungen. Methode geht ressourcenlastig durch ganzen Gruppierungsbaum.
     */
    public Collection<IcaGruppierung> getGruppierungen(int parentGruppierung)
            throws IOException, URISyntaxException, IcaApiException {

        Collection<IcaGruppierung> gruppierungen = new ArrayList<>();

        Collection<IcaGruppierung> childGruppierungen = this.getChildGruppierungen(parentGruppierung);
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
     */
    public IcaGruppierungDetail getGruppierungDetail(int gruppierungId) throws IOException, URISyntaxException,
            IcaApiException {

        HttpUrl httpUrl = icaConnection.getUrlBuilder()
                .addPathSegments(URL_GRP_DETAIL)
                .addPathSegment(String.valueOf(gruppierungId))
                .build();

        Type type = new TypeToken<IcaGruppierungDetail>() {}.getType();
        return icaConnection.executeApiRequest(httpUrl, type);
    }

}
