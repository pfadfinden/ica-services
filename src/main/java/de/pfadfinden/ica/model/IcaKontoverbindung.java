package de.pfadfinden.ica.model;

public class IcaKontoverbindung {
    private int id;
    private String institut;
    private String bankleitzahl;
    private String kontonummer;
    private String iban;
    private String bic;
    private String kontoinhaber;
    private int mitgliedsNummer;
    private int zahlungsKonditionId;
    private String zahlungsKondition;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstitut() {
        return institut;
    }

    public void setInstitut(String institut) {
        this.institut = institut;
    }

    public String getBankleitzahl() {
        return bankleitzahl;
    }

    public void setBankleitzahl(String bankleitzahl) {
        this.bankleitzahl = bankleitzahl;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getKontoinhaber() {
        return kontoinhaber;
    }

    public void setKontoinhaber(String kontoinhaber) {
        this.kontoinhaber = kontoinhaber;
    }

    public int getMitgliedsNummer() {
        return mitgliedsNummer;
    }

    public void setMitgliedsNummer(int mitgliedsNummer) {
        this.mitgliedsNummer = mitgliedsNummer;
    }

    public int getZahlungsKonditionId() {
        return zahlungsKonditionId;
    }

    public void setZahlungsKonditionId(int zahlungsKonditionId) {
        this.zahlungsKonditionId = zahlungsKonditionId;
    }

    public String getZahlungsKondition() {
        return zahlungsKondition;
    }

    public void setZahlungsKondition(String zahlungsKondition) {
        this.zahlungsKondition = zahlungsKondition;
    }
}
