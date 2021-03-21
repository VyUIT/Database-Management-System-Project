/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO_HOADONTHANHTOAN;
import DTO.HOADONTHANHTOAN_DTO;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HOADONTHANHTOAN_BUS {
    public List<HOADONTHANHTOAN_DTO>  getListHD ()
    {
        
        DAO_HOADONTHANHTOAN dao = new DAO_HOADONTHANHTOAN();
        return dao.getListHD();
        
    }
    
    public HOADONTHANHTOAN_DTO getHDbyMAPHONG(int MAPHONG)
    {
        try
        {
            DAO_HOADONTHANHTOAN dao = new DAO_HOADONTHANHTOAN();
            return dao.getHDbyMAPHONG(MAPHONG);
        }
        catch(Exception e){return null;}
    }
    
   /* public boolean INSERT_HOADONTHANHTOAN (HOADONTHANHTOAN_DTO hd)
    {
        try
        {
            HOADONTHANHTOAN_DAO dao = new HOADONTHANHTOAN_DAO();
            return dao.INSERT_HOADONTHANHTOAN(hd);
        }
        catch(Exception e){return false;}
    }*/
    
    public boolean UPDATE_HOADONTHANHTOAN (HOADONTHANHTOAN_DTO hd)
    {
        try
        {
            DAO_HOADONTHANHTOAN dao = new DAO_HOADONTHANHTOAN();
            return dao.UPDATE_HOADONTHANHTOAN(hd);
        }
        catch(Exception e){return false;}
    }
    
    public boolean DELETE_HOADONTHANHTOAN (HOADONTHANHTOAN_DTO hd)
    {
        try
        {
            DAO_HOADONTHANHTOAN dao = new DAO_HOADONTHANHTOAN();
            return dao.DELETE_HOADONTHANHTOAN(hd);
        }
        catch(Exception e){return false;}
    }
    public boolean SEARCH_HOADONTHANHTOAN(int MaPhong)
    {
        if(getHDbyMAPHONG(MaPhong) == null)
            return false;
        else
            return true;
    }
    
    public boolean UPDATE_HOADONTHANHTOAN_TRANGTHAI (int Ma_HD)
    {
        try
        {
            DAO_HOADONTHANHTOAN dao = new DAO_HOADONTHANHTOAN();
            return dao.UPDATE_HOADONTHANHTOAN_TRANGTHAI(Ma_HD);
        }
        catch(Exception e){return false;}
    }
    public boolean Check_thanh_toan (int Ma_HD)
    {
        DAO_HOADONTHANHTOAN hd = new DAO_HOADONTHANHTOAN();
        return hd.CHECK_THANHTOAN(Ma_HD);
    }
    
    
    public int TIEN_PHONG(int Ma_HD)
    {
        DAO_HOADONTHANHTOAN hd = new DAO_HOADONTHANHTOAN();
        return hd.TIENPHONG(Ma_HD);
    }
    public int TIEN_DICHVU(int Ma_HD)
    {
        DAO_HOADONTHANHTOAN hd = new DAO_HOADONTHANHTOAN();
        return hd.TIENDV(Ma_HD);
    }
    public int TIEN_MONAN(int Ma_HD)
    {
        DAO_HOADONTHANHTOAN hd = new DAO_HOADONTHANHTOAN();
        return hd.TIENMA(Ma_HD);
    }
    public int TIEN_DENBU(int Ma_HD)
    {
        DAO_HOADONTHANHTOAN hd = new DAO_HOADONTHANHTOAN();
        return hd.TIENDENBU(Ma_HD);
    }
    public int TIEN_GIAM(int Ma_HD)
    {
        DAO_HOADONTHANHTOAN hd = new DAO_HOADONTHANHTOAN();
        return hd.TIENGIAM(Ma_HD);
    }
    public int TONG_TIEN(int Ma_HD)
    {
        DAO_HOADONTHANHTOAN hd = new DAO_HOADONTHANHTOAN();
        return hd.TONG_TIEN(Ma_HD);
    }
    
    
}
