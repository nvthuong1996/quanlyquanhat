/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.util.Date;

/**
 *
 * @author thuongptitdev
 */
public class LichLamViec {
    private LichDangKy lichDangKy;
    private int id;
    private String gioden;
    private String giodi;
    
    public LichLamViec() {
    }

    public LichDangKy getLichDangKy() {
        return lichDangKy;
    }

    public void setLichDangKy(LichDangKy lichDangKy) {
        this.lichDangKy = lichDangKy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGioden() {
        return gioden;
    }

    public String getGiodi() {
        return giodi;
    }

    public void setGioden(String gioden) {
        this.gioden = gioden;
    }

    public void setGiodi(String giodi) {
        this.giodi = giodi;
    }
    
    
}
