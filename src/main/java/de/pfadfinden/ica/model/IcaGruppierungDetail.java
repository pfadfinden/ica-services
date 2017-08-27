package de.pfadfinden.ica.model;

import com.google.common.base.MoreObjects;

public class IcaGruppierungDetail {

    private int id;

    private String adrZusatzSgbAchtFz;
    private String aufloesungsDatum;

    private String creditorIdentification;
    private IcaEbene ebene;
    private int ebeneId;
    private String email;
    private String faxNummer;
    private String fibuDebitorKonto;
    private String fibuErloesKonto;
    private String grpNummer;
    private String gruendungsDatum;
    private boolean handlingSgbAchtFz;

    private IcaKontoverbindung kontoverbindung;

    private String name;
    private String nummer;
    private String parentGruppierung;
    private int parentGruppierungId;

    private String sitzLand;
    private int sitzLandId;
    private String sitzNameZusatz;
    private String sitzOrt;
    private String sitzPlz;
    private String sitzRegion;
    private String sitzRegionId;
    private String sitzStrasse;

    private IcaStatus status;
    private String telNummer;

    private String versandLand;
    private int versandLandId;
    private String versandNameZusatz;
    private String versandOrt;
    private String versandPlz;
    private String versandRegion;
    private int versandRegionId;
    private String versandStrasse;

    private String webUrl;
    private String zahlungsKondition;
    private int zahlungsKonditionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdrZusatzSgbAchtFz() {
        return adrZusatzSgbAchtFz;
    }

    public void setAdrZusatzSgbAchtFz(String adrZusatzSgbAchtFz) {
        this.adrZusatzSgbAchtFz = adrZusatzSgbAchtFz;
    }

    public String getAufloesungsDatum() {
        return aufloesungsDatum;
    }

    public void setAufloesungsDatum(String aufloesungsDatum) {
        this.aufloesungsDatum = aufloesungsDatum;
    }

    public String getCreditorIdentification() {
        return creditorIdentification;
    }

    public void setCreditorIdentification(String creditorIdentification) {
        this.creditorIdentification = creditorIdentification;
    }

    public IcaEbene getEbene() {
        return ebene;
    }

    public void setEbene(IcaEbene ebene) {
        this.ebene = ebene;
    }

    public int getEbeneId() {
        return ebeneId;
    }

    public void setEbeneId(int ebeneId) {
        this.ebeneId = ebeneId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaxNummer() {
        return faxNummer;
    }

    public void setFaxNummer(String faxNummer) {
        this.faxNummer = faxNummer;
    }

    public String getFibuDebitorKonto() {
        return fibuDebitorKonto;
    }

    public void setFibuDebitorKonto(String fibuDebitorKonto) {
        this.fibuDebitorKonto = fibuDebitorKonto;
    }

    public String getFibuErloesKonto() {
        return fibuErloesKonto;
    }

    public void setFibuErloesKonto(String fibuErloesKonto) {
        this.fibuErloesKonto = fibuErloesKonto;
    }

    public String getGrpNummer() {
        return grpNummer;
    }

    public void setGrpNummer(String grpNummer) {
        this.grpNummer = grpNummer;
    }

    public String getGruendungsDatum() {
        return gruendungsDatum;
    }

    public void setGruendungsDatum(String gruendungsDatum) {
        this.gruendungsDatum = gruendungsDatum;
    }

    public boolean isHandlingSgbAchtFz() {
        return handlingSgbAchtFz;
    }

    public void setHandlingSgbAchtFz(boolean handlingSgbAchtFz) {
        this.handlingSgbAchtFz = handlingSgbAchtFz;
    }

    public IcaKontoverbindung getKontoverbindung() {
        return kontoverbindung;
    }

    public void setKontoverbindung(IcaKontoverbindung kontoverbindung) {
        this.kontoverbindung = kontoverbindung;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getParentGruppierung() {
        return parentGruppierung;
    }

    public void setParentGruppierung(String parentGruppierung) {
        this.parentGruppierung = parentGruppierung;
    }

    public int getParentGruppierungId() {
        return parentGruppierungId;
    }

    public void setParentGruppierungId(int parentGruppierungId) {
        this.parentGruppierungId = parentGruppierungId;
    }

    public String getSitzLand() {
        return sitzLand;
    }

    public void setSitzLand(String sitzLand) {
        this.sitzLand = sitzLand;
    }

    public int getSitzLandId() {
        return sitzLandId;
    }

    public void setSitzLandId(int sitzLandId) {
        this.sitzLandId = sitzLandId;
    }

    public String getSitzNameZusatz() {
        return sitzNameZusatz;
    }

    public void setSitzNameZusatz(String sitzNameZusatz) {
        this.sitzNameZusatz = sitzNameZusatz;
    }

    public String getSitzOrt() {
        return sitzOrt;
    }

    public void setSitzOrt(String sitzOrt) {
        this.sitzOrt = sitzOrt;
    }

    public String getSitzPlz() {
        return sitzPlz;
    }

    public void setSitzPlz(String sitzPlz) {
        this.sitzPlz = sitzPlz;
    }

    public String getSitzRegion() {
        return sitzRegion;
    }

    public void setSitzRegion(String sitzRegion) {
        this.sitzRegion = sitzRegion;
    }

    public String getSitzRegionId() {
        return sitzRegionId;
    }

    public void setSitzRegionId(String sitzRegionId) {
        this.sitzRegionId = sitzRegionId;
    }

    public String getSitzStrasse() {
        return sitzStrasse;
    }

    public void setSitzStrasse(String sitzStrasse) {
        this.sitzStrasse = sitzStrasse;
    }

    public IcaStatus getStatus() {
        return status;
    }

    public void setStatus(IcaStatus status) {
        this.status = status;
    }

    public String getTelNummer() {
        return telNummer;
    }

    public void setTelNummer(String telNummer) {
        this.telNummer = telNummer;
    }

    public String getVersandLand() {
        return versandLand;
    }

    public void setVersandLand(String versandLand) {
        this.versandLand = versandLand;
    }

    public int getVersandLandId() {
        return versandLandId;
    }

    public void setVersandLandId(int versandLandId) {
        this.versandLandId = versandLandId;
    }

    public String getVersandNameZusatz() {
        return versandNameZusatz;
    }

    public void setVersandNameZusatz(String versandNameZusatz) {
        this.versandNameZusatz = versandNameZusatz;
    }

    public String getVersandOrt() {
        return versandOrt;
    }

    public void setVersandOrt(String versandOrt) {
        this.versandOrt = versandOrt;
    }

    public String getVersandPlz() {
        return versandPlz;
    }

    public void setVersandPlz(String versandPlz) {
        this.versandPlz = versandPlz;
    }

    public String getVersandRegion() {
        return versandRegion;
    }

    public void setVersandRegion(String versandRegion) {
        this.versandRegion = versandRegion;
    }

    public int getVersandRegionId() {
        return versandRegionId;
    }

    public void setVersandRegionId(int versandRegionId) {
        this.versandRegionId = versandRegionId;
    }

    public String getVersandStrasse() {
        return versandStrasse;
    }

    public void setVersandStrasse(String versandStrasse) {
        this.versandStrasse = versandStrasse;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getZahlungsKondition() {
        return zahlungsKondition;
    }

    public void setZahlungsKondition(String zahlungsKondition) {
        this.zahlungsKondition = zahlungsKondition;
    }

    public int getZahlungsKonditionId() {
        return zahlungsKonditionId;
    }

    public void setZahlungsKonditionId(int zahlungsKonditionId) {
        this.zahlungsKonditionId = zahlungsKonditionId;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("ebene", ebene)
                .add("status",status)
                .toString();
    }

}
