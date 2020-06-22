package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Material {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("panjang")
    @Expose
    private Integer panjang;
    @SerializedName("lebar")
    @Expose
    private Integer lebar;
    @SerializedName("tinggi")
    @Expose
    private Integer tinggi;
    @SerializedName("tebal")
    @Expose
    private Object tebal;
    @SerializedName("berat")
    @Expose
    private Object berat;
    @SerializedName("jumlah")
    @Expose
    private Object jumlah;
    @SerializedName("SatuanId")
    @Expose
    private Integer satuanId;
    @SerializedName("JenisId")
    @Expose
    private Integer jenisId;
    @SerializedName("harga")
    @Expose
    private Integer harga;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getPanjang() {
        return panjang;
    }

    public void setPanjang(Integer panjang) {
        this.panjang = panjang;
    }

    public Integer getLebar() {
        return lebar;
    }

    public void setLebar(Integer lebar) {
        this.lebar = lebar;
    }

    public Integer getTinggi() {
        return tinggi;
    }

    public void setTinggi(Integer tinggi) {
        this.tinggi = tinggi;
    }

    public Object getTebal() {
        return tebal;
    }

    public void setTebal(Object tebal) {
        this.tebal = tebal;
    }

    public Object getBerat() {
        return berat;
    }

    public void setBerat(Object berat) {
        this.berat = berat;
    }

    public Object getJumlah() {
        return jumlah;
    }

    public void setJumlah(Object jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getSatuanId() {
        return satuanId;
    }

    public void setSatuanId(Integer satuanId) {
        this.satuanId = satuanId;
    }

    public Integer getJenisId() {
        return jenisId;
    }

    public void setJenisId(Integer jenisId) {
        this.jenisId = jenisId;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
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
