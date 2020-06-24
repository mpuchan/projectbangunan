package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePlesteran {
    @SerializedName("message")
@Expose
private String message;
    @SerializedName("perhitunganplesteran1")
    @Expose
    private List<Perhitunganplesteran1> perhitunganplesteran1 = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Perhitunganplesteran1> getPerhitunganplesteran1() {
        return perhitunganplesteran1;
    }

    public void setPerhitunganplesteran1(List<Perhitunganplesteran1> perhitunganplesteran) {
        this.perhitunganplesteran1 = perhitunganplesteran;
    }

}
