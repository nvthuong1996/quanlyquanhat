/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

/**
 *
 * @author thuongptitdev
 */
public class CaLamViec {
    private int id;
    private int gioBatDau;
    private int phutBatDau;
    private int gioKetThuc;
    private int phutKetThuc;
    private String ten;

    public CaLamViec(int id, int gioBatDau, int phutBatDau, int gioKetThuc, int phutKetThuc, String ten) {
        this.id = id;
        this.gioBatDau = gioBatDau;
        this.phutBatDau = phutBatDau;
        this.gioKetThuc = gioKetThuc;
        this.phutKetThuc = phutKetThuc;
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public CaLamViec() {
    }

    public int getId() {
        return id;
    }

    public int getGioBatDau() {
        return gioBatDau;
    }

    public int getPhutBatDau() {
        return phutBatDau;
    }

    public int getGioKetThuc() {
        return gioKetThuc;
    }

    public int getPhutKetThuc() {
        return phutKetThuc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGioBatDau(int gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public void setPhutBatDau(int phutBatDau) {
        this.phutBatDau = phutBatDau;
    }

    public void setGioKetThuc(int gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public void setPhutKetThuc(int phutKetThuc) {
        this.phutKetThuc = phutKetThuc;
    }
}
