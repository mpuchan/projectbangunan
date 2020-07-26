package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Perhitunganlantai1 {
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
    @SerializedName("panjanglan")
    @Expose
    private Double panjanglan;
    @SerializedName("lebarlan")
    @Expose
    private Double lebarlan;
    @SerializedName("luas_lantai")
    @Expose
    private Double luasLantai;
    @SerializedName("nama_keramik")
    @Expose
    private String namaKeramik;
    @SerializedName("nama_semen")
    @Expose
    private String namaSemen;
    @SerializedName("nama_pasir")
    @Expose
    private String namaPasir;
    @SerializedName("nama_semennat")
    @Expose
    private String namaSemennat;
    @SerializedName("jumlahkeperluankeramik")
    @Expose
    private Double jumlahkeperluankeramik;
    @SerializedName("jumlahkeperluankeramikdus")
    @Expose
    private Double jumlahkeperluankeramikdus;
    @SerializedName("jumlahkeperluanpasir")
    @Expose
    private Double jumlahkeperluanpasir;
    @SerializedName("Jumlahkeperluansemen")
    @Expose
    private Double jumlahkeperluansemen;
    @SerializedName("jumlahkeperluannat")
    @Expose
    private Double jumlahkeperluannat;
    @SerializedName("jumlahdalamsak")
    @Expose
    private Double jumlahdalamsak;
    @SerializedName("metode")
    @Expose
    private String metode;
    @SerializedName("hargakeramik")
    @Expose
    private Double hargakeramik;
    @SerializedName("hargakeramiktotal")
    @Expose
    private Double hargakeramiktotal;
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
    @SerializedName("harganat")
    @Expose
    private Double harganat;
    @SerializedName("harganattotal")
    @Expose
    private Double harganattotal;
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

    public Double getPanjanglan() {
        return panjanglan;
    }

    public void setPanjanglan(Double panjanglan) {
        this.panjanglan = panjanglan;
    }

    public Double getLebarlan() {
        return lebarlan;
    }

    public void setLebarlan(Double lebarlan) {
        this.lebarlan = lebarlan;
    }

    public Double getLuasLantai() {
        return luasLantai;
    }

    public void setLuasLantai(Double luasLantai) {
        this.luasLantai = luasLantai;
    }

    public String getNamaKeramik() {
        return namaKeramik;
    }

    public void setNamaKeramik(String namaKeramik) {
        this.namaKeramik = namaKeramik;
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

    public String getNamaSemennat() {
        return namaSemennat;
    }

    public void setNamaSemennat(String namaSemennat) {
        this.namaSemennat = namaSemennat;
    }

    public Double getJumlahkeperluankeramik() {
        return jumlahkeperluankeramik;
    }

    public void setJumlahkeperluankeramik(Double jumlahkeperluankeramik) {
        this.jumlahkeperluankeramik = jumlahkeperluankeramik;
    }
    public Double getJumlahkeperluankeramikdus() {
        return jumlahkeperluankeramikdus;
    }

    public void setJumlahkeperluankeramikdus(Double jumlahkeperluankeramikdus) {
        this.jumlahkeperluankeramikdus = jumlahkeperluankeramikdus;
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

    public Double getJumlahkeperluannat() {
        return jumlahkeperluannat;
    }

    public void setJumlahkeperluannat(Double jumlahkeperluannat) {
        this.jumlahkeperluannat = jumlahkeperluannat;
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

    public Double getHargakeramik() {
        return hargakeramik;
    }

    public void setHargakeramik(Double hargakeramik) {
        this.hargakeramik = hargakeramik;
    }

    public Double getHargakeramiktotal() {
        return hargakeramiktotal;
    }

    public void setHargakeramiktotal(Double hargakeramiktotal) {
        this.hargakeramiktotal = hargakeramiktotal;
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

    public Double getHarganat() {
        return harganat;
    }

    public void setHarganat(Double harganat) {
        this.harganat = harganat;
    }

    public Double getHarganattotal() {
        return harganattotal;
    }

    public void setHarganattotal(Double harganattotal) {
        this.harganattotal = harganattotal;
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

