/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NHANVIEN;
import DTO.TAIKHOAN;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class DAO_DANGNHAP {
    
    public List<NHANVIEN> gettaikhoan () {

            List <NHANVIEN> dstk = new ArrayList <NHANVIEN> ();
            String sql = "SELECT TENTAIKHOAN,MATKHAU,MALOAITK FROM NHANVIEN";    
        try {
            Connection conn = OracleConnection.Connection();

            Statement sta = conn.createStatement();
            
            ResultSet rs = sta.executeQuery(sql);
            
            if (rs.next())
            {
                NHANVIEN nv = new NHANVIEN();
                nv.setTENLOAITK(rs.getString(1).toString());
                nv.setMATKHAU(rs.getString(2).toString());
                nv.setMALOAITK(rs.getInt(3));
                
                dstk.add(nv);
            }
            return dstk;
            
          
            
            
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
        
        public NHANVIEN taikhoan (String ten, String mk) {
            try {
                Connection conn = OracleConnection.Connection();
                NHANVIEN nv = new NHANVIEN();
                String sql = "SELECT TENTAIKHOAN, MATKHAU,MALOAITK FROM NHANVIEN WHERE TENTAIKHOAN = ? and MATKHAU = ?";
                
                PreparedStatement pre = conn.prepareStatement(sql);
                
                pre.setString(1, ten);
                pre.setString(2, mk);
                
                ResultSet rs = pre.executeQuery();
                
                if (rs.next())
                {
                    nv.setTENLOAITK(rs.getString(1));
                    nv.setMATKHAU(rs.getString(2));
                    nv.setMALOAITK(rs.getInt(3));
                    
                    return nv;
                    
                }
                
                    
            } catch (SQLException e){
                e.printStackTrace();                     
        }
        return null;
        }
    
}
