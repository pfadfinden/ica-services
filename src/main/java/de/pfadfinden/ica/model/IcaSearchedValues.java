package de.pfadfinden.ica.model;

import java.util.List;

@SuppressWarnings("unused")
public class IcaSearchedValues {

    private String vorname = "";
    private String nachname = "";
    private String spitzname = "";
    private String mitgliedsNummber = ""; // Fehler bewusst!
    private String mglWohnort = "";
    private String alterVon = "";
    private String alterBis = "";
    private IcaMitgliedStatus mglStatusId = null;
    private String funktion = "";
    private List<Integer> mglTypeId = null;
    private String organisation = "";
    private Integer tagId = null;
    private Integer bausteinIncludedId = null;
    private boolean zeitschriftenversand = false;
    private String searchName = "";
    private List<Integer> taetigkeitId = null;
    private Integer untergliederungId = null;
    private boolean mitAllenTaetigkeiten = false;
    private boolean withEndedTaetigkeiten = false;
    private Integer ebeneId = null;
    private String grpNummer = "";
    private String grpName = "";
    private String gruppierung1Id = "";
    private String gruppierung2Id = "";
    private String gruppierung3Id = "";
    private String gruppierung4Id = "";
    private String gruppierung5Id = "";
    private String gruppierung6Id = "";
    private boolean inGrp = false;
    private boolean unterhalbGrp = false;
    private String privacy = "";
    private String searchType = "MITGLIEDER";


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

    public String getMitgliedsNummber() {
        return mitgliedsNummber;
    }

    public void setMitgliedsNummber(String mitgliedsNummber) {
        this.mitgliedsNummber = mitgliedsNummber;
    }

    public String getMglWohnort() {
        return mglWohnort;
    }

    public void setMglWohnort(String mglWohnort) {
        this.mglWohnort = mglWohnort;
    }

    public String getAlterVon() {
        return alterVon;
    }

    public void setAlterVon(String alterVon) {
        this.alterVon = alterVon;
    }

    public String getAlterBis() {
        return alterBis;
    }

    public void setAlterBis(String alterBis) {
        this.alterBis = alterBis;
    }

    public IcaMitgliedStatus getMglStatusId() {
        return mglStatusId;
    }

    public void setMglStatusId(IcaMitgliedStatus mglStatusId) {
        this.mglStatusId = mglStatusId;
    }

    public String getFunktion() {
        return funktion;
    }

    public void setFunktion(String funktion) {
        this.funktion = funktion;
    }

    public List<Integer> getMglTypeId() {
        return mglTypeId;
    }

    public void setMglTypeId(List<Integer> mglTypeId) {
        this.mglTypeId = mglTypeId;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getBausteinIncludedId() {
        return bausteinIncludedId;
    }

    public void setBausteinIncludedId(Integer bausteinIncludedId) {
        this.bausteinIncludedId = bausteinIncludedId;
    }

    public boolean isZeitschriftenversand() {
        return zeitschriftenversand;
    }

    public void setZeitschriftenversand(boolean zeitschriftenversand) {
        this.zeitschriftenversand = zeitschriftenversand;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public List<Integer> getTaetigkeitId() {
        return taetigkeitId;
    }

    public void setTaetigkeitId(List<Integer> taetigkeitId) {
        this.taetigkeitId = taetigkeitId;
    }

    public Integer getUntergliederungId() {
        return untergliederungId;
    }

    public void setUntergliederungId(Integer untergliederungId) {
        this.untergliederungId = untergliederungId;
    }

    public boolean isMitAllenTaetigkeiten() {
        return mitAllenTaetigkeiten;
    }

    public void setMitAllenTaetigkeiten(boolean mitAllenTaetigkeiten) {
        this.mitAllenTaetigkeiten = mitAllenTaetigkeiten;
    }

    public boolean isWithEndedTaetigkeiten() {
        return withEndedTaetigkeiten;
    }

    public void setWithEndedTaetigkeiten(boolean withEndedTaetigkeiten) {
        this.withEndedTaetigkeiten = withEndedTaetigkeiten;
    }

    public Integer getEbeneId() {
        return ebeneId;
    }

    public void setEbeneId(Integer ebeneId) {
        this.ebeneId = ebeneId;
    }

    public String getGrpNummer() {
        return grpNummer;
    }

    public void setGrpNummer(String grpNummer) {
        this.grpNummer = grpNummer;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public String getGruppierung1Id() {
        return gruppierung1Id;
    }

    public void setGruppierung1Id(String gruppierung1Id) {
        this.gruppierung1Id = gruppierung1Id;
    }

    public String getGruppierung2Id() {
        return gruppierung2Id;
    }

    public void setGruppierung2Id(String gruppierung2Id) {
        this.gruppierung2Id = gruppierung2Id;
    }

    public String getGruppierung3Id() {
        return gruppierung3Id;
    }

    public void setGruppierung3Id(String gruppierung3Id) {
        this.gruppierung3Id = gruppierung3Id;
    }

    public String getGruppierung4Id() {
        return gruppierung4Id;
    }

    public void setGruppierung4Id(String gruppierung4Id) {
        this.gruppierung4Id = gruppierung4Id;
    }

    public String getGruppierung5Id() {
        return gruppierung5Id;
    }

    public void setGruppierung5Id(String gruppierung5Id) {
        this.gruppierung5Id = gruppierung5Id;
    }

    public String getGruppierung6Id() {
        return gruppierung6Id;
    }

    public void setGruppierung6Id(String gruppierung6Id) {
        this.gruppierung6Id = gruppierung6Id;
    }

    public boolean isInGrp() {
        return inGrp;
    }

    public void setInGrp(boolean inGrp) {
        this.inGrp = inGrp;
    }

    public boolean isUnterhalbGrp() {
        return unterhalbGrp;
    }

    public void setUnterhalbGrp(boolean unterhalbGrp) {
        this.unterhalbGrp = unterhalbGrp;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
