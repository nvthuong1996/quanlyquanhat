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
import model.dao.NguoiDAO;
import model.entities.Nguoi;
import model.entities.NguoiDung;

/**
 *
 * @author thuongptitdev
 */
public class NguoiDaoImpl implements NguoiDAO {

    private Connection conn;

    public NguoiDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Nguoi nguoi) throws Exception{
        PreparedStatement st = null;

        // conn.setAutoCommit(false);
        st = conn.prepareStatement(
                "INSERT INTO nguoi "
                + "(ho,ten,tuoi,quequan,sdt,id) "
                + "VALUES "
                + "(?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);

        st.setString(1, nguoi.getHo());
        st.setString(2, nguoi.getTen());
        st.setInt(3, nguoi.getTuoi());
        st.setString(4, nguoi.getQuequan());
        st.setString(5, nguoi.getSdt());
        st.setInt(6, nguoi.getId());

        int rowsAffected = st.executeUpdate();
        if (rowsAffected > 0) {
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                nguoi.setId(id);
            }
            // conn.commit();

        } else {
            throw new Exception("Unexpected error! No rows affected!");
        }
    }

    @Override
    public void findById(NguoiDung obj) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM nguoi WHERE Id = ?");
        st.setInt(1, obj.getId());
        rs = st.executeQuery();
        if (rs.next()) {
            obj.setId(rs.getInt("id"));
            obj.setHo(rs.getString("ho"));
            obj.setTen(rs.getString("ten"));
            obj.setQuequan(rs.getString("quequan"));
            obj.setTuoi(rs.getInt("tuoi"));
            obj.setSdt(rs.getString("sdt"));
        }
    }

}
