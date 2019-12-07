/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thuongptitdev
 */
public class NguoiDung extends Nguoi {

    public static enum ROLE {
        LE_TAN(1),
        PHUC_VU(2),
        QUAN_LY(3);

        private int value;
        private static Map map = new HashMap<>();

        private ROLE(int value) {
            this.value = value;
        }

        static {
            for (ROLE pageType : ROLE.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static ROLE valueOf(int pageType) {
            return (ROLE) map.get(pageType);
        }

        public int getValue() {
            return value;
        }
    }

    private Double luong;
    private ROLE role;
    private TaiKhoan taikhoan;

    public Double getLuong() {
        return luong;
    }

    public ROLE getRole() {
        return role;
    }

    public TaiKhoan getTaikhoan() {
        return taikhoan;
    }

    public void setLuong(Double luong) {
        this.luong = luong;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public void setTaikhoan(TaiKhoan taikhoan) {
        this.taikhoan = taikhoan;
    }

}
