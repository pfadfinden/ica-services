package de.pfadfinden.ica.model;

import com.google.gson.annotations.SerializedName;

public enum IcaMitgliedTyp {

    @SerializedName("Mitglied")
    MITGLIED,

    @SerializedName("Nicht_Mitglied")
    NICHT_MITGLIED,

    @SerializedName("Schnupper_Mitglied")
    SCHNUPPER_MITGLIED
}