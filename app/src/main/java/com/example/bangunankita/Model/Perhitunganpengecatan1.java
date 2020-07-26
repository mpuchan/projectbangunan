package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Perhitunganpengecatan1 {
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
    @SerializedName("sisi")
    @Expose
    private Double sisi;
    @SerializedName("luas_pengecatan")
    @Expose
    private Double luasPengecatan;
    @SerializedName("nama_cat")
    @Expose
    private String namaCat;
    @SerializedName("nama_plamur")
    @Expose
    private String namaPlamur;
    @SerializedName("jumlahkeperluancat")
    @Expose
    private Double jumlahkeperluancat;
    @SerializedName("jumlahkeperluancatkaleng")
    @Expose
    private Double jumlahkeperluancatkaleng;
    @SerializedName("jumlahkeperluanplamur")
    @Expose
    private Double jumlahkeperluanplamur;
    @SerializedName("jumlahkeperluanplamursak")
    @Expose
    private Double jumlahkeperluanplamursak;
    @SerializedName("hargacat")
    @Expose
    private Double hargacat;
    @SerializedName("hargacattotal")
    @Expose
    private Double hargacattotal;
    @SerializedName("hargaplamur")
    @Expose
    private Double hargaplamur;
    @SerializedName("hargaplamurtotal")
    @Expose
    private Double hargaplamurtotal;
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

    public Double getSisi() {
        return sisi;
    }

    public void setSisi(Double sisi) {
        this.sisi = sisi;
    }

    public Double getLuasPengecatan() {
        return luasPengecatan;
    }

    public void setLuasPengecatan(Double luasPengecatan) {
        this.luasPengecatan = luasPengecatan;
    }

    public String getNamaCat() {
        return namaCat;
    }

    public void setNamaCat(String namaCat) {
        this.namaCat = namaCat;
    }

    public String getNamaPlamur() {
        return namaPlamur;
    }

    public void setNamaPlamur(String namaPlamur) {
        this.namaPlamur = namaPlamur;
    }

    public Double getJumlahkeperluancat() {
        return jumlahkeperluancat;
    }

    public void setJumlahkeperluancat(Double jumlahkeperluancat) {
        this.jumlahkeperluancat = jumlahkeperluancat;
    }

    public Double getJumlahkeperluancatkaleng() {
        return jumlahkeperluancatkaleng;
    }

    public void setJumlahkeperluancatkaleng(Double jumlahkeperluancatkaleng) {
        this.jumlahkeperluancatkaleng = jumlahkeperluancatkaleng;
    }

    public Double getJumlahkeperluanplamur() {
        return jumlahkeperluanplamur;
    }

    public void setJumlahkeperluanplamur(Double jumlahkeperluanplamur) {
        this.jumlahkeperluanplamur = jumlahkeperluanplamur;
    }

    public Double getJumlahkeperluanplamursak() {
        return jumlahkeperluanplamursak;
    }

    public void setJumlahkeperluanplamursak(Double jumlahkeperluanplamursak) {
        this.jumlahkeperluanplamursak = jumlahkeperluanplamursak;
    }

    public Double getHargacat() {
        return hargacat;
    }

    public void setHargacat(Double hargacat) {
        this.hargacat = hargacat;
    }

    public Double getHargacattotal() {
        return hargacattotal;
    }

    public void setHargacattotal(Double hargacattotal) {
        this.hargacattotal = hargacattotal;
    }

    public Double getHargaplamur() {
        return hargaplamur;
    }

    public void setHargaplamur(Double hargaplamur) {
        this.hargaplamur = hargaplamur;
    }

    public Double getHargaplamurtotal() {
        return hargaplamurtotal;
    }

    public void setHargaplamurtotal(Double hargaplamurtotal) {
        this.hargaplamurtotal = hargaplamurtotal;
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
