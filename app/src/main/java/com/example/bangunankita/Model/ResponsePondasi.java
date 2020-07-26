package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePondasi {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("perhitunganpondasi")
    @Expose
    private List<Perhitunganpondasi1> perhitunganpondasi = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Perhitunganpondasi1> getPerhitunganpondasi() {
        return perhitunganpondasi;
    }

    public void setPerhitunganpondasi(List<Perhitunganpondasi1> perhitunganpondasi) {
        this.perhitunganpondasi = perhitunganpondasi;
    }
}
