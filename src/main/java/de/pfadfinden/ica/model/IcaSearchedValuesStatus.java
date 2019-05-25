package de.pfadfinden.ica.model;

import com.google.gson.annotations.SerializedName;

/**
 * Status eines Mitglieds in {@link IcaSearchedValues#getMglStatusId()}
 */
public enum IcaSearchedValuesStatus {
    @SerializedName("AKTIV") AKTIV,
    @SerializedName("INAKTIV") INAKTIV,
    @SerializedName("WARTEND") WARTEND,
    @SerializedName("GELOESCHT_ALT") ARCHIVIERT
}
