/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.TaiKhoan;

/**
 *
 * @author thuongptitdev
 */
public interface TaiKhoanDAO {

    void insert(TaiKhoan taikhoan) throws Exception;
    TaiKhoan findByTaiKhoan(String uString,String p) throws Exception;
    TaiKhoan findById(Integer id) throws Exception;
//    void update(TaiKhoan taikhoan);

//    void deleteById(Integer id);
//    List<TaiKhoan> fillAll();
}
