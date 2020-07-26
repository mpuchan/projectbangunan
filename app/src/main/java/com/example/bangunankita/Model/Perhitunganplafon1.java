package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Perhitunganplafon1 {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ProyekId")
    @Expose
    private Integer proyekId;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("panjang")
    @Expose
    private Double panjang;
    @SerializedName("lebar")
    @Expose
    private Double lebar;
    @SerializedName("luas")
    @Expose
    private Double luas;
    @SerializedName("namatriplek")
    @Expose
    private String namatriplek;
    @SerializedName("namapaku")
    @Expose
    private String namapaku;
    @SerializedName("namareng")
    @Expose
    private String namareng;
    @SerializedName("hargatriplek")
    @Expose
    private Double hargatriplek;
    @SerializedName("hargapaku")
    @Expose
    private Double hargapaku;
    @SerializedName("hargareng")
    @Expose
    private Double hargareng;
    @SerializedName("jumlahtriplek")
    @Expose
    private Double jumlahtriplek;
    @SerializedName("jumlahtripleklembar")
    @Expose
    private Double jumlahtripleklembar;
    @SerializedName("jumlahreng")
    @Expose
    private Double jumlahreng;
    @SerializedName("jumlahrengbatang")
    @Expose
    private Double jumlahrengbatang;
    @SerializedName("jumlahpaku")
    @Expose
    private Double jumlahpaku;
    @SerializedName("hargatotaltriplek")
    @Expose
    private Double hargatotaltriplek;
    @SerializedName("hargatotalpaku")
    @Expose
    private Double hargatotalpaku;
    @SerializedName("hargatotalreng")
    @Expose
    private Double hargatotalreng;
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

    public Double getPanjang() {
        return panjang;
    }

    public void setPanjang(Double panjang) {
        this.panjang = panjang;
    }

    public Double getLebar() {
        return lebar;
    }

    public void setLebar(Double lebar) {
        this.lebar = lebar;
    }

    public Double getLuas() {
        return luas;
    }

    public void setLuas(Double luas) {
        this.luas = luas;
    }

    public String getNamatriplek() {
        return namatriplek;
    }

    public void setNamatriplek(String namatriplek) {
        this.namatriplek = namatriplek;
    }

    public String getNamapaku() {
        return namapaku;
    }

    public void setNamapaku(String namapaku) {
        this.namapaku = namapaku;
    }

    public String getNamareng() {
        return namareng;
    }

    public void setNamareng(String namareng) {
        this.namareng = namareng;
    }

    public Double getHargatriplek() {
        return hargatriplek;
    }

    public void setHargatriplek(Double hargatriplek) {
        this.hargatriplek = hargatriplek;
    }

    public Double getHargapaku() {
        return hargapaku;
    }

    public void setHargapaku(Double hargapaku) {
        this.hargapaku = hargapaku;
    }

    public Double getHargareng() {
        return hargareng;
    }

    public void setHargareng(Double hargareng) {
        this.hargareng = hargareng;
    }

    public Double getJumlahtriplek() {
        return jumlahtriplek;
    }

    public void setJumlahtriplek(Double jumlahtriplek) {
        this.jumlahtriplek = jumlahtriplek;
    }
    public Double getJumlahpaku() {
        return jumlahpaku;
    }

    public void setJumlahpaku(Double jumlahpaku) {
        this.jumlahpaku = jumlahpaku;
    }
    public Double getJumlahtripleklembar() {
        return jumlahtripleklembar;
    }

    public void setJumlahtripleklembar(Double jumlahtripleklembar) {
        this.jumlahtripleklembar = jumlahtripleklembar;
    }

    public Double getJumlahreng() {
        return jumlahreng;
    }

    public void setJumlahreng(Double jumlahreng) {
        this.jumlahreng = jumlahreng;
    }

    public Double getJumlahrengbatang() {
        return jumlahrengbatang;
    }

    public void setJumlahrengbatang(Double jumlahrengbatang) {
        this.jumlahrengbatang = jumlahrengbatang;
    }

    public Double getHargatotaltriplek() {
        return hargatotaltriplek;
    }

    public void setHargatotaltriplek(Double hargatotaltriplek) {
        this.hargatotaltriplek = hargatotaltriplek;
    }

    public Double getHargatotalpaku() {
        return hargatotalpaku;
    }

    public void setHargatotalpaku(Double hargatotalpaku) {
        this.hargatotalpaku = hargatotalpaku;
    }

    public Double getHargatotalreng() {
        return hargatotalreng;
    }

    public void setHargatotalreng(Double hargatotalreng) {
        this.hargatotalreng = hargatotalreng;
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
