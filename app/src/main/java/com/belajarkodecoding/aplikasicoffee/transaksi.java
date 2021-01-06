package com.belajarkodecoding.aplikasicoffee;

public class transaksi {
 String date, metode;
 int harga;

 public transaksi() {

 }

    public transaksi(String date, String metode, int harga){
        this.date = date;
        this.harga = harga;
        this.metode = metode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMetode() {
        return metode;
    }

    public void setMetode(String metode) {
        this.metode = metode;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }




}
