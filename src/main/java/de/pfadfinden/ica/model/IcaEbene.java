package de.pfadfinden.ica.model;

import com.google.gson.annotations.SerializedName;

public enum IcaEbene {
    @SerializedName("Bund") BUND,
    @SerializedName("Landesverband") LAND,
    @SerializedName("Bezirk") BEZIRK,
    @SerializedName("Stamm") STAMM,
    @SerializedName("Aufbaugruppe") AUFBAUGRUPPE,
    @SerializedName("Landesunmittelbar") LANDESUNMITTELBAR,
    @SerializedName("Projektorganisation") PROJEKT
}
