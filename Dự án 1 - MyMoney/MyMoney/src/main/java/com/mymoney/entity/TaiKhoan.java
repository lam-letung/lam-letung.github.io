/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mymoney.entity;

/**
 *
 * @author lam
 */
public class TaiKhoan {
    private String MaTK;
    private String MatKhau;

    public TaiKhoan() {
    }

    public TaiKhoan(String MaTK, String MatKhau) {
        this.MaTK = MaTK;
        this.MatKhau = MatKhau;
    }

    public String getMaTK() {
        return MaTK ;
    }

    
    
    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }
    
    
}
