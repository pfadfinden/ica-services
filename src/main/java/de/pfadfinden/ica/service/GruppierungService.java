package de.pfadfinden.ica.service;

import com.google.gson.reflect.TypeToken;
import de.pfadfinden.ica.IcaConnector;
import de.pfadfinden.ica.IcaURIBuilder;
import de.pfadfinden.ica.execption.IcaApiException;
import de.pfadfinden.ica.model.IcaGruppierung;
import de.pfadfinden.ica.model.IcaGruppierungDetail;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

public class GruppierungService {

    private IcaConnector icaConnector;
    private final Logger logger = LoggerFactory.getLogger(GruppierungService.class);

    public GruppierungService(IcaConnector icaConnector) {
        this.icaConnector = icaConnector;
    }

    /**
     * Liefert genau die Root Gruppierung des Benutzers.
     */
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

    /**
     * Liefert die unmittelbare untergeordneten Kind-Gruppierungen
     * einer Gruppierung. Geht nicht rekursiv vor, keine Kindeskinder.
     */
    public Collection<IcaGruppierung> getChildGruppierungen(int id) throws IOException, URISyntaxException,
            IcaApiException {
        logger.debug("Calling getChildGruppierungen({})",id);

        IcaURIBuilder builder = icaConnector.getURIBuilder(IcaURIBuilder.URL_GRP);
        builder.addParameter("node", Integer.toString(id));

        HttpGet httpGet = new HttpGet(builder.build());
        Type type = new TypeToken<Collection<IcaGruppierung>>() {}.getType();
        return icaConnector.executeApiRequest(httpGet, type);
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

        IcaURIBuilder builder = icaConnector.getURIBuilder(IcaURIBuilder.URL_GRP_DETAIL);
        builder.appendPath("/"+gruppierungId);

        HttpGet httpGet = new HttpGet(builder.build());
        Type type = new TypeToken<IcaGruppierungDetail>() {}.getType();
        return icaConnector.executeApiRequest(httpGet, type);
    }

}
