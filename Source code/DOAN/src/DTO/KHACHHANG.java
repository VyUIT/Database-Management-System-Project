/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author HP
 */
public class KHACHHANG {
    private int MAKH;
    private String HOTEN, CMND, DIACHI, SDT, EMAIL, GIOITINH, NAMSINH;

    /**
     * @return the MAKH
     */
    public int getMAKH() {
        return MAKH;
    }
    
    public void setMAKH(int MAKH){
        this.MAKH = MAKH;
    }
    /**
     * @return the HOTEN
     */
    public String getHOTEN() {
        return HOTEN;
    }

    /**
     * @param HOTEN the HOTEN to set
     */
    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    /**
     * @return the CMND
     */
    public String getCMND() {
        return CMND;
    }

    /**
     * @param CMND the CMND to set
     */
    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    /**
     * @return the DIACHI
     */
    public String getDIACHI() {
        return DIACHI;
    }

    /**
     * @param DIACHI the DIACHI to set
     */
    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    /**
     * @return the SDT
     */
    public String getSDT() {
        return SDT;
    }

    /**
     * @param SDT the SDT to set
     */
    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    /**
     * @return the EMAIL
     */
    public String getEMAIL() {
        return EMAIL;
    }

    /**
     * @param EMAIL the EMAIL to set
     */
    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    /**
     * @return the GIOITINH
     */
    public String getGIOITINH() {
        return GIOITINH;
    }

    /**
     * @param GIOITINH the GIOITINH to set
     */
    public void setGIOITINH(String GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    /**
     * @return the NAMSINH
     */
    public String getNAMSINH() {
        return NAMSINH;
    }

    /**
     * @param NAMSINH the NAMSINH to set
     */
    public void setNAMSINH(String NAMSINH) {
        this.NAMSINH = NAMSINH;
    }

}
