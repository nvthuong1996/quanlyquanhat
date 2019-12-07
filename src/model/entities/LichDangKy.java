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
public class LichDangKy {
    private int id;
    private Date ngay;
    private NguoiDung nguoiDung;
    private String trangthai;
    CaLamViec caLamViec;

    public Date getNgay() {
        return ngay;
    }

    public CaLamViec getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
    }
    
    

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    
    public String getTrangthai() {
        return trangthai;
    }
    
    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public LichDangKy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
