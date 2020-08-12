package com.example.a10117227_uasif7.model;

public class DaftarModel {
    //    Tanggal Pengerjaan : 9 Agustus 2020
    //    Nim : 10117227
    //    Nama : Mohamad Riyan Hidayat
    //    Kelas : IF - 7
    private Integer id;
    private String nama;
    private String alamat;
    private String website;

    public DaftarModel(String nama, String alamat, String website) {
//        this.id = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.website = website;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
