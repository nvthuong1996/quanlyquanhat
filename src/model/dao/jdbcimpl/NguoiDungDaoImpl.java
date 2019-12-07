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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.NguoiDungDAO;
import model.entities.Nguoi;
import model.entities.NguoiDung;
import model.entities.NhanVienLeTan;
import model.entities.NhanVienPhucVu;
import model.entities.QuanLy;
import model.entities.TaiKhoan;

/**
 *
 * @author thuongptitdev
 */
public class NguoiDungDaoImpl implements NguoiDungDAO {

    private Connection conn;

    public NguoiDungDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(NguoiDung nguoidung) throws Exception {
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);
            TaiKhoan taikhoan = nguoidung.getTaikhoan();
            new TaiKhoanDaoImpl(conn).insert(taikhoan);
            nguoidung.setId(taikhoan.getId());

            st = conn.prepareStatement(
                    "INSERT INTO nguoidung"
                    + "(luong,role,id) "
                    + "VALUES "
                    + "(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setDouble(1, nguoidung.getLuong());
            st.setInt(2, nguoidung.getRole().getValue());
            st.setInt(3, nguoidung.getId());

            NguoiDaoImpl nguoiDao = new NguoiDaoImpl(conn);
            nguoiDao.insert(nguoidung);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    nguoidung.setId(id);
                }
                conn.commit();

            } else {
                throw new Exception("Unexpected error! No rows affected!");
            }
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            throw e;
        }
    }

    @Override
    public NguoiDung findById(Integer id) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM nguoidung WHERE Id = ?");
        st.setInt(1, id);
        rs = st.executeQuery();
        NguoiDung obj = null;
        if (rs.next()) {
            NguoiDung.ROLE role = NguoiDung.ROLE.valueOf(rs.getInt("role"));
            if(role  == NguoiDung.ROLE.LE_TAN){
                obj = new NhanVienLeTan();
            }else if(role  == NguoiDung.ROLE.PHUC_VU){
                obj = new NhanVienPhucVu();
            }else{
                obj = new QuanLy();
            }
            obj.setId(rs.getInt("id"));
            obj.setLuong(rs.getDouble("luong"));
            obj.setRole(NguoiDung.ROLE.valueOf(rs.getInt("role")));
            return obj;
        }
        return null;
    }

    @Override
    public NguoiDung findByAccount(String u,String p) throws Exception {
        TaiKhoanDaoImpl taikhoandao = new TaiKhoanDaoImpl(conn);
        TaiKhoan find = taikhoandao.findByTaiKhoan(u,p);
        if(find == null){
            return null;
        }
        NguoiDung nguoidung = this.findById(find.getId());
        
        NguoiDaoImpl nguoidao = new NguoiDaoImpl(conn);
        nguoidao.findById(nguoidung);
        nguoidung.setTaikhoan(find);
        return nguoidung;
    }
    
    public NguoiDung findByIdAccount(Integer id) throws Exception {
        TaiKhoanDaoImpl taikhoandao = new TaiKhoanDaoImpl(conn);
        TaiKhoan find = taikhoandao.findById(id);
        if(find == null){
            return null;
        }
        NguoiDung nguoidung = this.findById(find.getId());
        
        NguoiDaoImpl nguoidao = new NguoiDaoImpl(conn);
        nguoidao.findById(nguoidung);
        nguoidung.setTaikhoan(find);
        return nguoidung;
    }

}
