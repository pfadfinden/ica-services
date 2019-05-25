package de.pfadfinden.ica.model;

import java.util.List;

/**
 * Suchparameter für Suche nach Mitgliedern.
 *
 * @see <a href="https://meinbdp.de/x/5IQtB">MeinBdP: Suche verwenden</a>
 */
public class IcaSearchedValues {

    private String vorname = "";
    private String nachname = "";
    private String spitzname = "";
    private String mitgliedsNummber = ""; // Fehler bewusst!
    private String mglWohnort = "";
    private String alterVon = "";
    private String alterBis = "";
    private IcaSearchedValuesStatus mglStatusId = null;
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

    public IcaSearchedValues() {
    }

    private IcaSearchedValues(Builder builder) {
        setVorname(builder.vorname);
        setNachname(builder.nachname);
        setSpitzname(builder.spitzname);
        setMitgliedsNummber(builder.mitgliedsNummber);
        setMglWohnort(builder.mglWohnort);
        setAlterVon(builder.alterVon);
        setAlterBis(builder.alterBis);
        setMglStatusId(builder.mglStatusId);
        setFunktion(builder.funktion);
        setMglTypeId(builder.mglTypeId);
        setOrganisation(builder.organisation);
        setTagId(builder.tagId);
        setBausteinIncludedId(builder.bausteinIncludedId);
        setZeitschriftenversand(builder.zeitschriftenversand);
        setSearchName(builder.searchName);
        setTaetigkeitId(builder.taetigkeitId);
        setUntergliederungId(builder.untergliederungId);
        setMitAllenTaetigkeiten(builder.mitAllenTaetigkeiten);
        setWithEndedTaetigkeiten(builder.withEndedTaetigkeiten);
        setEbeneId(builder.ebeneId);
        setGrpNummer(builder.grpNummer);
        setGrpName(builder.grpName);
        setGruppierung1Id(builder.gruppierung1Id);
        setGruppierung2Id(builder.gruppierung2Id);
        setGruppierung3Id(builder.gruppierung3Id);
        setGruppierung4Id(builder.gruppierung4Id);
        setGruppierung5Id(builder.gruppierung5Id);
        setGruppierung6Id(builder.gruppierung6Id);
        setInGrp(builder.inGrp);
        setUnterhalbGrp(builder.unterhalbGrp);
        setPrivacy(builder.privacy);
        setSearchType(builder.searchType);
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

    public IcaSearchedValuesStatus getMglStatusId() {
        return mglStatusId;
    }

    public void setMglStatusId(IcaSearchedValuesStatus mglStatusId) {
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

    public static final class Builder {
        private String vorname;
        private String nachname;
        private String spitzname;
        private String mitgliedsNummber;
        private String mglWohnort;
        private String alterVon;
        private String alterBis;
        private IcaSearchedValuesStatus mglStatusId;
        private String funktion;
        private List<Integer> mglTypeId;
        private String organisation;
        private Integer tagId;
        private Integer bausteinIncludedId;
        private boolean zeitschriftenversand;
        private String searchName;
        private List<Integer> taetigkeitId;
        private Integer untergliederungId;
        private boolean mitAllenTaetigkeiten;
        private boolean withEndedTaetigkeiten;
        private Integer ebeneId;
        private String grpNummer;
        private String grpName;
        private String gruppierung1Id;
        private String gruppierung2Id;
        private String gruppierung3Id;
        private String gruppierung4Id;
        private String gruppierung5Id;
        private String gruppierung6Id;
        private boolean inGrp;
        private boolean unterhalbGrp;
        private String privacy;
        private String searchType;

        public Builder() {
        }

        public Builder withVorname(String val) {
            vorname = val;
            return this;
        }

        public Builder withNachname(String val) {
            nachname = val;
            return this;
        }

        public Builder withSpitzname(String val) {
            spitzname = val;
            return this;
        }

        public Builder withMitgliedsNummber(String val) {
            mitgliedsNummber = val;
            return this;
        }

        public Builder withMglWohnort(String val) {
            mglWohnort = val;
            return this;
        }

        public Builder withAlterVon(String val) {
            alterVon = val;
            return this;
        }

        public Builder withAlterBis(String val) {
            alterBis = val;
            return this;
        }

        public Builder withMglStatusId(IcaSearchedValuesStatus val) {
            mglStatusId = val;
            return this;
        }

        public Builder withFunktion(String val) {
            funktion = val;
            return this;
        }

        public Builder withMglTypeId(List<Integer> val) {
            mglTypeId = val;
            return this;
        }

        public Builder withOrganisation(String val) {
            organisation = val;
            return this;
        }

        public Builder withTagId(Integer val) {
            tagId = val;
            return this;
        }

        public Builder withBausteinIncludedId(Integer val) {
            bausteinIncludedId = val;
            return this;
        }

        public Builder withZeitschriftenversand(boolean val) {
            zeitschriftenversand = val;
            return this;
        }

        public Builder withSearchName(String val) {
            searchName = val;
            return this;
        }

        public Builder withTaetigkeitId(List<Integer> val) {
            taetigkeitId = val;
            return this;
        }

        public Builder withUntergliederungId(Integer val) {
            untergliederungId = val;
            return this;
        }

        public Builder withMitAllenTaetigkeiten(boolean val) {
            mitAllenTaetigkeiten = val;
            return this;
        }

        public Builder withWithEndedTaetigkeiten(boolean val) {
            withEndedTaetigkeiten = val;
            return this;
        }

        public Builder withEbeneId(Integer val) {
            ebeneId = val;
            return this;
        }

        public Builder withGrpNummer(String val) {
            grpNummer = val;
            return this;
        }

        public Builder withGrpName(String val) {
            grpName = val;
            return this;
        }

        public Builder withGruppierung1Id(String val) {
            gruppierung1Id = val;
            return this;
        }

        public Builder withGruppierung2Id(String val) {
            gruppierung2Id = val;
            return this;
        }

        public Builder withGruppierung3Id(String val) {
            gruppierung3Id = val;
            return this;
        }

        public Builder withGruppierung4Id(String val) {
            gruppierung4Id = val;
            return this;
        }

        public Builder withGruppierung5Id(String val) {
            gruppierung5Id = val;
            return this;
        }

        public Builder withGruppierung6Id(String val) {
            gruppierung6Id = val;
            return this;
        }

        public Builder withInGrp(boolean val) {
            inGrp = val;
            return this;
        }

        public Builder withUnterhalbGrp(boolean val) {
            unterhalbGrp = val;
            return this;
        }

        public Builder withPrivacy(String val) {
            privacy = val;
            return this;
        }

        public Builder withSearchType(String val) {
            searchType = val;
            return this;
        }

        public IcaSearchedValues build() {
            return new IcaSearchedValues(this);
        }
    }

    @Override
    public String toString() {
        return "IcaSearchedValues{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", spitzname='" + spitzname + '\'' +
                ", mitgliedsNummber='" + mitgliedsNummber + '\'' +
                ", mglWohnort='" + mglWohnort + '\'' +
                ", alterVon='" + alterVon + '\'' +
                ", alterBis='" + alterBis + '\'' +
                ", mglStatusId=" + mglStatusId +
                ", funktion='" + funktion + '\'' +
                ", mglTypeId=" + mglTypeId +
                ", organisation='" + organisation + '\'' +
                ", tagId=" + tagId +
                ", bausteinIncludedId=" + bausteinIncludedId +
                ", zeitschriftenversand=" + zeitschriftenversand +
                ", searchName='" + searchName + '\'' +
                ", taetigkeitId=" + taetigkeitId +
                ", untergliederungId=" + untergliederungId +
                ", mitAllenTaetigkeiten=" + mitAllenTaetigkeiten +
                ", withEndedTaetigkeiten=" + withEndedTaetigkeiten +
                ", ebeneId=" + ebeneId +
                ", grpNummer='" + grpNummer + '\'' +
                ", grpName='" + grpName + '\'' +
                ", gruppierung1Id='" + gruppierung1Id + '\'' +
                ", gruppierung2Id='" + gruppierung2Id + '\'' +
                ", gruppierung3Id='" + gruppierung3Id + '\'' +
                ", gruppierung4Id='" + gruppierung4Id + '\'' +
                ", gruppierung5Id='" + gruppierung5Id + '\'' +
                ", gruppierung6Id='" + gruppierung6Id + '\'' +
                ", inGrp=" + inGrp +
                ", unterhalbGrp=" + unterhalbGrp +
                ", privacy='" + privacy + '\'' +
                ", searchType='" + searchType + '\'' +
                '}';
    }
}
