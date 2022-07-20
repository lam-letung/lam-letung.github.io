/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mymoney.dao;

import com.mymoney.entity.LoaiThu;
import com.mymoney.entity.TaiKhoan;
import com.mymoney.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lam
 */
public class LoaiThuDAO extends MyMoneyDAO<LoaiThu, String> {
    final String INSERT_SQL = "INSERT INTO LoaiThu (MaLT,TenLT,HinhLT,MaTK) VALUES (?,?,?,?)";
    final String UPDATE_SQL = "UPDATE LoaiThu SET TenLT = ? ,HinhLT = ? ,MaTK = ? WHERE MaLT = ?";                               
    final String DELETE_SQL = "DELETE FROM dbo.LoaiThu  WHERE MaLT = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM LoaiThu";
    final String SELECT_BY_ID_SQL = "SELECT * FROM LoaiThu WHERE MaLT = ?";
    final String SELECT_BY_TAI_KHOAN_SQL = "SELECT * FROM LoaiThu WHERE MaTK = ?";
    final String SELECT_TEN_LOAI_THU_BY_ID_SQL = "SELECT TenLT FROM LoaiThu WHERE MaLT = ?";
    
    


    @Override
    public void insert(LoaiThu entity) {
     jdbcHelper.update(INSERT_SQL, entity.getMaLT(), entity.getTenLT(), entity.getHinhLT(), entity.getMaTK());  
    }

    @Override
    public void update(LoaiThu entity) {
    jdbcHelper.update(UPDATE_SQL, entity.getTenLT(), entity.getHinhLT(), entity.getMaTK(), entity.getMaLT());  
    }

    @Override
    public void delete(String id) {
      jdbcHelper.update(DELETE_SQL, id);    
    }

    @Override
    public List<LoaiThu> selectAll() {
    return selectBySql(SELECT_ALL_SQL);   
    }

    @Override
    public LoaiThu selectById(String id) {
    List<LoaiThu> list = selectBySql(SELECT_BY_ID_SQL, id);
       if(list.isEmpty()){
           return null;
       }
       return list.get(0);    
    }
    
     
    public List<LoaiThu>  selectByTenLTId(String id) {
    return  selectBySql(SELECT_TEN_LOAI_THU_BY_ID_SQL, id);
    }

     
    public List<LoaiThu> selectByTaiKhoan(String id) {
     
       return  selectBySql(SELECT_BY_TAI_KHOAN_SQL, id);    
    }
    
    @Override
    public List<LoaiThu> selectBySql(String sql, Object... args) {
    List<LoaiThu> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                LoaiThu entity = new LoaiThu();
                entity.setMaLT(rs.getString("MaLT"));
                entity.setTenLT(rs.getString("TenLT")); 
                entity.setHinhLT(rs.getString("HinhLT"));
                entity.setMaTK(rs.getString("MaTK"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;      
    }
    
    
    
   
    
}
