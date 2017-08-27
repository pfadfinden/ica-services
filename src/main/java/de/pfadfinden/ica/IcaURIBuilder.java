package de.pfadfinden.ica;

import de.pfadfinden.ica.execption.IcaURISyntaxException;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class IcaURIBuilder extends URIBuilder {

    public static final String URL_PDFREPORT = "/nami/grp-reports/filtered-for-grpadmin/run-all";
    public static final String URL_ONETIMTEDOWNLOAD = "/OneTimeDownload/otd";

    /**
     * URL, die zum Login in NaMi verwendet wird.
     */
    private static final String URL_STARTUP = "/rest/nami/auth/manual/sessionStartup";

    /**
     * URL, die zum Login in NaMi verwendet wird.
     */
    private static final String URL_TERMINATE = "/rest/nami/auth/logout";

    /**
     * URL, mit der die Root-Gruppierung und die Kinder für jede Gruppierung
     * abgefragt werden.
     */
    public static final String URL_GRP = "/nami/gruppierungen/filtered-for-navigation/gruppierung/node";

    /**
     * URL, mit der der Datensatz eines Mitglieds (identifiziert durch seine ID)
     * abgefragt wird.
     */
    // Am Ende der URL müsste eigentlich die GruppierungsID angegeben sein.
    // Scheinbar kann man aber auch immer "0" angeben und bekommt
    // trotzdem jedes Mitglied geliefert
    public static final String URL_MITGLIED = "/nami/mitglied/filtered-for-navigation/gruppierung/gruppierung/0";

    /**
     * URL, mit der eine Tätigkeitszuordnung eines Mitglieds abgefragt wird.
     */
    public static final String URL_TAETIGKEIT = "/nami/zugeordnete-taetigkeiten/filtered-for-navigation/gruppierung-mitglied/mitglied";

    /**
     * URL, mit der die Mitglieder ausgelesen werden können, die einer
     * bestimmten Gruppierung angehören (entweder als Stammgruppierung oder sie
     * üben dort eine Tätigkeit aus).
     */
    public static final String URL_MITGLIEDER_FROM_GRUPPIERUNG = "/nami/mitglied/filtered-for-navigation/gruppierung/gruppierung/%s/flist";

    /**
     * URL, mit der die Beitragszahlungen eines Mitglieds abgefragt werden
     * können.
     */
    public static final String URL_BEITRAGSZAHLUNGEN = "/mgl-verwaltungS/beitrKonto-anzeigen";

    /**
     * URL, um eine Suchanfrage an NaMi zu senden.
     */
    public static final String URL_SEARCH = "/nami/search-multi/result-list";

    /**
     * URL, mit der alle verfügbaren Tätigkeiten abgefragt werden können.
     */
    public static final String URL_TAETIGKEITEN = "/system/taetigkeit";

    /**
     * URL, mit der alle verfügbaren Untergliederungen abgefragt werden können.
     */
    public static final String URL_UNTERGLIEDERUNGEN = "/orgadmin/untergliederung";

    /**
     * Gruppierungsdetails
     */
    public static final String URL_GRP_DETAIL = "/nami/gruppierungen-for-grpadmin/parentgruppierung/parentGruppierung/0";


    public IcaURIBuilder() {
        super();
        setScheme("https");
        setHost("qa.mv.meinbdp.de");
    }

    @Override
    public URI build() {
        try {
            return super.build();
        } catch (URISyntaxException e) {
            throw new IcaURISyntaxException(e);
        }
    }

    public IcaURIBuilder(IcaServer server, String path, boolean restUrl) {
        super();
        setScheme("https");

        setHost(server.getHost());

        setPath("/" + server.getDeployment());
        if (restUrl) {
            appendPath("rest");
            appendPath("api/1/2/service");
        }

        appendPath(path);
    }

    public void appendPath(String pathAppendix) {
        String path = getPath();
        if (path.isEmpty()) {
            path = "/";
        }

        if ((path.charAt(path.length() - 1) != '/')
                && (pathAppendix.charAt(0) != '/')) {
            setPath(path + "/" + pathAppendix);
        } else {
            setPath(path + pathAppendix);
        }
    }

    public static IcaURIBuilder getLoginURIBuilder(IcaServer server) {
        return new IcaURIBuilder(server, URL_STARTUP, false);
    }

    public static IcaURIBuilder getLogoutURIBuilder(IcaServer server) {
        return new IcaURIBuilder(server, URL_TERMINATE, false);
    }
}
