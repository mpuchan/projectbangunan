package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Proyek_model{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("PengembangId")
    @Expose
    private Integer pengembangId;
    @SerializedName("nama_proyek")
    @Expose
    private String namaProyek;
    @SerializedName("luas_tanah")
    @Expose
    private Double luas_tanah;
    @SerializedName("luas_bangunan")
    @Expose
    private Double luas_bangunan;
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

    public Double getLuas_tanah() {
        return luas_tanah;
    }

    public void setLuas_tanah(Double luas_tanah) {
        this.luas_tanah = luas_tanah;
    }
    public Double getLuas_bangunan() {
        return luas_bangunan;
    }

    public void setLuas_bangunan(Double luas_bangunan) {
        this.luas_bangunan= luas_bangunan;
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

}