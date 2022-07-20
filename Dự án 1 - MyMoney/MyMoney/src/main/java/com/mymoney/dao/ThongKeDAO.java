/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mymoney.dao;

import com.mymoney.entity.KhoanChi;
import com.mymoney.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lam
 */
public class ThongKeDAO {
     private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs= jdbcHelper.query(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     
     
     public List<Object[]> getThuNam(String maTK, Integer nam){
        String sql ="{CALL sp_ThuNam(?,?)}";
        String[] cols = {"TenLoaiThu","TongThu","ThapNhat","CaoNhat","TrungBinh","ThangThu","NamThu"};
        return getListOfArray(sql, cols, maTK, nam);
    }
     
      public List<Object[]> getThuThamgNam(String maTK, Integer thang,Integer nam){
        String sql ="{CALL sp_ThuThangNam(?,?,?)}";
        String[] cols = {"TenLoaiThu","TongThu","ThapNhat","CaoNhat","TrungBinh"};
        return getListOfArray(sql, cols, maTK, thang, nam);
    }
      
       public List<Object[]> getChiNam(String maTK, Integer nam){
        String sql ="{CALL sp_ChiNam(?,?)}";
        String[] cols = {"TenLoaiChi","TongChi","ThapNhat","CaoNhat","TrungBinh","ThangChi","NamChi"};
        return getListOfArray(sql, cols, maTK, nam);
    }
     
      public List<Object[]> getChiThamgNam(String maTK, Integer thang, Integer nam){
        String sql ="{CALL sp_ChiThangNam(?,?,?)}";
        String[] cols = {"TenLoaiChi","TongChi","ThapNhat","CaoNhat","TrungBinh"};
        return getListOfArray(sql, cols, maTK, thang, nam);
    }  
     
}
