package de.pfadfinden.ica.model;

import com.google.gson.annotations.SerializedName;

/**
 * Status einer Gruppierung in {@link IcaGruppierungDetail#getStatus()}
 */
public enum IcaGruppierungStatus {
    @SerializedName("Aktiv") AKTIV,
    @SerializedName("Deaktiviert") DEAKTIVIERT
}
