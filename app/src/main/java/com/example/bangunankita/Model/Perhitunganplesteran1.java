package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Perhitunganplesteran1 {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ProyekId")
    @Expose
    private Integer proyekId;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("jenis_pengerjaan")
    @Expose
    private String jenisPengerjaan;
    @SerializedName("panjangdin")
    @Expose
    private Double panjangdin;
    @SerializedName("tinggidin")
    @Expose
    private Double tinggidin;
    @SerializedName("tebal")
    @Expose
    private Double tebal;
    @SerializedName("sisi")
    @Expose
    private Double sisi;
    @SerializedName("volume")
    @Expose
    private Double volume;
    @SerializedName("nama_semen")
    @Expose
    private String namaSemen;
    @SerializedName("nama_pasir")
    @Expose
    private String namaPasir;
    @SerializedName("jumlahkeperluanpasir")
    @Expose
    private Double jumlahkeperluanpasir;
    @SerializedName("Jumlahkeperluansemen")
    @Expose
    private Double jumlahkeperluansemen;
    @SerializedName("jumlahdalamsak")
    @Expose
    private Double jumlahdalamsak;
    @SerializedName("metode")
    @Expose
    private String metode;
    @SerializedName("hargapasir")
    @Expose
    private Double hargapasir;
    @SerializedName("hargapasirtotal")
    @Expose
    private Double hargapasirtotal;
    @SerializedName("hargasemen")
    @Expose
    private Double hargasemen;
    @SerializedName("hargasementotal")
    @Expose
    private Double hargasementotal;
    @SerializedName("hargatotal")
    @Expose
    private Double hargatotal;
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

    public Integer getProyekId() {
        return proyekId;
    }

    public void setProyekId(Integer proyekId) {
        this.proyekId = proyekId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisPengerjaan() {
        return jenisPengerjaan;
    }

    public void setJenisPengerjaan(String jenisPengerjaan) {
        this.jenisPengerjaan = jenisPengerjaan;
    }

    public Double getPanjangdin() {
        return panjangdin;
    }

    public void setPanjangdin(Double panjangdin) {
        this.panjangdin = panjangdin;
    }

    public Double getTinggidin() {
        return tinggidin;
    }

    public void setTinggidin(Double tinggidin) {
        this.tinggidin = tinggidin;
    }

    public Double getTebal() {
        return tebal;
    }

    public void setTebal(Double tebal) {
        this.tebal = tebal;
    }

    public Double getSisi() {
        return sisi;
    }

    public void setSisi(Double sisi) {
        this.sisi = sisi;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getNamaSemen() {
        return namaSemen;
    }

    public void setNamaSemen(String namaSemen) {
        this.namaSemen = namaSemen;
    }

    public String getNamaPasir() {
        return namaPasir;
    }

    public void setNamaPasir(String namaPasir) {
        this.namaPasir = namaPasir;
    }

    public Double getJumlahkeperluanpasir() {
        return jumlahkeperluanpasir;
    }

    public void setJumlahkeperluanpasir(Double jumlahkeperluanpasir) {
        this.jumlahkeperluanpasir = jumlahkeperluanpasir;
    }

    public Double getJumlahkeperluansemen() {
        return jumlahkeperluansemen;
    }

    public void setJumlahkeperluansemen(Double jumlahkeperluansemen) {
        this.jumlahkeperluansemen = jumlahkeperluansemen;
    }

    public Double getJumlahdalamsak() {
        return jumlahdalamsak;
    }

    public void setJumlahdalamsak(Double jumlahdalamsak) {
        this.jumlahdalamsak = jumlahdalamsak;
    }

    public String getMetode() {
        return metode;
    }

    public void setMetode(String metode) {
        this.metode = metode;
    }

    public Double getHargapasir() {
        return hargapasir;
    }

    public void setHargapasir(Double hargapasir) {
        this.hargapasir = hargapasir;
    }


    public Double getHargapasirtotal() {
        return hargapasirtotal;
    }

    public void setHargapasirtotal(Double hargapasirtotal) {
        this.hargapasirtotal = hargapasirtotal;
    }

    public Double getHargasementotal() {
        return hargasementotal;
    }

    public void setHargasementotal(Double hargasementotal) {
        this.hargasementotal = hargasementotal;
    }

    public Double getHargasemen() {
        return hargasemen;
    }

    public void setHargasemen(Double hargasemen) {
        this.hargasemen = hargasemen;
    }

    public Double getHargatotal() {
        return hargatotal;
    }

    public void setHargatotal(Double hargatotal) {
        this.hargatotal = hargatotal;
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
