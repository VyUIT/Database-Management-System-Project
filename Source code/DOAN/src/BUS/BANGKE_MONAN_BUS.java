/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.BANGKE_MONAN_DTO;
import java.util.List;
import DAO.DAO_BANGKE_MONAN;


/**
 *
 * @author ADMIN
 */
public class BANGKE_MONAN_BUS {
    public List<BANGKE_MONAN_DTO> getListBKMonAnbyMAHD(int Ma_HD)
    {
            DAO_BANGKE_MONAN dao = new DAO_BANGKE_MONAN();
            return dao.getListBKMonAnbyMAHD(Ma_HD);
        
        
    }
    
    public boolean INSERT_BANGKE_MONAN(BANGKE_MONAN_DTO bk)
    {
        
            DAO_BANGKE_MONAN dao = new DAO_BANGKE_MONAN();
            return dao.INSERT_BANGKE_MONAN(bk);
        
        
    }
    
    public boolean DELETE_BANGKE_MONAN(BANGKE_MONAN_DTO bk)
    {
        
            DAO_BANGKE_MONAN dao = new DAO_BANGKE_MONAN();
            return dao.DELETE_BANGKE_MONAN(bk);
        
        
    }
}
