package de.pfadfinden.ica.model;

import com.google.gson.annotations.SerializedName;

/**
 * Status eines Mitglieds in {@link IcaMitglied#getStatus()}
 */
public enum IcaMitgliedStatus {
    @SerializedName("Aktiv") AKTIV,
    @SerializedName("Inaktiv") INAKTIV,
    @SerializedName("Wartend") WARTEND,
    @SerializedName("archiviert") ARCHIVIERT
}