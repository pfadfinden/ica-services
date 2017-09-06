package de.pfadfinden.ica.model;

import com.google.gson.annotations.SerializedName;

public enum IcaEbene {
    @SerializedName("Bund") BUND,
    @SerializedName("Land") LAND,
    @SerializedName("Bezirk") BEZIRK,
    @SerializedName("Stamm") STAMM,
    @SerializedName("Aufbaugruppe") AUFBAUGRUPPE
}
