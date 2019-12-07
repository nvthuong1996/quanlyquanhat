/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Nguoi;
import model.entities.NguoiDung;

/**
 *
 * @author thuongptitdev
 */
public interface NguoiDAO {
    void insert(Nguoi user) throws Exception;
//    void update(Nguoi user);
//    void deleteById(Integer id);
      void findById(NguoiDung nguoidung) throws Exception;
//    List<Nguoi> fillAll();
}
