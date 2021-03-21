/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import  DAO.*;
import DTO.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class BUS_DICHVU {


    
    public List<DICHVU> BUS_LoadData () 
    {

            DAO_DICHVU dichvu = new DAO_DICHVU();
            return dichvu.getListDV();
            
    }
    
    public boolean BUS_AddDICHVU (DICHVU dv) 
    {
        DAO_DICHVU ddv = new DAO_DICHVU();
        return ddv.ADDDICHVU(dv);
    }
    public boolean BUS_UPDATEDICHVU (DICHVU dv)
    {
        DAO_DICHVU ddv = new DAO_DICHVU();
        return ddv.UPDATEDICHVU(dv);
    }
    public DICHVU BUS_getDVbyten (String ten) 
    {
        DAO_DICHVU ddv = new DAO_DICHVU();
        return ddv.getDVbyten(ten);
    }
    public boolean BUS_DELETEDICHVU (DICHVU dv)
    {
        DAO_DICHVU ddv = new DAO_DICHVU ();
        return ddv.DELETEDICHVU(dv);
    }
}
