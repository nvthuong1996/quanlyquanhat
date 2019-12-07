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
import model.dao.BangCongDAO;
import model.dao.CaLamViecDAO;
import model.entities.BangCong;
import model.entities.CaLamViec;

/**
 *
 * @author thuongptitdev
 */
public class BangCongImpl implements BangCongDAO {

    private Connection conn;

    public BangCongImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insert(BangCong obj) throws Exception {
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "INSERT INTO bangcong "
                    + "(sogio,hesoluong,idlichlamviec)"
                    + "VALUES "
                    + "(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setDouble(1, obj.getSogiolamviec());
            st.setDouble(2, obj.getHsluong());
            st.setInt(3, obj.getLichLamViec().getId());
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
}
