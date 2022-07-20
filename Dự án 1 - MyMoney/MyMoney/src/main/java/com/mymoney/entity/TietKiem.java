/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mymoney.entity;

import java.util.Date;

/**
 *
 * @author lam
 */
public class TietKiem {
    private String MaTKiem;
    private String TenTKiem;
    private int ThoiHan;
    private String NganHang;
    private Date NgayBatDau;
    private Date NgayHetHan;
    private boolean TrangThai;
    private String MaTK;

    public TietKiem() {
    }

    public TietKiem(String MaTKiem, String TenTKiem, int ThoiHan, String NganHang, Date NgayBatDau, Date NgayHetHan, boolean TrangThai, String MaTK) {
        this.MaTKiem = MaTKiem;
        this.TenTKiem = TenTKiem;
        this.ThoiHan = ThoiHan;
        this.NganHang = NganHang;
        this.NgayBatDau = NgayBatDau;
        this.NgayHetHan = NgayHetHan;
        this.TrangThai = TrangThai;
        this.MaTK = MaTK;
    }

    public String getMaTKiem() {
        return MaTKiem;
    }

    public void setMaTKiem(String MaTKiem) {
        this.MaTKiem = MaTKiem;
    }

    public String getTenTKiem() {
        return TenTKiem;
    }

    public void setTenTKiem(String TenTKiem) {
        this.TenTKiem = TenTKiem;
    }

    public int getThoiHan() {
        return ThoiHan;
    }

    public void setThoiHan(int ThoiHan) {
        this.ThoiHan = ThoiHan;
    }

    public String getNganHang() {
        return NganHang;
    }

    public void setNganHang(String NganHang) {
        this.NganHang = NganHang;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Date getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(Date NgayHetHan) {
        this.NgayHetHan = NgayHetHan;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }

    
    
}
