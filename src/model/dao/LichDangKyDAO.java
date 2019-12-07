/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.LichDangKy;

/**
 *
 * @author thuongptitdev
 */
public interface LichDangKyDAO {
    void insert(LichDangKy lichdangky) throws Exception;
    void update(LichDangKy lichdangky) throws Exception;
    void deleteById(Integer id) throws Exception;
    LichDangKy findById(Integer id) throws Exception;
    List<LichDangKy> fillAllByIdNguoiDung(Integer id) throws Exception;
    List<LichDangKy> fillAll() throws Exception;
    void changeStatus(Integer id,String status) throws Exception;
}
