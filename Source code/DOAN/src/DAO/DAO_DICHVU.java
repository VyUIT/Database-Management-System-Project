/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.DICHVU;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DAO_DICHVU {
    
    public List<DICHVU> getListDV () {
         List<DICHVU> dvs = new ArrayList <DICHVU> ();
            String sql = "SELECT * FROM DICHVU";
        try {
            Connection conn = OracleConnection.Connection ();
            Statement sta = conn.createStatement();

            // Thực thi câu lệnh SQL trả về đối tương ResultSet
            ResultSet rs = sta.executeQuery(sql);
            
            while(rs.next()) {
                DICHVU dv = new DICHVU ();
                dv.setMADV(rs.getInt(1));
                dv.setTENDV(rs.getString(2));
                dv.setGIADV(rs.getInt(3));
                dv.setTINHTRANG(rs.getString(4));
                dvs.add(dv);
            }
            return dvs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
            
        } 
    }
    public DICHVU getDVbyten (String TENDV) {
        try {
        Connection conn = OracleConnection.Connection ();
        DICHVU dv = new DICHVU ();
        String sql = "SELECT * FROM DICHVU WHERE TENDV = ?";
        PreparedStatement pre = conn.prepareStatement(sql);
            
        pre.setString(1, TENDV);
        
        // Thực thi câu lệnh sql trả về đối tượng ResultSet
        ResultSet rs = pre.executeQuery();
            
        // Gán dữ liệu vào đối tượng DV
        while (rs.next())
        {
            dv.setMADV(rs.getInt(1));
            dv.setTENDV(rs.getString(2));
            dv.setGIADV(rs.getInt(3));
            dv.setTINHTRANG(rs.getString(4));
        }
        pre.close();
        conn.close();
        
        return dv;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
                
    }
    public boolean ADDDICHVU (DICHVU dv) {
        
        try {
            Connection conn = OracleConnection.Connection();
            String sql = "INSERT INTO DICHVU (MADV,TENDV,GIADV,TINHTRANG) VALUES (SEQ_DV.NEXTVAL,?,?,?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, dv.getTENDV());
            pre.setInt(2, dv.getGIADV());
            pre.setString(3, dv.getTINHTRANG());
            
            pre.execute();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public boolean UPDATEDICHVU (DICHVU dv) {
    {
        try {
            Connection conn = OracleConnection.Connection();
            String sql = "UPDATE DICHVU SET TENDV = ?, GIADV = ?, TINHTRANG = ? WHERE MADV = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, dv.getTENDV());
            pre.setInt(2, dv.getGIADV());
            pre.setString(3, dv.getTINHTRANG());
            pre.setInt(4, dv.getMADV());
            
            int sl = pre.executeUpdate();
            conn.close ();
            pre.close();
            if (sl >0)
            {
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    }
     public boolean DELETEDICHVU (DICHVU dv) {
         try {
             Connection conn = OracleConnection.Connection();
             String sql = "DELETE FROM DICHVU WHERE MADV = ? OR TENDV = ?";
             PreparedStatement pre = conn.prepareStatement(sql);
             pre.setInt(1, dv.getMADV());
             pre.setString(2, dv.getTENDV());
             
             pre.execute();
             conn.close();
             pre.close();
             return true;
         } catch (SQLException e) 
         {
             e.printStackTrace();
             return false;
         }
     }
}
