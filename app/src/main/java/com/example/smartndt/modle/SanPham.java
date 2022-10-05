package com.example.smartndt.modle;

import java.io.Serializable;

public class SanPham implements Serializable {
    int id ;
    String tenSanPham;
    int giaSanPham;
    String hinhAnhSanPham;
    String moTaSanPham;
    int idHangSanPham;

    public SanPham() {
    }

    public SanPham(int id, String tenSanPham, int giaSanPham, String hinhAnhSanPham, String moTaSanPham, int idHangSanPham) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.moTaSanPham = moTaSanPham;
        this.idHangSanPham = idHangSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getHinhAnhSanPham() {
        return hinhAnhSanPham;
    }

    public void setHinhAnhSanPham(String hinhAnhSanPham) {
        this.hinhAnhSanPham = hinhAnhSanPham;
    }

    public String getMoTaSanPham() {
        return moTaSanPham;
    }

    public void setMoTaSanPham(String moTaSanPham) {
        this.moTaSanPham = moTaSanPham;
    }

    public int getIdHangSanPham() {
        return idHangSanPham;
    }

    public void setIdHangSanPham(int idHangSanPham) {
        this.idHangSanPham = idHangSanPham;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "id=" + id +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", giaSanPham=" + giaSanPham +
                ", hinhAnhSanPham='" + hinhAnhSanPham + '\'' +
                ", moTaSanPham='" + moTaSanPham + '\'' +
                ", idHangSanPham=" + idHangSanPham +
                '}';
    }
}
