/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.CaLamViec;

/**
 *
 * @author thuongptitdev
 */
public interface CaLamViecDAO {
    void insert(CaLamViec calamviec) throws Exception;
    void update(CaLamViec calamviec) throws Exception;
    void deleteById(Integer id) throws Exception;
    CaLamViec findById(Integer id) throws Exception;
    List<CaLamViec> fillAll() throws Exception;
}
