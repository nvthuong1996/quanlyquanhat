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
public class Nguoi {
    private int id;
    private String ho;
    private String ten;
    private int tuoi;
    private String quequan;
    private String sdt;

    public Nguoi(int id, String ho, String ten, int tuoi, String quequan, String sdt) {
        this.id = id;
        this.ho = ho;
        this.ten = ten;
        this.tuoi = tuoi;
        this.quequan = quequan;
        this.sdt = sdt;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }



    public Nguoi() {
    }
    

    public int getId() {
        return id;
    }

    public String getHo() {
        return ho;
    }

    public String getTen() {
        return ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }
    
    
    
    
}
