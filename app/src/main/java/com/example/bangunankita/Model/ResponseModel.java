package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel extends Proyek_model {
    @SerializedName("proyek")
    @Expose
    private List<Proyek_model> proyek = null;

    public List<Proyek_model> getProyek() {
        return proyek;
    }

    public void setProyek(List<Proyek_model> proyek) {
        this.proyek = proyek;
    }

}



