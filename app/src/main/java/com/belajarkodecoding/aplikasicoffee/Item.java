package com.belajarkodecoding.aplikasicoffee;

public class Item {
    String harga, nama, url;

    public Item() {
    }

    public Item(String harga, String nama, String url) {
        this.harga = harga;
        this.nama = nama;
        this.url = url;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
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
