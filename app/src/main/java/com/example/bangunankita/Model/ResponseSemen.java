package com.example.bangunankita.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSemen {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("semens")
        @Expose
        private List<Semen> semens = null;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Semen> getSemens() {
            return semens;
        }

        public void setSemens(List<Semen> semens) {
            this.semens = semens;
        }
}
