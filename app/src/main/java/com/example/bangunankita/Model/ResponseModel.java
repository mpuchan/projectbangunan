package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel extends Proyek_model {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("proyek")
    @Expose
    private List<Proyek_model> proyek = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<Proyek_model> getProyek() {
        return proyek;
    }

    public void setProyek(List<Proyek_model> proyek) {
        this.proyek = proyek;
    }

}





