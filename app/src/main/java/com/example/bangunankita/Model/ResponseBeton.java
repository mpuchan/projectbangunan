package com.example.bangunankita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBeton {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("perhitunganbeton")
        @Expose
        private List<Perhitunganbeton1> perhitunganbeton1 = null;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Perhitunganbeton1> getPerhitunganbeton() {
            return perhitunganbeton1;
        }

        public void setPerhitunganbeton(List<Perhitunganbeton1> perhitunganbeton) {
            this.perhitunganbeton1 = perhitunganbeton;
        }


    }

