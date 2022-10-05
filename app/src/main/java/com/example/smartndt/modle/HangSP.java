package com.example.smartndt.modle;

public class HangSP {
  private   int id;
   private  String tenHang;
   private String loGoHang;

    public HangSP() {
    }

    public HangSP(int id, String tenHang, String loGoHang) {
        this.id = id;
        this.tenHang = tenHang;
        this.loGoHang = loGoHang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getLoGoHang() {
        return loGoHang;
    }

    public void setLoGoHang(String loGoHang) {
        this.loGoHang = loGoHang;
    }
}
