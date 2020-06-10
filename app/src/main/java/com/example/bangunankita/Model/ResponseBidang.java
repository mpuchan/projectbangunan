package com.example.bangunankita.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBidang {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("perhitunganbidang")
        @Expose
        private List<Perhitunganbidang> perhitunganbidang = null;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Perhitunganbidang> getPerhitunganbidang() {
            return perhitunganbidang;
        }

        public void setPerhitunganbidang(List<Perhitunganbidang> perhitunganbidang) {
            this.perhitunganbidang = perhitunganbidang;
        }

    }
