package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Proyek_model {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("PengembangId")
    @Expose
    private Integer pengembangId;
    @SerializedName("nama_proyek")
    @Expose
    private String namaProyek;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("Pengembang")
    @Expose
    private Pengembang_model pengembang;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPengembangId() {
        return pengembangId;
    }

    public void setPengembangId(Integer pengembangId) {
        this.pengembangId = pengembangId;
    }

    public String getNamaProyek() {
        return namaProyek;
    }

    public void setNamaProyek(String namaProyek) {
        this.namaProyek = namaProyek;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Pengembang_model getPengembang() {
        return pengembang;
    }

    public void setPengembang(Pengembang_model pengembang) {
        this.pengembang = pengembang;
    }

}