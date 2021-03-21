/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COMBOBOX;

/**
 *
 * @author HP
 */
public class KHACHHANG_TT {
    private int MAKH;
    private String TENKH;

    public KHACHHANG_TT(int MAKH, String TENKH) {
        this.MAKH = MAKH;
        this.TENKH = TENKH;
    }

    /**
     * @return the MAKH
     */
    public int getMAKH() {
        return MAKH;
    }

    /**
     * @param MAKH the MAKH to set
     */
    public void setMAKH(int MAKH) {
        this.MAKH = MAKH;
    }

    /**
     * @return the TENKH
     */
    public String getTENKH() {
        return TENKH;
    }

    /**
     * @param TENKH the TENKH to set
     */
    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }
    
    public String toString(){
        return TENKH;
    }
}
