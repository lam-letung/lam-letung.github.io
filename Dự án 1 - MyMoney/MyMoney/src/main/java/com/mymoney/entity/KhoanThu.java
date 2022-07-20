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
public class KhoanThu {
    private int MaKT;
    private String TenKT;
    private double SoTienKT;
    private Date NgayThu;
    private String GhiChu;
    private String MaTK;
    private String MaLT;

    @Override
     public String toString(){
         return this.MaLT  ;
     }
    
    
    public KhoanThu() {
    }

    public KhoanThu(int MaKT, String TenKT, double SoTienKT, Date NgayThu, String GhiChu, String MaTK, String MaLT) {
        this.MaKT = MaKT;
        this.TenKT = TenKT;
        this.SoTienKT = SoTienKT;
        this.NgayThu = NgayThu;
        this.GhiChu = GhiChu;
        this.MaTK = MaTK;
        this.MaLT = MaLT;
    }

    public int getMaKT() {
        return MaKT;
    }

    public void setMaKT(int MaKT) {
        this.MaKT = MaKT;
    }

    public String getTenKT() {
        return TenKT;
    }

    public void setTenKT(String TenKT) {
        this.TenKT = TenKT;
    }

    public double getSoTienKT() {
        return SoTienKT;
    }

    public void setSoTienKT(double SoTienKT) {
        this.SoTienKT = SoTienKT;
    }

    public Date getNgayThu() {
        return NgayThu;
    }

    public void setNgayThu(Date NgayThu) {
        this.NgayThu = NgayThu;
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

    public String getMaLT() {
        return MaLT;
    }

    public void setMaLT(String MaLT) {
        this.MaLT = MaLT;
    }
    
    
}


