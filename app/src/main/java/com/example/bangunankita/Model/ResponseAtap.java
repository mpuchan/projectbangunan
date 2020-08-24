package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAtap {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("perhitunganatap")
    @Expose
    private List<Perhitunganatap1> perhitunganatap = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Perhitunganatap1> getPerhitunganatap() {
        return perhitunganatap;
    }

    public void setPerhitunganatap(List<Perhitunganatap1> perhitunganatap) {
        this.perhitunganatap = perhitunganatap;
    }
}
