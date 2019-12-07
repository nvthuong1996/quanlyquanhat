/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Nguoi;
import model.entities.NguoiDung;
import model.entities.TaiKhoan;

/**
 *
 * @author thuongptitdev
 */
public interface NguoiDungDAO {

    void insert(NguoiDung nguoidung) throws Exception;
//    void update(NguoiDung nguoidung);
//    void deleteById(Integer id);

    NguoiDung findById(Integer id) throws Exception;
    
    NguoiDung findByAccount(String u,String p) throws Exception;
    
    NguoiDung findByIdAccount(Integer id) throws Exception;
//    List<NguoiDung> fillAll();
}
