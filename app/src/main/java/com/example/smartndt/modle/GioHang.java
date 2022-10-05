package com.example.smartndt.modle;

public class GioHang {
    int idsp;
    String tenSP;
    long giaSP;
    String hinHanhSP;
    int soLuong;

    public GioHang() {
    }

    public GioHang(int idsp, String tenSP, long giaSP, String hinHanhSP, int soLuong) {
        this.idsp = idsp;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.hinHanhSP = hinHanhSP;
        this.soLuong = soLuong;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public long getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(long giaSP) {
        this.giaSP = giaSP;
    }

    public String getHinHanhSP() {
        return hinHanhSP;
    }

    public void setHinHanhSP(String hinHanhSP) {
        this.hinHanhSP = hinHanhSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
