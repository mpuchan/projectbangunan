package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Perhitunganurugan1 {

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
        @SerializedName("panjang")
        @Expose
        private Double panjang;
        @SerializedName("lebar")
        @Expose
        private Double lebar;
        @SerializedName("tinggi")
        @Expose
        private Double tinggi;
        @SerializedName("volume")
        @Expose
        private Double volume;
        @SerializedName("volumejadi")
        @Expose
        private Double volumejadi;
        @SerializedName("nama_pasir")
        @Expose
        private String namaPasir;
        @SerializedName("Jumlahkeperluanpasir")
        @Expose
        private Double jumlahkeperluanpasir;
        @SerializedName("jumlahdalamtruk")
        @Expose
        private Double jumlahdalamtruk;
        @SerializedName("hargapasir")
        @Expose
        private Double hargapasir;
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

        public Double getTinggi() {
            return tinggi;
        }

        public void setTinggi(Double tinggi) {
            this.tinggi = tinggi;
        }

        public Double getVolume() {
            return volume;
        }

        public void setVolume(Double volume) {
            this.volume = volume;
        }

        public Double getVolumejadi() {
            return volumejadi;
        }

        public void setVolumejadi(Double volumejadi) {
            this.volumejadi = volumejadi;
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

        public Double getJumlahdalamtruk() {
            return jumlahdalamtruk;
        }

        public void setJumlahdalamtruk(Double jumlahdalamtruk) {
            this.jumlahdalamtruk = jumlahdalamtruk;
        }

        public Double getHargapasir() {
            return hargapasir;
        }

        public void setHargapasir(Double hargapasir) {
            this.hargapasir = hargapasir;
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
