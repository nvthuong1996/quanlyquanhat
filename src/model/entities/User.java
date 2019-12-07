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
public class User {
    private String ten;
    private int id;

    public User(String ten) {
        this.ten = ten;
    }

    public User() {
    }

    public String getTen() {
        return ten;
    }

    public int getId() {
        return id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
