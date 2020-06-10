package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pasir {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("nama")
        @Expose
        private String nama;
        @SerializedName("jumlah")
        @Expose
        private Integer jumlah;
        @SerializedName("SatuanId")
        @Expose
        private Integer satuanId;
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

        public Integer getJumlah() {
            return jumlah;
        }

        public void setJumlah(Integer jumlah) {
            this.jumlah = jumlah;
        }

        public Integer getSatuanId() {
            return satuanId;
        }

        public void setSatuanId(Integer satuanId) {
            this.satuanId = satuanId;
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
