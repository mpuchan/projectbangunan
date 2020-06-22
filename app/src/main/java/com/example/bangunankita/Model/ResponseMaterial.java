package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMaterial {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("materials")
    @Expose
    private List<Material> materials = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

}

