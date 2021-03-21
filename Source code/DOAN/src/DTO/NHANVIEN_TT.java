/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author HP
 */
public class NHANVIEN_TT {
    private int MANV;
    private String TENNV;

    /**
     * @return the MANV
     */
    public int getMANV() {
        return MANV;
    }

    /**
     * @param MANV the MANV to set
     */
    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    /**
     * @return the TENNV
     */
    public String getTENNV() {
        return TENNV;
    }

    /**
     * @param TENNV the TENNV to set
     */
    public void setTENNV(String TENNV) {
        this.TENNV = TENNV;
    }
    
    public String toString(){
        return TENNV;
    }

    public NHANVIEN_TT(int MANV, String TENNV) {
        this.MANV = MANV;
        this.TENNV = TENNV;
    }
    
    
}
