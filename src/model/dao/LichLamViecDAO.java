/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.Date;
import java.util.List;
import model.entities.LichLamViec;

/**
 *
 * @author thuongptitdev
 */
public interface LichLamViecDAO {
    void accept(LichLamViec lichlamviec) throws Exception;
    void checkin(LichLamViec lichlamviec,String gioden) throws Exception;
    void checkout(LichLamViec lichlamviec,String giodi) throws Exception;
    List<LichLamViec> fillAll() throws Exception;
}
