/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO_MONAN;
import DTO.MONAN;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public class MONAN_BUS {
    public List<MONAN> getListMonAn(){
        DAO_MONAN monan = new DAO_MONAN();
        return monan.getListMonAn();
    }
    
    public MONAN getMonAnByName(String tenmon){
        DAO_MONAN monan = new DAO_MONAN();
        return monan.getMonAnByTen(tenmon);
    }
    
    public boolean findMonAn(String tenmon){
        DAO_MONAN monan = new DAO_MONAN();
        return monan.findMonAn(tenmon);
    }
    
    public boolean addMonAn(String tenmon, Long gia, String tinhtrang){
        DAO_MONAN monan = new DAO_MONAN();
        return monan.addMonAn(tenmon, gia, tinhtrang);
    }
    
    public boolean updateMonAn(int ma, String tenmon, Long gia, String tinhtrang){
        DAO_MONAN monan = new DAO_MONAN();
        return monan.updateMonAn(ma, tenmon, gia, tinhtrang);
    }
    
    public boolean deleteMonAn(String tenmon){
        DAO_MONAN monan = new DAO_MONAN();
        return monan.deleteMonAn(tenmon);
    }
}
