/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LOAIPHONG;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DAO_LOAIPHONG {
    
        public List<LOAIPHONG> getLoaiPhong ()
    {
        List<LOAIPHONG> dslp = new ArrayList <LOAIPHONG> ();
        String sql = "SELECT * FROM LOAIPHONG ORDER BY MALOAIPHONG";
        try
        {
            Connection conn = OracleConnection.Connection();
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                LOAIPHONG p = new LOAIPHONG();
                p.setMALOAIPHONG(rs.getInt(1));
                p.setTENLOAIPHONG(rs.getString(2));
                p.setSOLUONGNGUOI(rs.getInt(3));
                p.setGIAPHONG(rs.getInt(4));
                dslp.add(p);
            }
            return dslp;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
}
      
}
