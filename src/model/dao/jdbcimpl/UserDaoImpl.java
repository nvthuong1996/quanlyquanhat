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
import model.dao.UserDAO;
import model.entities.User;

/**
 *
 * @author thuongptitdev
 */
public class UserDaoImpl implements UserDAO {

    private Connection conn;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(User user) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO users "
                    + "(ten) "
                    + "VALUES "
                    + "(?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, user.getTen());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    user.setId(id);
                }
            } else {
                throw new Exception("Unexpected error! No rows affected!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
