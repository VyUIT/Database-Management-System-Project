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
public class PHONGTRONG {
    private int MAPHONG;
    private int MALOAIPHONG;

    /**
     * @return the MAPHONG
     */
    public int getMAPHONG() {
        return MAPHONG;
    }

    /**
     * @param MAPHONG the MAPHONG to set
     */
    public void setMAPHONG(int MAPHONG) {
        this.MAPHONG = MAPHONG;
    }

    /**
     * @return the MALOAIPHONG
     */
    public int getMALOAIPHONG() {
        return MALOAIPHONG;
    }

    /**
     * @param MALOAIPHONG the MALOAIPHONG to set
     */
    public void setMALOAIPHONG(int MALOAIPHONG) {
        this.MALOAIPHONG = MALOAIPHONG;
    }

    public PHONGTRONG(int MAPHONG, int MALOAIPHONG) {
        this.MAPHONG = MAPHONG;
        this.MALOAIPHONG = MALOAIPHONG;
    }
    
}
