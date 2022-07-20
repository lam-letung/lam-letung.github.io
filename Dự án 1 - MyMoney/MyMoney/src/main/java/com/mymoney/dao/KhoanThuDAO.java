/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mymoney.dao;

import com.mymoney.entity.KhoanThu;
import com.mymoney.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lam
 */
public class KhoanThuDAO extends MyMoneyDAO<KhoanThu, Integer>{
    final String INSERT_SQL = "INSERT INTO dbo.KhoanThu(TenKT,SoTienKT,NgayThu,GhiChu,MaTK,MaLT) VALUES (?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.KhoanThu SET TenKT = ? ,SoTienKT = ? ,NgayThu = ? ,GhiChu = ? ,MaTK = ? ,MaLT = ? WHERE MaKT = ?";                                
    final String DELETE_SQL = "DELETE FROM dbo.KhoanThu WHERE MaKT = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM KhoanThu";
    final String SELECT_BY_ID_SQL = "SELECT * FROM KhoanThu WHERE MaKT = ?";
    final String SELECT_BY_TAI_KHOAN_SQL = "SELECT * FROM KhoanThu WHERE MaTK = ?";
    final String SELECT_BY_NGAY_THU_SQL = "SELECT * FROM KhoanThu WHERE MaTK = ? ORDER BY NgayThu desc";
   
    
    
    @Override
    public void insert(KhoanThu entity) {
     jdbcHelper.update(INSERT_SQL, entity.getTenKT(), entity.getSoTienKT(), entity.getNgayThu(), entity.getGhiChu(), entity.getMaTK(), entity.getMaLT());         
    }

    @Override
    public void update(KhoanThu entity) {
         jdbcHelper.update(UPDATE_SQL, entity.getTenKT(), entity.getSoTienKT(), entity.getNgayThu(), entity.getGhiChu(), entity.getMaTK(), entity.getMaLT(), entity.getMaKT());     
    }

    @Override
    public void delete(Integer id) {
    jdbcHelper.update(DELETE_SQL, id);  
    }

    @Override
    public List<KhoanThu> selectAll() {
     return selectBySql(SELECT_ALL_SQL);           
    }
    
    public List<KhoanThu> selectByNgayThu(String id) {
     return selectBySql(SELECT_BY_NGAY_THU_SQL,id);           
    }
    
    @Override
    public KhoanThu selectById(Integer id) {
    List<KhoanThu> list = selectBySql(SELECT_BY_ID_SQL, id);
       if(list.isEmpty()){
           return null;
       }
       return list.get(0);      
    }

    public List<KhoanThu> selectByTaiKhoan(String id) {
     
       return  selectBySql(SELECT_BY_TAI_KHOAN_SQL, id);    
    }
    
    @Override
    public List<KhoanThu> selectBySql(String sql, Object... args) {
    List<KhoanThu> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                KhoanThu entity = new KhoanThu();
                entity.setMaKT(rs.getInt("MaKT"));
                entity.setTenKT(rs.getString("TenKT"));
                entity.setSoTienKT(rs.getDouble("SoTienKT"));
                entity.setNgayThu(rs.getDate("NgayThu"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaTK(rs.getString("MaTK"));
                entity.setMaLT(rs.getString("MaLT"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;              
    }
    
    
     public List<Integer> selectYear(){
        String sql = "SELECT DISTINCT year([NgayThu]) Year FROM [dbo].[KhoanThu] ORDER BY Year DESC";
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
        String sql = "SELECT DISTINCT MONTH([NgayThu]) Month FROM [dbo].[KhoanThu] WHERE YEAR(NgayThu) = ? ORDER BY Month DESC";
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
         String sql = "SELECT SUM([SoTienKT]) FROM [dbo].[KhoanThu] WHERE MaTK = ? AND MONTH([NgayThu]) = ? AND YEAR([NgayThu]) = ?";
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
