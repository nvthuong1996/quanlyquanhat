/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyquanhatkaraoke.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.DAOFactory;
import model.dao.NguoiDungDAO;
import model.dao.UserDAO;
import model.entities.NguoiDung;
import model.entities.User;

/**
 *
 * @author thuongptitdev
 */
public class testdao {
    public static void main(String[] args) {
        User user = new User("Nguyen Van Thuong");
        NguoiDungDAO userDao = DAOFactory.createNguoiDungDAO();
        try {
            NguoiDung n = userDao.findByAccount("b","a");
            System.out.println("");
        } catch (Exception ex) {
            Logger.getLogger(testdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("");      
    }
}
