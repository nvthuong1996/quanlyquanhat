/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.dao.CaLamViecDAO;
import model.dao.LichDangKyDAO;
import model.entities.CaLamViec;
import model.entities.LichDangKy;
import model.entities.NguoiDung;

/**
 *
 * @author thuongptitdev
 */
public class LichDangKyImpl implements LichDangKyDAO {

    private Connection conn;

    public LichDangKyImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public LichDangKy findById(Integer id) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM lichdangky WHERE Id = ?");
        st.setInt(1, id);
        rs = st.executeQuery();
        if (rs.next()) {
            LichDangKy obj = new LichDangKy();
            CaLamViec ca = new CaLamViec();
            obj.setCaLamViec(ca);
            obj.setId(rs.getInt("id"));
            obj.setNgay(rs.getDate("ngay"));
            obj.setTrangthai(rs.getString("trangthai"));
            NguoiDungDaoImpl nguoidungdao = new NguoiDungDaoImpl(conn);
            NguoiDung nguoidung = nguoidungdao.findByIdAccount(rs.getInt("idnhanvien"));
            obj.setNguoiDung(nguoidung);
            obj.getCaLamViec().setTen(rs.getString("tenca"));
            obj.getCaLamViec().setGioBatDau(rs.getInt("hbatdau"));
            obj.getCaLamViec().setGioKetThuc(rs.getInt("hketthuc"));
            obj.getCaLamViec().setPhutBatDau(rs.getInt("pbatdau"));
            obj.getCaLamViec().setPhutKetThuc(rs.getInt("pketthuc"));
            return obj;
        }
        return null;
    }

    @Override
    public void insert(LichDangKy obj) throws Exception {
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "INSERT INTO lichdangky "
                    + "(tenca,hbatdau,hketthuc,pbatdau,pketthuc,idnhanvien,trangthai,ngay)"
                    + "VALUES "
                    + "(?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getCaLamViec().getTen());
            st.setInt(2, obj.getCaLamViec().getGioBatDau());
            st.setInt(3, obj.getCaLamViec().getGioKetThuc());
            st.setInt(4, obj.getCaLamViec().getPhutBatDau());
            st.setInt(5, obj.getCaLamViec().getPhutKetThuc());
            st.setInt(6, obj.getNguoiDung().getId());
            st.setString(7, obj.getTrangthai());
            st.setDate(8, convertUtilToSql(obj.getNgay()));
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                conn.commit();
            }
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        }
    }

    @Override
    public void update(LichDangKy obj) throws Exception {
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "UPDATE lichdangky "
                    + "SET tenca = ?, hbatdau = ?, hketthuc = ?, pbatdau = ?, pketthuc = ?"
                    + "WHERE id = ?");
            st.setString(1, obj.getCaLamViec().getTen());
            st.setInt(2, obj.getCaLamViec().getGioBatDau());
            st.setInt(3, obj.getCaLamViec().getGioKetThuc());
            st.setInt(4, obj.getCaLamViec().getPhutBatDau());
            st.setInt(5, obj.getCaLamViec().getPhutKetThuc());
            st.setInt(6, obj.getId());
            st.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);
            st = conn.prepareStatement("DELETE FROM lichdangky WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    }

    @Override
    public List<LichDangKy> fillAllByIdNguoiDung(Integer id) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM lichdangky WHERE idnhanvien = ? ORDER BY tenca");
        
        st.setInt(1, id);
        rs = st.executeQuery();
        
       

        List<LichDangKy> list = new ArrayList<>();

        while (rs.next()) {
            LichDangKy obj = new LichDangKy();
            CaLamViec ca = new CaLamViec();
            obj.setCaLamViec(ca);
            obj.setId(rs.getInt("id"));
            obj.setNgay(rs.getDate("ngay"));
            obj.setTrangthai(rs.getString("trangthai"));
            NguoiDungDaoImpl nguoidungdao = new NguoiDungDaoImpl(conn);
            NguoiDung nguoidung = nguoidungdao.findByIdAccount(rs.getInt("idnhanvien"));
            obj.setNguoiDung(nguoidung);
            obj.getCaLamViec().setTen(rs.getString("tenca"));
            obj.getCaLamViec().setGioBatDau(rs.getInt("hbatdau"));
            obj.getCaLamViec().setGioKetThuc(rs.getInt("hketthuc"));
            obj.getCaLamViec().setPhutBatDau(rs.getInt("pbatdau"));
            obj.getCaLamViec().setPhutKetThuc(rs.getInt("pketthuc"));
            list.add(obj);
        }
        return list;
    }
    
    public void changeStatus(Integer id,String status) throws Exception{
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "UPDATE lichdangky "
                    + "SET trangthai = ?"
                    + "WHERE id = ?");
            st.setString(1, status);
            st.setInt(2, id);
            st.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }   
    
    @Override
    public List<LichDangKy> fillAll() throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM lichdangky ORDER BY tenca");
        
        rs = st.executeQuery();
        
       

        List<LichDangKy> list = new ArrayList<>();

        while (rs.next()) {
            LichDangKy obj = new LichDangKy();
            CaLamViec ca = new CaLamViec();
            obj.setCaLamViec(ca);
            obj.setId(rs.getInt("id"));
            obj.setNgay(rs.getDate("ngay"));
            obj.setTrangthai(rs.getString("trangthai"));
            NguoiDungDaoImpl nguoidungdao = new NguoiDungDaoImpl(conn);
            NguoiDung nguoidung = nguoidungdao.findByIdAccount(rs.getInt("idnhanvien"));
            obj.setNguoiDung(nguoidung);
            obj.getCaLamViec().setTen(rs.getString("tenca"));
            obj.getCaLamViec().setGioBatDau(rs.getInt("hbatdau"));
            obj.getCaLamViec().setGioKetThuc(rs.getInt("hketthuc"));
            obj.getCaLamViec().setPhutBatDau(rs.getInt("pbatdau"));
            obj.getCaLamViec().setPhutKetThuc(rs.getInt("pketthuc"));
            list.add(obj);
        }
        return list;
    }
    
    
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
