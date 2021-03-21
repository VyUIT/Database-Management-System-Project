/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HOADON_CSVC_DTO;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;

/**
 *
 * @author ADMIN
 */
public class DAO_HOADON_CSVC {
    public List<HOADON_CSVC_DTO> getListCSVCbyMaHD (int Ma_HD)
    {
        try
        {
            List<HOADON_CSVC_DTO> hd_list = new ArrayList<>();
            String sql = "SELECT * FROM HOADON_CSVC WHERE MAHD = ?";
            Connection conn = OracleConnection.Connection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, Ma_HD);
            
            // Thực thi câu lệnh SQL trả về đối tương ResultSet
            ResultSet rs = pre.executeQuery();
            
            while(rs.next())
            {
                HOADON_CSVC_DTO hd = new HOADON_CSVC_DTO();
                hd.setMACSVC(rs.getInt(1));
                hd.setMAHD(rs.getInt(2));
                hd.setSOLUONG(rs.getInt(3));
                hd.setTHANHTIEN(rs.getInt(4));
                
                hd_list.add(hd);
            }
            
            return hd_list;
        }
        catch(SQLException e)        
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean INSERT_HOADON_CSVC(HOADON_CSVC_DTO hd)
    {
        try
        {
            String sql = "INSERT INTO HOADON_CSVC(MACSVC,MAHD,SOLUONG,THANHTIEN) VALUES (?,?,?,?)";
            Connection conn = OracleConnection.Connection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, hd.getMACSVC());
            pre.setInt(2, hd.getMAHD());
            pre.setInt(3, hd.getSOLUONG());
            pre.setInt(4, hd.getTHANHTIEN());
            
            pre.execute();
            conn.close();
            return true;
        }
        catch(SQLException e)        
        {
            e.printStackTrace();
            return false;
        }
    }
}
