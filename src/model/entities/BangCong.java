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
public class BangCong {
    private int id;
    private LichLamViec lichLamViec;
    private double sogiolamviec;
    private double hsluong;

    public BangCong() {
    }

    public double getHsluong() {
        return hsluong;
    }

    public void setHsluong(double hsluong) {
        this.hsluong = hsluong;
    }
    

    public int getId() {
        return id;
    }

    public LichLamViec getLichLamViec() {
        return lichLamViec;
    }

    public double getSogiolamviec() {
        return sogiolamviec;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLichLamViec(LichLamViec lichLamViec) {
        this.lichLamViec = lichLamViec;
    }

    public void setSogiolamviec(double sogiolamviec) {
        this.sogiolamviec = sogiolamviec;
    }
    
    
}
