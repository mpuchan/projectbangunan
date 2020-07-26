package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLantai {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("perhitunganlantai")
    @Expose
    private List<Perhitunganlantai1> perhitunganlantai1 = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Perhitunganlantai1> getPerhitunganlantai() {
        return perhitunganlantai1;
    }

    public void setPerhitunganlantai(List<Perhitunganlantai1> perhitunganlantai) {
        this.perhitunganlantai1 = perhitunganlantai;
    }


}
