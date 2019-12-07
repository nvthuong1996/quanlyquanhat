/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.TaiKhoanDAO;
import model.entities.TaiKhoan;

/**
 *
 * @author thuongptitdev
 */
public class TaiKhoanDaoImpl implements TaiKhoanDAO {

    private Connection conn;

    public TaiKhoanDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(TaiKhoan taikhoan) throws Exception {
        PreparedStatement st = null;

        // conn.setAutoCommit(false);
        st = conn.prepareStatement(
                "INSERT INTO taikhoan "
                + "(taikhoan,matkhau) "
                + "VALUES "
                + "(?,?)",
                Statement.RETURN_GENERATED_KEYS);

        st.setString(1, taikhoan.getTenTaiKhoan());
        st.setString(2, taikhoan.getMatKhau());

        int rowsAffected = st.executeUpdate();
        if (rowsAffected > 0) {
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                taikhoan.setId(id);
            }
            // conn.commit();
        } else {
            throw new Exception("Unexpected error! No rows affected!");
        }
    }

    @Override
    public TaiKhoan findByTaiKhoan(String u,String p) throws Exception{
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM taikhoan WHERE taikhoan = ? AND matkhau = ?");
        st.setString(1, u);
        st.setString(2, p);
        rs = st.executeQuery();
        if (rs.next()) {
            TaiKhoan obj = new TaiKhoan();
            obj.setId(rs.getInt("id"));
            obj.setMatKhau(rs.getString("matkhau"));
            obj.setTenTaiKhoan(rs.getString("taikhoan"));
            return obj;
        }
        return null;
    }
    
    @Override
    public TaiKhoan findById(Integer id) throws Exception{
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM taikhoan WHERE id = ?");
        st.setInt(1, id);
        rs = st.executeQuery();
        if (rs.next()) {
            TaiKhoan obj = new TaiKhoan();
            obj.setId(rs.getInt("id"));
            obj.setMatKhau(rs.getString("matkhau"));
            obj.setTenTaiKhoan(rs.getString("taikhoan"));
            return obj;
        }
        return null;
    }
}
