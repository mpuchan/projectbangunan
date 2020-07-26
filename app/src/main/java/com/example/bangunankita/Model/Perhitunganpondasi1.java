package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Perhitunganpondasi1 {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ProyekId")
    @Expose
    private Integer proyekId;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("a")
    @Expose
    private Double a;
    @SerializedName("b")
    @Expose
    private Double b;
    @SerializedName("t")
    @Expose
    private Double t;
    @SerializedName("p")
    @Expose
    private Double p;
    @SerializedName("luas")
    @Expose
    private Double luas;
    @SerializedName("metode")
    @Expose
    private String metode;
    @SerializedName("namasemen")
    @Expose
    private String namasemen;
    @SerializedName("namapasir")
    @Expose
    private String namapasir;
    @SerializedName("namabatu")
    @Expose
    private String namabatu;
    @SerializedName("hargasemen")
    @Expose
    private Double hargasemen;
    @SerializedName("hargapasir")
    @Expose
    private Double hargapasir;
    @SerializedName("hargabatukali")
    @Expose
    private Double hargabatukali;
    @SerializedName("jumlahsemen")
    @Expose
    private Double jumlahsemen;
    @SerializedName("jumlahsemendalamsak")
    @Expose
    private Double jumlahsemendalamsak;
    @SerializedName("jumlahpasir")
    @Expose
    private Double jumlahpasir;
    @SerializedName("jumlahbatu")
    @Expose
    private Double jumlahbatu;
    @SerializedName("jumlahbatutruk")
    @Expose
    private Double jumlahbatutruk;
    @SerializedName("hargasementotal")
    @Expose
    private Double hargasementotal;
    @SerializedName("hargapasirtotal")
    @Expose
    private Double hargapasirtotal;
    @SerializedName("hargabatutotal")
    @Expose
    private Double hargabatutotal;
    @SerializedName("hargatotal")
    @Expose
    private Double hargatotal;
    @SerializedName("createdAt")
    @Expose
    private Object createdAt;
    @SerializedName("updatedAt")
    @Expose
    private Object updatedAt;

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

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getT() {
        return t;
    }

    public void setT(Double t) {
        this.t = t;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getLuas() {
        return luas;
    }

    public void setLuas(Double luas) {
        this.luas = luas;
    }

    public String getMetode() {
        return metode;
    }

    public void setMetode(String metode) {
        this.metode = metode;
    }

    public String getNamasemen() {
        return namasemen;
    }

    public void setNamasemen(String namasemen) {
        this.namasemen = namasemen;
    }

    public String getNamapasir() {
        return namapasir;
    }

    public void setNamapasir(String namapasir) {
        this.namapasir = namapasir;
    }

    public String getNamabatu() {
        return namabatu;
    }

    public void setNamabatu(String namabatu) {
        this.namabatu = namabatu;
    }

    public Double getHargasemen() {
        return hargasemen;
    }

    public void setHargasemen(Double hargasemen) {
        this.hargasemen = hargasemen;
    }

    public Double getHargapasir() {
        return hargapasir;
    }

    public void setHargapasir(Double hargapasir) {
        this.hargapasir = hargapasir;
    }

    public Double getHargabatukali() {
        return hargabatukali;
    }

    public void setHargabatukali(Double hargabatukali) {
        this.hargabatukali = hargabatukali;
    }

    public Double getJumlahsemen() {
        return jumlahsemen;
    }

    public void setJumlahsemen(Double jumlahsemen) {
        this.jumlahsemen = jumlahsemen;
    }

    public Double getJumlahsemendalamsak() {
        return jumlahsemendalamsak;
    }

    public void setJumlahsemendalamsak(Double jumlahsemendalamsak) {
        this.jumlahsemendalamsak = jumlahsemendalamsak;
    }

    public Double getJumlahpasir() {
        return jumlahpasir;
    }

    public void setJumlahpasir(Double jumlahpasir) {
        this.jumlahpasir = jumlahpasir;
    }

    public Double getJumlahbatu() {
        return jumlahbatu;
    }

    public void setJumlahbatu(Double jumlahbatu) {
        this.jumlahbatu = jumlahbatu;
    }

    public Double getJumlahbatutruk() {
        return jumlahbatutruk;
    }

    public void setJumlahbatutruk(Double jumlahbatutruk) {
        this.jumlahbatutruk = jumlahbatutruk;
    }

    public Double getHargasementotal() {
        return hargasementotal;
    }

    public void setHargasementotal(Double hargasementotal) {
        this.hargasementotal = hargasementotal;
    }

    public Double getHargapasirtotal() {
        return hargapasirtotal;
    }

    public void setHargapasirtotal(Double hargapasirtotal) {
        this.hargapasirtotal = hargapasirtotal;
    }

    public Double getHargabatutotal() {
        return hargabatutotal;
    }

    public void setHargabatutotal(Double hargabatutotal) {
        this.hargabatutotal = hargabatutotal;
    }

    public Double getHargatotal() {
        return hargatotal;
    }

    public void setHargatotal(Double hargatotal) {
        this.hargatotal = hargatotal;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }
}
