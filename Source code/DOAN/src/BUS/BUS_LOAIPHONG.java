/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO_LOAIPHONG;
import DTO.LOAIPHONG;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BUS_LOAIPHONG {
    public List <LOAIPHONG> BUS_LOAIPHONG (){
    DAO_LOAIPHONG dlp = new DAO_LOAIPHONG();
    return dlp.getLoaiPhong();
    }
    
}
