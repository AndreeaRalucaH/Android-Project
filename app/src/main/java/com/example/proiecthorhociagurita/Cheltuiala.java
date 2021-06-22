package com.example.proiecthorhociagurita;

import java.io.Serializable;
import java.util.Date;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (tableName = "cheltuieli", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = CASCADE), indices = @Index("userId"))
public class Cheltuiala implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String denumire;
    private int suma;
    private String dataCheltuiala;
    private String notite;
    private String tipCheltuiala;

    private long userId;

    @Ignore
    public Cheltuiala(){

    }

    @Ignore
    public Cheltuiala(String denumire, int suma, String dataCheltuiala, String notite, String tipCheltuiala) {
        this.denumire = denumire;
        this.suma = suma;
        this.dataCheltuiala = dataCheltuiala;
        this.notite = notite;
        this.tipCheltuiala = tipCheltuiala;
    }

    public Cheltuiala(String denumire, int suma, String dataCheltuiala, String notite, String tipCheltuiala, long userId) {
        this.denumire = denumire;
        this.suma = suma;
        this.dataCheltuiala = dataCheltuiala;
        this.notite = notite;
        this.tipCheltuiala = tipCheltuiala;
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public String getDataCheltuiala() {
        return dataCheltuiala;
    }

    public void setDataCheltuiala(String dataCheltuiala) {
        this.dataCheltuiala = dataCheltuiala;
    }

    public String getNotite() {
        return notite;
    }

    public void setNotite(String notite) {
        this.notite = notite;
    }

    public String getTipCheltuiala() {
        return tipCheltuiala;
    }

    public void setTipCheltuiala(String tipCheltuiala) {
        this.tipCheltuiala = tipCheltuiala;
    }

    @Override
    public String toString() {
        return "Cheltuiala{" +
                "denumire='" + denumire + '\'' +
                ", suma=" + suma +
                ", dataCheltuiala='" + dataCheltuiala + '\'' +
                ", notite='" + notite + '\'' +
                ", tipCheltuiala='" + tipCheltuiala + '\'' +
                ", userId= " + userId +
                '}';
    }
}
