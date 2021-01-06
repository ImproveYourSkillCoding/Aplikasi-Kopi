package com.belajarkodecoding.aplikasicoffee;

// deklarasi tipe data
public class user {
    private String key;
    private String nama;
    private String alamat;
    private String no_telp;

    public user(){
    // public constructor
    }

    // memasukkan data ke user
    public user(String nama, String alamat,String no_telp){
        this.nama = nama;
        this.alamat = alamat;
        this.no_telp = no_telp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }
}
