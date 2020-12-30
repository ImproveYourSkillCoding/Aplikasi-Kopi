package com.belajarkodecoding.aplikasicoffee;

public class Item {
    String harga, judul, purl;

    public Item() {
    }

    public Item(String harga, String judul, String purl) {
        this.harga = harga;
        this.judul = judul;
        this.purl = purl;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
