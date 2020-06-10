package com.example.bangunankita.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBatako {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("batakos")
        @Expose
        private List<Batako> batakos = null;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Batako> getBatakos() {
            return batakos;
        }

        public void setBatakos(List<Batako> batakos) {
            this.batakos = batakos;
        }

    }


