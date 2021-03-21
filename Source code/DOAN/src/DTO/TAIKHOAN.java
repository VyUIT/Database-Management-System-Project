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
public class TAIKHOAN {

    /**
     * @return the maloaitk
     */
    public int getMaloaitk() {
        return maloaitk;
    }

    /**
     * @param maloaitk the maloaitk to set
     */
    public void setMaloaitk(int maloaitk) {
        this.maloaitk = maloaitk;
    }

    /**
     * @return the ten
     */
    public String getTen() {
        return ten;
    }

    /**
     * @param ten the ten to set
     */
    public void setTen(String ten) {
        this.ten = ten;
    }

    /**
     * @return the mk
     */
    public String getMk() {
        return mk;
    }

    /**
     * @param mk the mk to set
     */
    public void setMk(String mk) {
        this.mk = mk;
    }
    private String ten,mk;
    private int maloaitk;
    
}
