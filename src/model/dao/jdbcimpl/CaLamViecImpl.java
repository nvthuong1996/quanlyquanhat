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
import model.entities.CaLamViec;

/**
 *
 * @author thuongptitdev
 */
public class CaLamViecImpl implements CaLamViecDAO {

    private Connection conn;

    public CaLamViecImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public CaLamViec findById(Integer id) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM calamviec WHERE Id = ?");
        st.setInt(1, id);
        rs = st.executeQuery();
        if (rs.next()) {
            CaLamViec obj = new CaLamViec();
            obj.setId(rs.getInt("Id"));
            obj.setTen(rs.getString("ten"));
            obj.setGioBatDau(rs.getInt("hbatdau"));
            obj.setGioKetThuc(rs.getInt("hketthuc"));
            obj.setPhutBatDau(rs.getInt("pbatdau"));
            obj.setPhutKetThuc(rs.getInt("pketthuc"));
            return obj;
        }
        return null;

    }

    @Override
    public void insert(CaLamViec obj) throws Exception {
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "INSERT INTO calamviec "
                    + "(tenca,hbatdau,hketthuc,pbatdau,pketthuc)"
                    + "VALUES "
                    + "(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getTen());
            st.setInt(2, obj.getGioBatDau());
            st.setInt(3, obj.getGioKetThuc());
            st.setInt(4, obj.getPhutBatDau());
            st.setInt(5, obj.getPhutKetThuc());

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
    public void update(CaLamViec obj) throws Exception {
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "UPDATE calamviec "
                    + "SET tenca = ?, hbatdau = ?, hketthuc = ?, pbatdau = ?, pketthuc = ?"
                    + "WHERE id = ?");
            st.setString(1, obj.getTen());
            st.setInt(2, obj.getGioBatDau());
            st.setInt(3, obj.getGioKetThuc());
            st.setInt(4, obj.getPhutBatDau());
            st.setInt(5, obj.getPhutKetThuc());
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
            st = conn.prepareStatement("DELETE FROM calamviec WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    }

    @Override
    public List<CaLamViec> fillAll() throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM calamviec ORDER BY tenca");
        rs = st.executeQuery();

        List<CaLamViec> list = new ArrayList<>();

        while (rs.next()) {
            CaLamViec obj = new CaLamViec();
            obj.setId(rs.getInt("id"));
            obj.setTen(rs.getString("tenca"));
            obj.setGioBatDau(rs.getInt("hbatdau"));
            obj.setGioKetThuc(rs.getInt("hketthuc"));
            obj.setPhutBatDau(rs.getInt("pbatdau"));
            obj.setPhutKetThuc(rs.getInt("pketthuc"));
            list.add(obj);
        }
        return list;
    }
}
