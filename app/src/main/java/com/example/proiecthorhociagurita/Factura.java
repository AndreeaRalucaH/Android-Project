package com.example.proiecthorhociagurita;

import java.io.Serializable;
import java.util.Date;

public class Factura implements Serializable {

    private String denumireFurn;
    private String dataEmitere;
    private int suma;
    private String dataScadenta;
    private String mentiuni;


    public Factura(){

    }


    public Factura(String denumireFurn, String dataEmitere, int suma, String dataScadenta, String mentiuni) {
        this.denumireFurn = denumireFurn;
        this.dataEmitere = dataEmitere;
        this.suma = suma;
        this.dataScadenta = dataScadenta;
        this.mentiuni = mentiuni;
    }

    public String getDenumireFurn() {
        return denumireFurn;
    }

    public void setDenumireFurn(String denumireFurn) {
        this.denumireFurn = denumireFurn;
    }

    public String getDataEmitere() {
        return dataEmitere;
    }

    public void setDataEmitere(String dataEmitere) {
        this.dataEmitere = dataEmitere;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public String getDataScadenta() {
        return dataScadenta;
    }

    public void setDataScadenta(String dataScadenta) {
        this.dataScadenta = dataScadenta;
    }

    public String getMentiuni() {
        return mentiuni;
    }

    public void setMentiuni(String mentiuni) {
        this.mentiuni = mentiuni;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "denumireFurn='" + denumireFurn + '\'' +
                ", dataEmitere='" + dataEmitere + '\'' +
                ", suma=" + suma +
                ", dataScadenta='" + dataScadenta + '\'' +
                ", mentiuni='" + mentiuni + '\'' +
                '}';
    }
}
