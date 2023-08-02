package com.example.assigment_and2.model;

public class sanPham {
    private int maSp;
    private int giaBan;
    private int soLuong;
    private String tenSp;

    public sanPham(int maSp, int giaBan, int soLuong, String tenSp) {
        this.maSp = maSp;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.tenSp = tenSp;
    }

    public sanPham() {
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }
}
