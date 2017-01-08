package de.pfadfinden.ica.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class IcaMitgliedListElement {

    private long id;
    private String descriptor;
    private String representedClass;

    @SerializedName("entries_ersteTaetigkeitId")
    private String ersteTaetigkeitId;

    @SerializedName("entries_genericField1")
    private String genericField1;

    @SerializedName("entries_version")
    private long version;

    @SerializedName("entries_telefon3")
    private String telefon3;

    @SerializedName("entries_telefon2")
    private String telefon2;

    @SerializedName("entries_telefon1")
    private String telefon1;

    @SerializedName("entries_id")
    private long entriesId;

    @SerializedName("entries_staatsangehoerigkeit")
    private String staatsangehoerigkeit;

    @SerializedName("entries_rover")
    private String rover;

    @SerializedName("entries_pfadfinder")
    private String pfadfinder;

    @SerializedName("entries_mitgliedsNummer")
    private long mitgliedsNummer;

    @SerializedName("entries_wiederverwendenFlag")
    private String wiederverwendenFlag;

    @SerializedName("entries_ersteUntergliederungId")
    private String ersteUntergliederungId;

    @SerializedName("entries_rowCssClass")
    private String rowCssClass;

    @SerializedName("entries_gruppierung")
    private String gruppierung;

    @SerializedName("entries_vorname")
    private String vorname;

    @SerializedName("entries_woelfling")
    private String woelfling;

    @SerializedName("entries_beitragsarten")
    private String beitragsarten;

    @SerializedName("entries_stufe")
    private String stufe;

    @SerializedName("entries_email")
    private String email;

    @SerializedName("entries_konfession")
    private String konfession;

    @SerializedName("entries_gruppierungId")
    private String gruppierungId;

    @SerializedName("entries_emailVertretungsberechtigter")
    private String emailVertretungsberechtigter;

    @SerializedName("entries_fixBeitrag")
    private String fixBeitrag;

    @SerializedName("entries_lastUpdated")
    private String lastUpdated;

    @SerializedName("entries_status")
    private String status;

    @SerializedName("entries_jungpfadfinder")
    private String jungpfadfinder;

    @SerializedName("entries_mglType")
    private String mglType;

    @SerializedName("entries_kontoverbindung")
    private String kontoverbindung;

    @SerializedName("entries_geschlecht")
    private String geschlecht;

    @SerializedName("entries_spitzname")
    private String spitzname;

    @SerializedName("entries_geburtsDatum")
    private LocalDate geburtsDatum;

    @SerializedName("entries_staatangehoerigkeitText")
    private String staatangehoerigkeitText;

    @SerializedName("entries_nachname")
    private String nachname;

    @SerializedName("entries_eintrittsdatum")
    private LocalDate eintrittsdatum;

    @SerializedName("entries_austrittsDatum")
    private LocalDate austrittsDatum;

    @SerializedName("entries_genericField2")
    private String genericField2;

    @SerializedName("entries_telefax")
    private String telefax;

    /* Spec func */
    public String toString(){
        return "IcaMitglied{" +
                "membershipNumber=" + mitgliedsNummer +
                ", lastName='" + nachname + "'" +
                ", firstName='" + vorname + "'" +
                ", dateOfBirth='" + geburtsDatum +
                '}';
    }

    /* Getter and Setter */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getRepresentedClass() {
        return representedClass;
    }

    public void setRepresentedClass(String representedClass) {
        this.representedClass = representedClass;
    }

    public String getErsteTaetigkeitId() {
        return ersteTaetigkeitId;
    }

    public void setErsteTaetigkeitId(String ersteTaetigkeitId) {
        this.ersteTaetigkeitId = ersteTaetigkeitId;
    }

    public String getGenericField1() {
        return genericField1;
    }

    public void setGenericField1(String genericField1) {
        this.genericField1 = genericField1;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getTelefon3() {
        return telefon3;
    }

    public void setTelefon3(String telefon3) {
        this.telefon3 = telefon3;
    }

    public String getTelefon2() {
        return telefon2;
    }

    public void setTelefon2(String telefon2) {
        this.telefon2 = telefon2;
    }

    public String getTelefon1() {
        return telefon1;
    }

    public void setTelefon1(String telefon1) {
        this.telefon1 = telefon1;
    }

    public long getEntriesId() {
        return entriesId;
    }

    public void setEntriesId(long entriesId) {
        this.entriesId = entriesId;
    }

    public String getStaatsangehoerigkeit() {
        return staatsangehoerigkeit;
    }

    public void setStaatsangehoerigkeit(String staatsangehoerigkeit) {
        this.staatsangehoerigkeit = staatsangehoerigkeit;
    }

    public String getRover() {
        return rover;
    }

    public void setRover(String rover) {
        this.rover = rover;
    }

    public String getPfadfinder() {
        return pfadfinder;
    }

    public void setPfadfinder(String pfadfinder) {
        this.pfadfinder = pfadfinder;
    }

    public long getMitgliedsNummer() {
        return mitgliedsNummer;
    }

    public void setMitgliedsNummer(long mitgliedsNummer) {
        this.mitgliedsNummer = mitgliedsNummer;
    }

    public String getWiederverwendenFlag() {
        return wiederverwendenFlag;
    }

    public void setWiederverwendenFlag(String wiederverwendenFlag) {
        this.wiederverwendenFlag = wiederverwendenFlag;
    }

    public String getErsteUntergliederungId() {
        return ersteUntergliederungId;
    }

    public void setErsteUntergliederungId(String ersteUntergliederungId) {
        this.ersteUntergliederungId = ersteUntergliederungId;
    }

    public String getRowCssClass() {
        return rowCssClass;
    }

    public void setRowCssClass(String rowCssClass) {
        this.rowCssClass = rowCssClass;
    }

    public String getGruppierung() {
        return gruppierung;
    }

    public void setGruppierung(String gruppierung) {
        this.gruppierung = gruppierung;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getWoelfling() {
        return woelfling;
    }

    public void setWoelfling(String woelfling) {
        this.woelfling = woelfling;
    }

    public String getBeitragsarten() {
        return beitragsarten;
    }

    public void setBeitragsarten(String beitragsarten) {
        this.beitragsarten = beitragsarten;
    }

    public String getStufe() {
        return stufe;
    }

    public void setStufe(String stufe) {
        this.stufe = stufe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKonfession() {
        return konfession;
    }

    public void setKonfession(String konfession) {
        this.konfession = konfession;
    }

    public String getGruppierungId() {
        return gruppierungId;
    }

    public void setGruppierungId(String gruppierungId) {
        this.gruppierungId = gruppierungId;
    }

    public String getEmailVertretungsberechtigter() {
        return emailVertretungsberechtigter;
    }

    public void setEmailVertretungsberechtigter(String emailVertretungsberechtigter) {
        this.emailVertretungsberechtigter = emailVertretungsberechtigter;
    }

    public String getFixBeitrag() {
        return fixBeitrag;
    }

    public void setFixBeitrag(String fixBeitrag) {
        this.fixBeitrag = fixBeitrag;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJungpfadfinder() {
        return jungpfadfinder;
    }

    public void setJungpfadfinder(String jungpfadfinder) {
        this.jungpfadfinder = jungpfadfinder;
    }

    public String getMglType() {
        return mglType;
    }

    public void setMglType(String mglType) {
        this.mglType = mglType;
    }

    public String getKontoverbindung() {
        return kontoverbindung;
    }

    public void setKontoverbindung(String kontoverbindung) {
        this.kontoverbindung = kontoverbindung;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getSpitzname() {
        return spitzname;
    }

    public void setSpitzname(String spitzname) {
        this.spitzname = spitzname;
    }

    public LocalDate getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum(LocalDate geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }

    public String getStaatangehoerigkeitText() {
        return staatangehoerigkeitText;
    }

    public void setStaatangehoerigkeitText(String staatangehoerigkeitText) {
        this.staatangehoerigkeitText = staatangehoerigkeitText;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public LocalDate getEintrittsdatum() {
        return eintrittsdatum;
    }

    public void setEintrittsdatum(LocalDate eintrittsdatum) {
        this.eintrittsdatum = eintrittsdatum;
    }

    public LocalDate getAustrittsDatum() {
        return austrittsDatum;
    }

    public void setAustrittsDatum(LocalDate austrittsDatum) {
        this.austrittsDatum = austrittsDatum;
    }

    public String getGenericField2() {
        return genericField2;
    }

    public void setGenericField2(String genericField2) {
        this.genericField2 = genericField2;
    }

    public String getTelefax() {
        return telefax;
    }

    public void setTelefax(String telefax) {
        this.telefax = telefax;
    }



}
