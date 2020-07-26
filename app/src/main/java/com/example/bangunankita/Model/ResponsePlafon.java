package com.example.bangunankita.Model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ResponsePlafon {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("perhitunganplafon")
        @Expose
        private List<Perhitunganplafon1> perhitunganplafon = null;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Perhitunganplafon1> getPerhitunganplafon() {
            return perhitunganplafon;
        }

        public void setPerhitunganplafon(List<Perhitunganplafon1> perhitunganplafon) {
            this.perhitunganplafon = perhitunganplafon;
        }

    }

