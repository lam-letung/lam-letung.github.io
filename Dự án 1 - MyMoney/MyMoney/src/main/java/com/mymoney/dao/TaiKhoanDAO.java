/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mymoney.dao;

import com.mymoney.entity.TaiKhoan;
import com.mymoney.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lam
 */
public class TaiKhoanDAO extends MyMoneyDAO<TaiKhoan, String> {
    
    final String INSERT_SQL = "INSERT INTO TaiKhoan(TaiKhoan,MatKhau) VALUES(?,?)";
    final String UPDATE_SQL = "UPDATE TaiKhoan SET MatKhau = ? WHERE TaiKhoan = ?";                               
//    final String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM TaiKhoan";
    final String SELECT_BY_ID_SQL = "SELECT * FROM TaiKhoan WHERE TaiKhoan = ?";

    @Override
    public void insert(TaiKhoan entity) {
        jdbcHelper.update(INSERT_SQL, entity.getMaTK(), entity.getMatKhau());
    }

    @Override
    public void update(TaiKhoan entity) {
        jdbcHelper.update(UPDATE_SQL , entity.getMatKhau(), entity.getMaTK());
    }

    @Override
    public void delete(String id) {
       
    }

    @Override
    public List<TaiKhoan> selectAll() {
                return selectBySql(SELECT_ALL_SQL);

    }

    @Override
    public TaiKhoan selectById(String id) {
      List<TaiKhoan> list = selectBySql(SELECT_BY_ID_SQL, id);
       if(list.isEmpty()){
           return null;
       }
       return list.get(0);
    }

    @Override
    public List<TaiKhoan> selectBySql(String sql, Object... args) {
    List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                TaiKhoan entity = new TaiKhoan();
                entity.setMaTK(rs.getString("TaiKhoan"));
                entity.setMatKhau(rs.getString("MatKhau"));               
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;   
    }

}
