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
import java.util.Date;
import java.util.List;
import model.dao.LichLamViecDAO;
import model.entities.BangCong;
import model.entities.LichDangKy;
import model.entities.LichLamViec;

/**
 *
 * @author thuongptitdev
 */
public class LichLamViecImpl implements LichLamViecDAO{
    private Connection conn;

    public LichLamViecImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void accept(LichLamViec lichlamviec) throws Exception {
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "INSERT INTO lichlamviec "
                    + "(idlichdangky)"
                    + "VALUES "
                    + "(?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, lichlamviec.getLichDangKy().getId());
            
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    lichlamviec.setId(id);
                }
                LichDangKyImpl dao = new LichDangKyImpl(conn);
                dao.changeStatus(lichlamviec.getLichDangKy().getId(), "Da duyet");
                conn.commit();
            }
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        }
    }

    @Override
    public void checkin(LichLamViec lichlamviec,String gioden) throws Exception {
        PreparedStatement st = null;
       
        
        try {
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "UPDATE lichlamviec "
                    + "SET gioden = ?"
                    + "WHERE id = ?");
            st.setString(1, gioden);
            st.setInt(2, lichlamviec.getId());
            st.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }

    @Override
    public void checkout(LichLamViec lichlamviec,String giodi) throws Exception {
        PreparedStatement st = null;
        String gioden = lichlamviec.getGioden();
         
        try {
            String[] tmp = gioden.split("h");
            int h1 = Integer.parseInt(tmp[0]);
            int p1 = Integer.parseInt(tmp[1]);
            tmp = giodi.split("h");
            int h2 = Integer.parseInt(tmp[0]);
            int p2 = Integer.parseInt(tmp[1]);
            int tong = 0;
            if(h1 == h2){
                tong = p2 - p1;
            }else{
                tong = (60 - p1) + p2  + (h2-h1 -1)*60;
            }
            BangCong bc = new BangCong();
            bc.setHsluong(lichlamviec.getLichDangKy().getNguoiDung().getLuong());
            bc.setLichLamViec(lichlamviec);
            bc.setSogiolamviec(tong/60);
            
            BangCongImpl dao = new BangCongImpl(conn);
            dao.insert(bc);
            
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "UPDATE lichlamviec "
                    + "SET giodi = ?"
                    + "WHERE id = ?");
            st.setString(1, giodi);
            st.setInt(2, lichlamviec.getId());
            st.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }
    
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @Override
    public List<LichLamViec> fillAll() throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM lichlamviec");
        
        rs = st.executeQuery();
        
        List<LichLamViec> list = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            int idlichdangky = rs.getInt("idlichdangky");
            LichDangKyImpl dao = new LichDangKyImpl(conn);
            LichDangKy dk = dao.findById(idlichdangky);
            LichLamViec lm = new LichLamViec();
            lm.setLichDangKy(dk);
            lm.setId(id);
            lm.setGioden(rs.getString("gioden"));
            lm.setGiodi(rs.getString("giodi"));
            list.add(lm);
        }
        return list;
    }

}
