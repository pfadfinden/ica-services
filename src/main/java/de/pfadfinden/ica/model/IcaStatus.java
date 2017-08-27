package de.pfadfinden.ica.model;

import com.google.gson.annotations.SerializedName;

public enum IcaStatus {
    @SerializedName("Aktiv") AKTIV,
    @SerializedName("Deaktiviert") DEAKTIVIERT
}
