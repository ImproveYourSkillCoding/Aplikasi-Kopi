package com.belajarkodecoding.aplikasicoffee;

public class Item_keranjang {
    String  nama, url;
    int harga, jumlah,total;

    public Item_keranjang() {
    }

    public Item_keranjang(int harga, int jumlah,int total, String nama, String url) {
        this.harga = harga;
        this.nama = nama;
        this.url = url;
        this.jumlah = jumlah;
        this.total= total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public int getJumlah() { return jumlah;}

    public void setJumlah(int jumlah){
        this.jumlah = jumlah;
    }
}
