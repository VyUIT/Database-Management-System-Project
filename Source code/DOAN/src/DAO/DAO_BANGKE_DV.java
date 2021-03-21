/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BANGKE_DV_DTO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author ADMIN
 */
public class DAO_BANGKE_DV {
    public List<BANGKE_DV_DTO> getListBangKeDVbyMAHD (int Ma_HD)
    {
        try
        {
            List<BANGKE_DV_DTO> bk_list = new ArrayList<>();
            Connection conn = OracleConnection.Connection();
            SimpleDateFormat std = new SimpleDateFormat ("dd-MM-YYYY");
            String sql = "SELECT * FROM BANGKE_DV WHERE MAHD = ? ORDER BY NGAY";
            
            
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, Ma_HD);
            
            // Thực thi câu lệnh SQL trả về đối tương ResultSet
            ResultSet rs = pre.executeQuery();
            
            while(rs.next())
            {
                BANGKE_DV_DTO bk = new BANGKE_DV_DTO();
                bk.setMADV(rs.getInt(1));
                bk.setMAHD(rs.getInt(2));
                bk.setNGAY(std.format(rs.getDate(3)));
                bk.setTHANHTIEN(rs.getInt(4));
                
                bk_list.add(bk);
            }
            
            return bk_list;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean INSERT_BANGKE_DV(BANGKE_DV_DTO bk)
    {
        try
        {
            Connection conn = OracleConnection.Connection();
            String sql = "INSERT INTO BANGKE_DV (MADV,MAHD,NGAY,THANHTIEN) VALUES (?,?,TO_DATE(?,'dd-MM-YY'),?)";
            
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, bk.getMADV());
            pre.setInt(2, bk.getMAHD());
            pre.setString(3, bk.getNGAY());
            pre.setInt(4, bk.getTHANHTIEN());
            
            pre.execute();
            conn.close();
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean DELETE_BANGKE_DV(BANGKE_DV_DTO bk)
    {
        try
        {
            Connection conn = OracleConnection.Connection();
            String sql = "DELETE FROM BANGKE_DV WHERE MADV = ? AND MAHD = ? AND NGAY = TO_DATE(?, 'dd-MM-YY')";
            
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, bk.getMADV());
            pre.setInt(2, bk.getMAHD());
            pre.setString(3, bk.getNGAY());
            
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
