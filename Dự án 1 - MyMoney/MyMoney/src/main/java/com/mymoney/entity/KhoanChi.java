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
public class KhoanChi {
    private int MaKC;
    private String TenKC;
    private double SoTienKC;
    private Date NgayChi;
    private String GhiChu;
    private String MaTK;
    private String MaLC;

    public KhoanChi() {
    }

    public KhoanChi(int MaKC, String TenKC, double SoTienKC, Date NgayChi, String GhiChu, String MaTK, String MaLC) {
        this.MaKC = MaKC;
        this.TenKC = TenKC;
        this.SoTienKC = SoTienKC;
        this.NgayChi = NgayChi;
        this.GhiChu = GhiChu;
        this.MaTK = MaTK;
        this.MaLC = MaLC;
    }

    public int getMaKC() {
        return MaKC;
    }

    public void setMaKC(int MaKC) {
        this.MaKC = MaKC;
    }

    public String getTenKC() {
        return TenKC;
    }

    public void setTenKC(String TenKC) {
        this.TenKC = TenKC;
    }

    public double getSoTienKC() {
        return SoTienKC;
    }

    public void setSoTienKC(double SoTienKC) {
        this.SoTienKC = SoTienKC;
    }

    public Date getNgayChi() {
        return NgayChi;
    }

    public void setNgayChi(Date NgayChi) {
        this.NgayChi = NgayChi;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }

    public String getMaLC() {
        return MaLC;
    }

    public void setMaLC(String MaLC) {
        this.MaLC = MaLC;
    }
    
    
}
