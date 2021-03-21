/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BANGKE_MONAN_DTO;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.CallableStatement;
/**
 *
 * @author ADMIN
 */
public class DAO_BANGKE_MONAN {
    public List<BANGKE_MONAN_DTO> getListBKMonAnbyMAHD(int Ma_HD)
    {
        try
        {
            List<BANGKE_MONAN_DTO> bk_list = new ArrayList<>();
            SimpleDateFormat std = new SimpleDateFormat ("dd-MM-YYYY");
            String sql = "SELECT * FROM BANGKE_MONAN WHERE MAHD = ? ORDER BY NGAY";
            Connection conn = OracleConnection.Connection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, Ma_HD);
            
            // Thực thi câu lệnh SQL trả về đối tương ResultSet
            ResultSet rs = pre.executeQuery();
            
            while(rs.next())
            {
                BANGKE_MONAN_DTO bk = new BANGKE_MONAN_DTO();
                bk.setMAMON(rs.getInt(1));
                bk.setMAHD(rs.getInt(2));
                bk.setNGAY(std.format(rs.getDate(3)));
                bk.setSOLUONG(rs.getInt(4));
                bk.setTHANHTIEN(rs.getInt(5));
                
                bk_list.add(bk);
            }
            pre.close();
            conn.close();
            return bk_list;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean INSERT_BANGKE_MONAN(BANGKE_MONAN_DTO bk)
    {
        try
        {   
            Connection conn = OracleConnection.Connection();
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            //conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            SimpleDateFormat std = new SimpleDateFormat ("dd-MM-YYYY");
            String sql = "{CALL PROD_BK_MONAN_THANHTIEN(?,?,TO_DATE(?,'dd-MM-YY'),?)}";
            CallableStatement pre = conn.prepareCall(sql);
            pre.setInt(2, bk.getMAMON());
            pre.setInt(1, bk.getMAHD());
            pre.setString(3, bk.getNGAY());
            pre.setInt(4, bk.getSOLUONG());
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
        
    public boolean DELETE_BANGKE_MONAN (BANGKE_MONAN_DTO bk)
    {
        try
        {
            Connection conn = OracleConnection.Connection();
            String sql = "DELETE FROM BANGKE_MONAN WHERE MAMON=? AND MAHD=? AND NGAY=TO_DATE(?, 'dd-MM-YY')";

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, bk.getMAMON());
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
