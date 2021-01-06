package com.belajarkodecoding.aplikasicoffee;

// deklarasi tipe data
public class Item {
    String  nama, url;
    int harga;

    public Item() {
        // public constructor
    }

    // memasukkan data ke dalam Item
    public Item(int harga, String nama, String url) {
        this.harga = harga;
        this.nama = nama;
        this.url = url;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
