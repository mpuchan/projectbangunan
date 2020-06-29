package com.example.bangunankita.Model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ResponseAcian {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("perhitunganacian")
        @Expose
        private List<Perhitunganacian1> perhitunganacian1 = null;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Perhitunganacian1> getPerhitunganacian() {
            return perhitunganacian1;
        }

        public void setPerhitunganacian(List<Perhitunganacian1> perhitunganacian) {
            this.perhitunganacian1 = perhitunganacian;
        }

    }

