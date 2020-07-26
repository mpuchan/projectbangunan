package com.example.bangunankita.Model;

import com.example.bangunankita.Perhitunganpengecatan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePengecatan {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("perhitunganpengecatan")
    @Expose
    private List<Perhitunganpengecatan1> perhitunganpengecatan1= null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Perhitunganpengecatan1> getPerhitunganpengecatan() {
        return perhitunganpengecatan1;
    }

    public void setPerhitunganpengecatan(List<Perhitunganpengecatan1> perhitunganpengecatan) {
        this.perhitunganpengecatan1 = perhitunganpengecatan1;
    }
}
