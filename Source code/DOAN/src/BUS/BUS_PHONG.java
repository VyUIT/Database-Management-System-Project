/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import  DAO.*;
import DTO.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BUS_PHONG {

    public List<PHONG> BUS_LoadPhong() {
        DAO_PHONG dp = new DAO_PHONG ();
        return dp.getListPhong(); 
    }
    
    public boolean BUS_ADDPHONG (PHONG p) {
        DAO_PHONG dp = new DAO_PHONG ();
        return dp.ADDPHONG(p);
    }
    
    public boolean BUS_DeletePhong (PHONG p) {
        DAO_PHONG dp = new DAO_PHONG ();
        return dp.DeletePhong(p);
    }
    
    public boolean BUS_UpdatePhong (PHONG p) {
        DAO_PHONG dp = new DAO_PHONG ();
        return dp.UpdatePhong(p);
    }
    
    public List <PHONG> FilterPhong (String ND, String NR)
    {
        DAO_PHONG dp = new DAO_PHONG ();
        return dp.FilterPhong(ND,NR);
    }
}
