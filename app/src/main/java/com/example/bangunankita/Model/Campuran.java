package com.example.bangunankita.Model;

public class Campuran {

    private String campuran;
    private double pc;

    public double getPp() {
        return pp;
    }

    public void setPp(double pp) {
        this.pp = pp;
    }

    private  double pp;

    public double getPc() {
        return pc;
    }

    public void setPc(double pc) {
        this.pc = pc;
    }

    public Campuran(String campuran, double pc, double pp){
        this.campuran = campuran;
        this.pc = pc;
        this.pp = pp;
    }

    public String getCampuran() {
        return campuran;
    }

    public void setCampuran(String campuran) {
        this.campuran = campuran;
    }
}