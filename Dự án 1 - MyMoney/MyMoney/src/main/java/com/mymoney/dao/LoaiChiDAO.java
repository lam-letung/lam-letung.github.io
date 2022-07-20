/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mymoney.dao;

import com.mymoney.entity.LoaiChi;
import com.mymoney.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lam
 */
public class LoaiChiDAO extends MyMoneyDAO<LoaiChi, String>{
    final String INSERT_SQL = "INSERT INTO LoaiChi (MaLC,TenLC,HinhLC,MaTK) VALUES (?,?,?,?)";
    final String UPDATE_SQL = "UPDATE LoaiChi SET TenLC = ? ,HinhLC = ? ,MaTK = ? WHERE MaLC = ?";                               
    final String DELETE_SQL = "DELETE FROM dbo.LoaiChi  WHERE MaLC = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM LoaiChi";
    final String SELECT_BY_ID_SQL = "SELECT * FROM LoaiChi WHERE MaLC = ?";
    final String SELECT_BY_TAI_KHOAN_SQL = "SELECT * FROM LoaiChi WHERE MaTK = ?";
    
    
    @Override
    public void insert(LoaiChi entity) {
    jdbcHelper.update(INSERT_SQL, entity.getMaLC(), entity.getTenLC(), entity.getHinhLC(), entity.getMaTK());     
    }

    @Override
    public void update(LoaiChi entity) {
    jdbcHelper.update(UPDATE_SQL, entity.getTenLC(), entity.getHinhLC(), entity.getMaTK(), entity.getMaLC());      
    }

    @Override
    public void delete(String id) {
    jdbcHelper.update(DELETE_SQL, id);        
    }

    @Override
    public List<LoaiChi> selectAll() {
     return selectBySql(SELECT_ALL_SQL);       
    }

    @Override
    public LoaiChi selectById(String id) {
     List<LoaiChi> list = selectBySql(SELECT_BY_ID_SQL, id);
       if(list.isEmpty()){
           return null;
       }
       return list.get(0);        
    }
    
    public List<LoaiChi> selectByTaiKhoan(String id) {
     
       return  selectBySql(SELECT_BY_TAI_KHOAN_SQL, id);    
    }
    
    @Override
    public List<LoaiChi> selectBySql(String sql, Object... args) {
    List<LoaiChi> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                LoaiChi entity = new LoaiChi();
                entity.setMaLC(rs.getString("MaLC"));
                entity.setTenLC(rs.getString("TenLC")); 
                entity.setHinhLC(rs.getString("HinhLC"));
                entity.setMaTK(rs.getString("MaTK"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;          
    }
    
}
