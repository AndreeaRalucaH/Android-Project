package com.example.proiecthorhociagurita;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "venituri", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = CASCADE), indices = @Index("userId"))
public class Venit implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String dataVenit;
    private String provenienta;
    private int suma;
    private String tipVenit;

    private long userId;

    @Ignore
    private String uid;

    @Ignore
    public Venit(){

    }

    @Ignore
    public Venit(String dataVenit, String provenienta, int suma, String tipVenit) {
        this.dataVenit = dataVenit;
        this.provenienta = provenienta;
        this.suma = suma;
        this.tipVenit = tipVenit;
    }

    public Venit(String dataVenit, String provenienta, int suma, String tipVenit, long userId) {
        this.dataVenit = dataVenit;
        this.provenienta = provenienta;
        this.suma = suma;
        this.tipVenit = tipVenit;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDataVenit() {
        return dataVenit;
    }

    public void setDataVenit(String dataVenit) {
        this.dataVenit = dataVenit;
    }

    public String getProvenienta() {
        return provenienta;
    }

    public void setProvenienta(String provenienta) {
        this.provenienta = provenienta;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public String getTipVenit() {
        return tipVenit;
    }

    public void setTipVenit(String tipVenit) {
        this.tipVenit = tipVenit;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Venit{" +
                "dataVenit='" + dataVenit + '\'' +
                ", Provenienta='" + provenienta + '\'' +
                ", suma=" + suma +
                ", tipVenit='" + tipVenit + '\'' +
                '}';
    }
}
