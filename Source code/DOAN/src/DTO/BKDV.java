/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Admin
 */
public class BKDV {

    /**
     * @return the madv
     */
    public int getMadv() {
        return madv;
    }

    /**
     * @param madv the madv to set
     */
    public void setMadv(int madv) {
        this.madv = madv;
    }

    /**
     * @return the mahd
     */
    public int getMahd() {
        return mahd;
    }

    /**
     * @param mahd the mahd to set
     */
    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    /**
     * @return the tien
     */
    public int getTien() {
        return tien;
    }

    /**
     * @param tien the tien to set
     */
    public void setTien(int tien) {
        this.tien = tien;
    }

    /**
     * @return the ngay
     */
    public String getNgay() {
        return ngay;
    }

    /**
     * @param ngay the ngay to set
     */
    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
    private int madv;
    private int mahd;
    private int tien;
    private String ngay;
    
}
