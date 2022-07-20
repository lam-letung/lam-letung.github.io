/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mymoney.dao;

import com.mymoney.entity.TietKiem;
import com.mymoney.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lam
 */
public class TietKiemDAO extends MyMoneyDAO<TietKiem, String>{
    final String INSERT_SQL = "INSERT INTO [dbo].[TietKiem]([MaTKiem],[TenTKiem],[ThoiHan],[NganHang],[NgayBatDau],[NgayHetHan],[TrangThai],[MaTK])  VALUES (?,?,?,?,? ,? ,? ,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[TietKiem]  SET [TenTKiem] = ? ,[ThoiHan] = ? ,[NganHang] = ? ,[NgayBatDau] = ? ,[NgayHetHan] = ? ,[TrangThai] = ? ,[MaTK] = ? WHERE [MaTKiem] = ?";                                
    final String DELETE_SQL = "DELETE FROM [dbo].[TietKiem] WHERE [MaTKiem] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[TietKiem]";
    final String SELECT_BY_ID_SQL = "SELECT * FROM [dbo].[TietKiem] WHERE [MaTKiem] = ?";
    final String SELECT_BY_TAI_KHOAN_SQL = "SELECT * FROM [dbo].[TietKiem] WHERE [MaTK] = ?";
    
    
    @Override
    public void insert(TietKiem entity) {
        jdbcHelper.update(INSERT_SQL, entity.getMaTKiem(), entity.getTenTKiem(), entity.getThoiHan(), entity.getNganHang(), entity.getNgayBatDau(), entity.getNgayHetHan(), entity.isTrangThai(),entity.getMaTK());         
    }

    @Override
    public void update(TietKiem entity) {
        jdbcHelper.update(UPDATE_SQL,  entity.getTenTKiem(), entity.getThoiHan(), entity.getNganHang(), entity.getNgayBatDau(), entity.getNgayHetHan(), entity.isTrangThai(),entity.getMaTK(), entity.getMaTKiem());         
    }

    @Override
    public void delete(String id) {
     jdbcHelper.update(DELETE_SQL, id);     
    }

    @Override
    public List<TietKiem> selectAll() {
       return selectBySql(SELECT_ALL_SQL);              
    }

    @Override
    public TietKiem selectById(String id) {
     List<TietKiem> list = selectBySql(SELECT_BY_ID_SQL, id);
       if(list.isEmpty()){
           return null;
       }
       return list.get(0);          
    }

    @Override
    public List<TietKiem> selectBySql(String sql, Object... args) {
     List<TietKiem> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
               TietKiem entity = new TietKiem();
                entity.setMaTKiem(rs.getString("MaTKiem"));
                entity.setTenTKiem(rs.getString("TenTKiem"));
                entity.setThoiHan(rs.getInt("ThoiHan"));
                entity.setNganHang(rs.getString("NganHang"));
                entity.setNgayBatDau(rs.getDate("NgayBatDau"));
                entity.setNgayHetHan(rs.getDate("NgayHetHan"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                entity.setMaTK(rs.getString("MaTK"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;                
    }
    
      public List<TietKiem> selectByTaiKhoan(String id) {
     
       return  selectBySql(SELECT_BY_TAI_KHOAN_SQL, id);    
    }
    
}
