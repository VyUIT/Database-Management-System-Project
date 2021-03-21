/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO_CSVC;
import DTO.CSVC;
import java.util.List;

/**
 *
 * @author HP
 */
public class CSVC_BUS {
    public List<CSVC> getCSVC(){
        DAO_CSVC cs = new DAO_CSVC();
        return cs.getListCSVC();
    }
    
    public CSVC getCSVCByName(String ten){
        DAO_CSVC cs = new DAO_CSVC();
        return cs.getCSVCByName(ten);
    }
    
    public boolean findCSVCByName(String ten){
        DAO_CSVC cs = new DAO_CSVC();
        return cs.findCSVCByName(ten);
    }
    
    public boolean addCSVC(CSVC cs){
        DAO_CSVC csvc = new DAO_CSVC();
        return csvc.addCSVC(cs);
    }
    
    public boolean updateCSVC(CSVC cs){
        DAO_CSVC csvc = new DAO_CSVC();
        return csvc.updateCSVC(cs);
    }
    
    public boolean deleteCSVC(String ten){
        DAO_CSVC csvc = new DAO_CSVC();
        return csvc.deleteCSVC(ten);
    }
}
