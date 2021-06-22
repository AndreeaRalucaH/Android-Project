package com.example.proiecthorhociagurita;

public class PayU {

    private long nrCard;
    private String dataCard;
    private int cvv;
    private String nume;
    private int totalPlata;

    public PayU(){

    }

    public PayU(long nrCard, String dataCard, int cvv, String nume, int totalPlata) {
        this.nrCard = nrCard;
        this.dataCard = dataCard;
        this.cvv = cvv;
        this.nume = nume;
        this.totalPlata = totalPlata;
    }

    public long getNrCard() {
        return nrCard;
    }

    public void setNrCard(int nrCard) {
        this.nrCard = nrCard;
    }

    public String getDataCard() {
        return dataCard;
    }

    public void setDataCard(String dataCard) {
        this.dataCard = dataCard;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getTotalPlata() {
        return totalPlata;
    }

    public void setTotalPlata(int totalPlata) {
        this.totalPlata = totalPlata;
    }


    @Override
    public String toString() {
        return "PayU{" +
                "nrCard=" + nrCard +
                ", dataCard='" + dataCard + '\'' +
                ", cvv=" + cvv +
                ", nume='" + nume + '\'' +
                ", totalPlata=" + totalPlata +
                '}';
    }
}
