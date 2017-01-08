package de.pfadfinden.ica.model;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class IcaMitglied {

    /**
     * Beschreibt die Bankverbindung eines Mitglieds.
     */
    public static class KontoverbindungType {
        private String id;
        private String mitgliedsNummer;

        private String kontoinhaber;
        private String kontonummer;
        private String bankleitzahl;
        private String institut;

        private String iban;
        private String bic;
    }

    private int id;
    private int mitgliedsNummer;

    private String beitragsarten;
    private Collection<Integer> beitragsartenId;
    private IcaMitgliedStatus status;
    private IcaMitgliedTyp mglType;

    private String vorname;
    private String nachname;
    private String spitzname;
    private String strasse;
    private String plz;
    private String ort;
    private String telefon1;
    private String telefon2;
    private String telefon3;
    private String telefax;
    private String email;
    private String emailVertretungsberechtigter;

    private LocalDate geburtsDatum;

    private int landId;
    private String land;

    private String gruppierung;
    private int gruppierungId;

    private boolean wiederverwendenFlag;
    private boolean zeitschriftenversand;

    private IcaGeschlecht geschlecht;

    private KontoverbindungType kontoverbindung;

    @SerializedName("eintrittsdatum")
    private LocalDate eintrittsDatum;
    private LocalDate austrittsDatum;

    private int version;
    private LocalDateTime lastUpdated;

    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("id", this.id)
                .add("nachname", this.nachname)
                .add("vorname",this.vorname)
                .add("geschlecht",this.geschlecht)
                .add("geburtsDatum",this.geburtsDatum)
                .add("gruppierung",this.gruppierung)
                .add("gruppierungId",this.gruppierungId)
                .toString();
    }

    /* getter and setter */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMitgliedsNummer() {
        return mitgliedsNummer;
    }

    public void setMitgliedsNummer(int mitgliedsNummer) {
        this.mitgliedsNummer = mitgliedsNummer;
    }

    public String getBeitragsarten() {
        return beitragsarten;
    }

    public void setBeitragsarten(String beitragsarten) {
        this.beitragsarten = beitragsarten;
    }

    public Collection<Integer> getBeitragsartenId() {
        return beitragsartenId;
    }

    public void setBeitragsartenId(Collection<Integer> beitragsartenId) {
        this.beitragsartenId = beitragsartenId;
    }

    public IcaMitgliedStatus getStatus() {
        return status;
    }

    public void setStatus(IcaMitgliedStatus status) {
        this.status = status;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getSpitzname() {
        return spitzname;
    }

    public void setSpitzname(String spitzname) {
        this.spitzname = spitzname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getTelefon1() {
        return telefon1;
    }

    public void setTelefon1(String telefon1) {
        this.telefon1 = telefon1;
    }

    public String getTelefon2() {
        return telefon2;
    }

    public void setTelefon2(String telefon2) {
        this.telefon2 = telefon2;
    }

    public String getTelefon3() {
        return telefon3;
    }

    public void setTelefon3(String telefon3) {
        this.telefon3 = telefon3;
    }

    public String getTelefax() {
        return telefax;
    }

    public void setTelefax(String telefax) {
        this.telefax = telefax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVertretungsberechtigter() {
        return emailVertretungsberechtigter;
    }

    public void setEmailVertretungsberechtigter(String emailVertretungsberechtigter) {
        this.emailVertretungsberechtigter = emailVertretungsberechtigter;
    }

    public IcaMitgliedTyp getMglType() {
        return mglType;
    }

    public void setMglType(IcaMitgliedTyp mglType) {
        this.mglType = mglType;
    }

    public int getLandId() {
        return landId;
    }

    public void setLandId(int landId) {
        this.landId = landId;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getGruppierung() {
        return gruppierung;
    }

    public void setGruppierung(String gruppierung) {
        this.gruppierung = gruppierung;
    }

    public int getGruppierungId() {
        return gruppierungId;
    }

    public void setGruppierungId(int gruppierungId) {
        this.gruppierungId = gruppierungId;
    }

    public boolean isWiederverwendenFlag() {
        return wiederverwendenFlag;
    }

    public void setWiederverwendenFlag(boolean wiederverwendenFlag) {
        this.wiederverwendenFlag = wiederverwendenFlag;
    }

    public boolean isZeitschriftenversand() {
        return zeitschriftenversand;
    }

    public void setZeitschriftenversand(boolean zeitschriftenversand) {
        this.zeitschriftenversand = zeitschriftenversand;
    }

    public LocalDate getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum(LocalDate geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }

    public LocalDate getEintrittsDatum() {
        return eintrittsDatum;
    }

    public void setEintrittsDatum(LocalDate eintrittsDatum) {
        this.eintrittsDatum = eintrittsDatum;
    }

    public LocalDate getAustrittsDatum() {
        return austrittsDatum;
    }

    public void setAustrittsDatum(LocalDate austrittsDatum) {
        this.austrittsDatum = austrittsDatum;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    public IcaGeschlecht getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(IcaGeschlecht geschlecht) {
        this.geschlecht = geschlecht;
    }

    public KontoverbindungType getKontoverbindung() {
        return kontoverbindung;
    }

    public void setKontoverbindung(KontoverbindungType kontoverbindung) {
        this.kontoverbindung = kontoverbindung;
    }
}
