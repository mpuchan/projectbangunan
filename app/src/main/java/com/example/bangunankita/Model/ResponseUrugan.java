package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUrugan {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("perhitunganurugan")
    @Expose
    private List<Perhitunganurugan1> perhitunganurugan1 = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Perhitunganurugan1> getPerhitunganurugan() {
        return perhitunganurugan1;
    }

    public void setPerhitunganurugan(List<Perhitunganurugan1> perhitunganurugan) {
        this.perhitunganurugan1 = perhitunganurugan;
    }
}
