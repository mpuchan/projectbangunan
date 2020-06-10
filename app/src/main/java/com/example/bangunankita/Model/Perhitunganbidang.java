package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Perhitunganbidang {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("ProyekId")
        @Expose
        private Integer proyekId;
        @SerializedName("BatakoId")
        @Expose
        private Integer batakoId;
        @SerializedName("SemenId")
        @Expose
        private Integer semenId;
        @SerializedName("PasirId")
        @Expose
        private Integer pasirId;
        @SerializedName("luas_bidang")
        @Expose
        private Integer luasBidang;
        @SerializedName("jumlahkeperluanbatako")
        @Expose
        private Integer jumlahkeperluanbatako;
        @SerializedName("jumlahkeperluanpasir")
        @Expose
        private Integer jumlahkeperluanpasir;
        @SerializedName("Jumlahkeperluansemen")
        @Expose
        private Object jumlahkeperluansemen;
        @SerializedName("metode")
        @Expose
        private String metode;
        @SerializedName("hargabatako")
        @Expose
        private Integer hargabatako;
        @SerializedName("hargapasir")
        @Expose
        private Integer hargapasir;
        @SerializedName("hargasemen")
        @Expose
        private Integer hargasemen;
        @SerializedName("hargatotal")
        @Expose
        private Integer hargatotal;
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

        public Integer getBatakoId() {
            return batakoId;
        }

        public void setBatakoId(Integer batakoId) {
            this.batakoId = batakoId;
        }

        public Integer getSemenId() {
            return semenId;
        }

        public void setSemenId(Integer semenId) {
            this.semenId = semenId;
        }

        public Integer getPasirId() {
            return pasirId;
        }

        public void setPasirId(Integer pasirId) {
            this.pasirId = pasirId;
        }

        public Integer getLuasBidang() {
            return luasBidang;
        }

        public void setLuasBidang(Integer luasBidang) {
            this.luasBidang = luasBidang;
        }

        public Integer getJumlahkeperluanbatako() {
            return jumlahkeperluanbatako;
        }

        public void setJumlahkeperluanbatako(Integer jumlahkeperluanbatako) {
            this.jumlahkeperluanbatako = jumlahkeperluanbatako;
        }

        public Integer getJumlahkeperluanpasir() {
            return jumlahkeperluanpasir;
        }

        public void setJumlahkeperluanpasir(Integer jumlahkeperluanpasir) {
            this.jumlahkeperluanpasir = jumlahkeperluanpasir;
        }

        public Object getJumlahkeperluansemen() {
            return jumlahkeperluansemen;
        }

        public void setJumlahkeperluansemen(Object jumlahkeperluansemen) {
            this.jumlahkeperluansemen = jumlahkeperluansemen;
        }

        public String getMetode() {
            return metode;
        }

        public void setMetode(String metode) {
            this.metode = metode;
        }

        public Integer getHargabatako() {
            return hargabatako;
        }

        public void setHargabatako(Integer hargabatako) {
            this.hargabatako = hargabatako;
        }

        public Integer getHargapasir() {
            return hargapasir;
        }

        public void setHargapasir(Integer hargapasir) {
            this.hargapasir = hargapasir;
        }

        public Integer getHargasemen() {
            return hargasemen;
        }

        public void setHargasemen(Integer hargasemen) {
            this.hargasemen = hargasemen;
        }

        public Integer getHargatotal() {
            return hargatotal;
        }

        public void setHargatotal(Integer hargatotal) {
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
