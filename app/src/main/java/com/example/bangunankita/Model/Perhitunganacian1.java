package com.example.bangunankita.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Perhitunganacian1 {

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
        @SerializedName("luas")
        @Expose
        private Double luas;
        @SerializedName("nama_semen")
        @Expose
        private String namaSemen;
        @SerializedName("Jumlahkeperluansemen")
        @Expose
        private Double jumlahkeperluansemen;
        @SerializedName("jumlahdalamsak")
        @Expose
        private Double jumlahdalamsak;
        @SerializedName("metode")
        @Expose
        private String metode;
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

        public Double getLuas() {
            return luas;
        }

        public void setLuas(Double luas) {
            this.luas = luas;
        }

        public String getNamaSemen() {
            return namaSemen;
        }

        public void setNamaSemen(String namaSemen) {
            this.namaSemen = namaSemen;
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

