/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import db.ConnectionFactory;
import java.sql.SQLException;
import model.dao.jdbcimpl.CaLamViecImpl;
import model.dao.jdbcimpl.LichDangKyImpl;
import model.dao.jdbcimpl.LichLamViecImpl;
import model.dao.jdbcimpl.NguoiDungDaoImpl;
import model.dao.jdbcimpl.UserDaoImpl;

/**
 *
 * @author thuongptitdev
 */
public class DAOFactory {
    public static UserDAO createUserDao(){
        return new UserDaoImpl(ConnectionFactory.getInstance().getConnection());
    }
    public static NguoiDungDAO createNguoiDungDAO(){
        return new NguoiDungDaoImpl(ConnectionFactory.getInstance().getConnection());
    }
    public static CaLamViecDAO createCaLamViecDAO(){
        return new CaLamViecImpl(ConnectionFactory.getInstance().getConnection());
    }
    
    public static LichDangKyDAO createLichDangKyDAO(){
        return new LichDangKyImpl(ConnectionFactory.getInstance().getConnection());
    }
    
    public static LichLamViecDAO createLichLamViecDAO(){
        return new LichLamViecImpl(ConnectionFactory.getInstance().getConnection());
    }
}
