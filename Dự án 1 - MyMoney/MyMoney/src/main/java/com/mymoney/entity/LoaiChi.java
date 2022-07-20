/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mymoney.entity;

/**
 *
 * @author lam
 */
public class LoaiChi {
    private String MaLC;
    private String TenLC;
    private String HinhLC;
    private String MaTK;

     @Override
    public boolean equals(Object obj){
        LoaiChi target = (LoaiChi) obj;
        if(target == null) return false;
        return target.getMaLC().equals(this.getMaLC());
    }
    
     @Override
    public String toString(){
        return this.TenLC;
    }
    
    public LoaiChi() {
    }

    public LoaiChi(String MaLC, String TenLC, String HinhLC, String MaTK) {
        this.MaLC = MaLC;
        this.TenLC = TenLC;
        this.HinhLC = HinhLC;
        this.MaTK = MaTK;
    }

    public String getMaLC() {
        return MaLC;
    }

    public void setMaLC(String MaLC) {
        this.MaLC = MaLC;
    }

    public String getTenLC() {
        return TenLC;
    }

    public void setTenLC(String TenLC) {
        this.TenLC = TenLC;
    }

    public String getHinhLC() {
        return HinhLC;
    }

    public void setHinhLC(String HinhLC) {
        this.HinhLC = HinhLC;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }
    
    
}
