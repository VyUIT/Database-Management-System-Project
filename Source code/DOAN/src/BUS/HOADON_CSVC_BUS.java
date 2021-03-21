/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO_HOADON_CSVC;
import DTO.HOADON_CSVC_DTO;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HOADON_CSVC_BUS {
    public List<HOADON_CSVC_DTO> getListCSVCbyMaHD (int Ma_HD)
    {
        try
        {
            DAO_HOADON_CSVC dao = new DAO_HOADON_CSVC();
            return dao.getListCSVCbyMaHD(Ma_HD);
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public boolean INSERT_HOADON_CSVC(HOADON_CSVC_DTO hd)
    {
        try
        {
            DAO_HOADON_CSVC dao = new DAO_HOADON_CSVC();
            return dao.INSERT_HOADON_CSVC(hd);
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
