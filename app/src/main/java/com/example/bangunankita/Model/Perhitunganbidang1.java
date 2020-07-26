package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Perhitunganbidang1 {
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
    @SerializedName("panjangbid")
    @Expose
    private Double panjangbid;
    @SerializedName("tinggibid")
    @Expose
    private Double tinggibid;
    @SerializedName("panjangpin")
    @Expose
    private Double panjangpin;
    @SerializedName("tinggipin")
    @Expose
    private Double tinggipin;
    @SerializedName("panjangjen")
    @Expose
    private Double panjangjen;
    @SerializedName("tinggijen")
    @Expose
    private Double tinggijen;
    @SerializedName("luas_bidang")
    @Expose
    private Double luasBidang;
    @SerializedName("nama_batako")
    @Expose
    private String namaBatako;
    @SerializedName("nama_semen")
    @Expose
    private String namaSemen;
    @SerializedName("nama_pasir")
    @Expose
    private String namaPasir;
    @SerializedName("jumlahkeperluanbatako")
    @Expose
    private Double jumlahkeperluanbatako;
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
    @SerializedName("hargabatako")
    @Expose
    private Double hargabatako;
    @SerializedName("hargabatakototal")
    @Expose
    private Double hargabatakototal;
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

    public Double getPanjangbid() {
        return panjangbid;
    }

    public void setPanjangbid(Double panjangbid) {
        this.panjangbid = panjangbid;
    }

    public Double getTinggibid() {
        return tinggibid;
    }

    public void setTinggibid(Double tinggibid) {
        this.tinggibid = tinggibid;
    }

    public Double getPanjangpin() {
        return panjangpin;
    }

    public void setPanjangpin(Double panjangpin) {
        this.panjangpin = panjangpin;
    }

    public Double getTinggipin() {
        return tinggipin;
    }

    public void setTinggipin(Double tinggipin) {
        this.tinggipin = tinggipin;
    }

    public Double getPanjangjen() {
        return panjangjen;
    }

    public void setPanjangjen(Double panjangjen) {
        this.panjangjen = panjangjen;
    }

    public Double getTinggijen() {
        return tinggijen;
    }

    public void setTinggijen(Double tinggijen) {
        this.tinggijen = tinggijen;
    }

    public Double getLuasBidang() {
        return luasBidang;
    }

    public void setLuasBidang(Double luasBidang) {
        this.luasBidang = luasBidang;
    }

    public String getNamaBatako() {
        return namaBatako;
    }

    public void setNamaBatako(String namaBatako) {
        this.namaBatako = namaBatako;
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

    public Double getJumlahkeperluanbatako() {
        return jumlahkeperluanbatako;
    }

    public void setJumlahkeperluanbatako(Double jumlahkeperluanbatako) {
        this.jumlahkeperluanbatako = jumlahkeperluanbatako;
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

    public Double getHargabatako() {
        return hargabatako;
    }

    public void setHargabatako(Double hargabatako) {
        this.hargabatako = hargabatako;
    }

    public Double getHargabatakototal() {
        return hargabatakototal;
    }

    public void setHargabatakototal(Double hargabatakototal) {
        this.hargabatakototal = hargabatakototal;
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

    public Double getHargasemen() {
        return hargasemen;
    }

    public void setHargasemen(Double hargasemen) {
        this.hargasemen = hargasemen;
    }
    public Double getHargasementotal() {
        return hargasementotal;
    }

    public void setHargasementotal(Double hargasementotal) {
        this.hargasementotal = hargasementotal;
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