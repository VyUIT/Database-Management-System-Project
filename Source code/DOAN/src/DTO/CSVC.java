/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author HP
 */
public class CSVC {
    private int MACSVC;
    private String TENCSVC;
    private int GIACSVC;

    public int getMACSVC() {
        return MACSVC;
    }

    public String getTENCSVC() {
        return TENCSVC;
    }

    public int getGIACSVC() {
        return GIACSVC;
    }

    public void setMACSVC(int MACSVC) {
        this.MACSVC = MACSVC;
    }

    public void setTENCSVC(String TENCSVC) {
        this.TENCSVC = TENCSVC;
    }

    public void setGIACSVC(int GIACSVC) {
        this.GIACSVC = GIACSVC;
    }

}