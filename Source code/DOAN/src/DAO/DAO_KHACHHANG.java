/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import COMBOBOX.KHACHHANG_TT;
import static DAO.OracleConnection.Connection;
import DTO.KHACHHANG;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

/**
 *
 * @author HP
 */
public class DAO_KHACHHANG {
    public List<KHACHHANG> getListKhachHang(){
        List<KHACHHANG> kh = null;
        try{
           Connection conn = OracleConnection.Connection();
            kh = new ArrayList<>();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM KHACHHANG ORDER BY MAKH ASC");
            while(rs.next()){
                KHACHHANG khachhang = new KHACHHANG();
                khachhang.setMAKH(rs.getInt("MAKH"));
                khachhang.setHOTEN(rs.getString("HOTEN"));
                khachhang.setCMND(rs.getString("CMND"));
                khachhang.setGIOITINH(rs.getString("GIOITINH"));
                khachhang.setDIACHI(rs.getString("DIACHI"));
                Date date = rs.getDate("NGAYSINH");
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-YYYY");
                String ngaysinh = dateformat.format(date);
                khachhang.setNAMSINH(ngaysinh);
                khachhang.setEMAIL(rs.getString("EMAIL"));
                khachhang.setSDT(rs.getString("SDT"));
                kh.add(khachhang);
            }
           return kh;
        }catch(Exception e){
            return null;
        } 
    }
    
    public KHACHHANG getKhachHangByCMND(String CMND){
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement caSt = conn.prepareStatement("SELECT * FROM KHACHHANG WHERE CMND = ?");
            caSt.setString(1,CMND);
            ResultSet rs = caSt.executeQuery();
            KHACHHANG khachhang = new KHACHHANG();
            while(rs.next()){
                khachhang.setMAKH(rs.getInt("MAKH"));
                khachhang.setHOTEN(rs.getString("HOTEN"));
                khachhang.setCMND(rs.getString("CMND"));
                Date date = rs.getDate("NGAYSINH");
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-YYYY");
                String ngaysinh = dateformat.format(date);
                khachhang.setNAMSINH(ngaysinh);
                khachhang.setDIACHI(rs.getString("DIACHI"));
                khachhang.setSDT(rs.getString("SDT"));
                khachhang.setEMAIL(rs.getString("EMAIL"));
                khachhang.setGIOITINH(rs.getString("GIOITINH"));
            }
            caSt.close();
            conn.close();
            return khachhang;
        }catch(Exception e){
            return null;
        }
    }
    
    public boolean addKhachHang(String hoten,String ngaysinh, String cmnd, String diachi, String sdt, String email, String gioitinh){
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement caSt = conn.prepareStatement("INSERT INTO KHACHHANG(MAKH, HOTEN,"
                    + "NGAYSINH, CMND, DIACHI, SDT, EMAIL, GIOITINH)"
                    + "VALUES(SEQ_KH.NEXTVAL,?,TO_DATE(?,'dd-MM-YYYY'),?,?,?,?,?)");
            caSt.setString(1,hoten);
            caSt.setString(2,ngaysinh);
            caSt.setString(3,cmnd);
            caSt.setString(4,diachi);
            caSt.setString(5,sdt);
            caSt.setString(6,email);
            caSt.setString(7,gioitinh);
            int n = caSt.executeUpdate();
            caSt.close();
            conn.close();
            if(n > 0)
                return true;
            else 
                return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean findKhachHang(String CMND){
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement caSt = conn.prepareStatement("SELECT COUNT(*) FROM KHACHHANG WHERE CMND = ?");
            caSt.setString(1,CMND);
            ResultSet rs = caSt.executeQuery();
            int i = 0;
            while(rs.next()){
               i = rs.getInt(1);
            }
            if(i > 0)
                return true;
            else 
                return false;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean updateKhachHang(int ma,String hoten,String ngaysinh, String diachi, String sdt, String email, String gioitinh){
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement caSt= conn.prepareStatement("UPDATE KHACHHANG SET HOTEN = ?, NGAYSINH = TO_DATE(?, 'dd-MM-YYYY'), "
                        + "DIACHI = ?, SDT = ?,"
                        + "EMAIL = ?, GIOITINH =? WHERE MAKH = ?");
            caSt.setInt(7,ma);
            caSt.setString(1,hoten);
            caSt.setString(2,ngaysinh);
            caSt.setString(3,diachi);
            caSt.setString(4,sdt);
            caSt.setString(5,email);
            caSt.setString(6,gioitinh);
            int n = caSt.executeUpdate();
            caSt.close();
            conn.close();
            if(n > 0)
                return true;
            else 
                return false;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean deleteKhachhang(String CMND){
        try{
           Connection conn = OracleConnection.Connection();        
            PreparedStatement caSt = conn.prepareStatement("DELETE FROM KHACHHANG WHERE CMND = ?");
            caSt.setString(1,CMND);
            int n = caSt.executeUpdate();
            caSt.close();
            conn.close();
            if(n > 0)
                return true;
            else 
                return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }  
    }
}
