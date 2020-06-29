package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePlesteran {
    @SerializedName("message")
@Expose
private String message;
    @SerializedName("perhitunganplesteran")
    @Expose
    private List<Perhitunganplesteran1> perhitunganplesteran1 = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Perhitunganplesteran1> getPerhitunganplesteran() {
        return perhitunganplesteran1;
    }

    public void setPerhitunganplesteran(List<Perhitunganplesteran1> perhitunganplesteran) {
        this.perhitunganplesteran1 = perhitunganplesteran;
    }

}
