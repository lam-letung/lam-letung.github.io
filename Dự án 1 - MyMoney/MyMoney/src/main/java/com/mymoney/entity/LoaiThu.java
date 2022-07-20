/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mymoney.entity;

import java.util.Objects;

/**
 *
 * @author lam
 */
public class LoaiThu {
    private String MaLT;
    private String TenLT;
    private String HinhLT;
    private String MaTK;

    @Override
    public boolean equals(Object obj){
        LoaiThu target = (LoaiThu) obj;
        if(target == null) return false;
        return target.getMaLT().equals(this.getMaLT());
    }
    
    
    @Override
    public String toString(){
        return this.TenLT;
    }
    
    public LoaiThu() {
    }

    public LoaiThu(String MaLT, String TenLT, String HinhLT, String MaTK) {
        this.MaLT = MaLT;
        this.TenLT = TenLT;
        this.HinhLT = HinhLT;
        this.MaTK = MaTK;
    }

    public String getMaLT() {
        return MaLT;
    }

    public void setMaLT(String MaLT) {
        this.MaLT = MaLT;
    }

    public String getTenLT() {
        return TenLT;
    }

    public void setTenLT(String TenLT) {
        this.TenLT = TenLT;
    }

    public String getHinhLT() {
        return HinhLT;
    }

    public void setHinhLT(String HinhLT) {
        this.HinhLT = HinhLT;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }
    
    
}
