/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO_BANGKE_DV;
import DTO.BANGKE_DV_DTO;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class BANGKE_DV_BUS {
    public List<BANGKE_DV_DTO> getListBangKeDVbyMAHD (int Ma_HD)
    {
        
            DAO_BANGKE_DV dao = new DAO_BANGKE_DV();
            return dao.getListBangKeDVbyMAHD(Ma_HD);
        
        
    }
    
    public boolean INSERT_BANGKE_DV(BANGKE_DV_DTO bk)
    {
        
            DAO_BANGKE_DV dao = new DAO_BANGKE_DV();
            return dao.INSERT_BANGKE_DV(bk);
        
        
    }
    
    public boolean DELETE_BANGKE_DV(BANGKE_DV_DTO bk)
    {
        
            DAO_BANGKE_DV dao = new DAO_BANGKE_DV();
            return dao.DELETE_BANGKE_DV(bk);
        
    }
}
