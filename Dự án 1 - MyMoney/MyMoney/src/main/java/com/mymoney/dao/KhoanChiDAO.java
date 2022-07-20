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
public class KhoanChiDAO extends MyMoneyDAO<KhoanChi, Integer>{
    final String INSERT_SQL = "INSERT INTO dbo.KhoanChi(TenKC,SoTienKC,NgayChi,GhiChu,MaTK,MaLC) VALUES (?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.KhoanChi SET TenKC = ? ,SoTienKC = ? ,NgayChi = ? ,GhiChu = ? ,MaTK = ? ,MaLC = ? WHERE MaKC = ?";                                
    final String DELETE_SQL = "DELETE FROM dbo.KhoanChi WHERE MaKC = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM KhoanChi";
    final String SELECT_BY_ID_SQL = "SELECT * FROM KhoanChi WHERE MaKC = ?";
    final String SELECT_BY_TAI_KHOAN_SQL = "SELECT * FROM KhoanChi WHERE MaTK = ?";
    final String SELECT_BY_NGAY_CHI_SQL = "SELECT * FROM KhoanChi WHERE MaTK = ? ORDER BY NgayChi desc";
   
    
    
    @Override
    public void insert(KhoanChi entity) {
     jdbcHelper.update(INSERT_SQL, entity.getTenKC(), entity.getSoTienKC(), entity.getNgayChi(), entity.getGhiChu(), entity.getMaTK(), entity.getMaLC());         
    }

    @Override
    public void update(KhoanChi entity) {
         jdbcHelper.update(UPDATE_SQL, entity.getTenKC(), entity.getSoTienKC(), entity.getNgayChi(), entity.getGhiChu(), entity.getMaTK(), entity.getMaLC(), entity.getMaKC());     
    }

    @Override
    public void delete(Integer id) {
    jdbcHelper.update(DELETE_SQL, id);  
    }

    @Override
    public List<KhoanChi> selectAll() {
     return selectBySql(SELECT_ALL_SQL);           
    }
    
    public List<KhoanChi> selectByNgayChi(String id) {
     return selectBySql(SELECT_BY_NGAY_CHI_SQL,id);           
    }
    
    @Override
    public KhoanChi selectById(Integer id) {
    List<KhoanChi> list = selectBySql(SELECT_BY_ID_SQL, id);
       if(list.isEmpty()){
           return null;
       }
       return list.get(0);      
    }

    public List<KhoanChi> selectByTaiKhoan(String id) {
     
       return  selectBySql(SELECT_BY_TAI_KHOAN_SQL, id);    
    }
    
    @Override
    public List<KhoanChi> selectBySql(String sql, Object... args) {
    List<KhoanChi> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                KhoanChi entity = new KhoanChi();
                entity.setMaKC(rs.getInt("MaKC"));
                entity.setTenKC(rs.getString("TenKC"));
                entity.setSoTienKC(rs.getDouble("SoTienKC"));
                entity.setNgayChi(rs.getDate("NgayChi"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaTK(rs.getString("MaTK"));
                entity.setMaLC(rs.getString("MaLC"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;              
    }
    
    public List<Integer> selectYear(){
        String sql = "SELECT DISTINCT year([NgayChi]) Year FROM [dbo].[KhoanChi] ORDER BY Year DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql);
            while(rs.next()){
                list.add(rs.getInt(1));
             }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Integer> selectMonth(Integer id){
        String sql = "SELECT DISTINCT MONTH([NgayChi]) Month FROM [dbo].[KhoanChi] WHERE YEAR(NgayChi) = ? ORDER BY Month DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql,id);
            while(rs.next()){
                list.add(rs.getInt(1));
             }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
     public Integer selectSumBy3id(String tk, Integer thang, Integer nam) {
         String sql = "SELECT SUM([SoTienKC]) FROM [dbo].[KhoanChi] WHERE MaTK = ? AND MONTH([NgayChi]) = ? AND YEAR([NgayChi]) = ?";
    List<Integer> list = new ArrayList<>();
       try {
            ResultSet rs = jdbcHelper.query(sql,tk, thang, nam);
            while(rs.next()){
                list.add(rs.getInt(1));
             }
            rs.getStatement().getConnection().close();
//            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }  
        return list.get(0);
    }
    
}
