package com.example.bangunankita.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePasir {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("pasirs")
        @Expose
        private List<Pasir> pasirs = null;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Pasir> getPasirs() {
            return pasirs;
        }

        public void setPasirs(List<Pasir> pasirs) {
            this.pasirs = pasirs;
        }

    }
