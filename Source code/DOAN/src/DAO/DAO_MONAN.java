/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.OracleConnection.Connection;
import DTO.MONAN;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
/**
 *
 * @author HP
 */
public class DAO_MONAN {
    
    public List<MONAN>getListMonAn(){
       
        List <MONAN> dsma = new ArrayList <MONAN> ();
        String sql = "SELECT * FROM MONAN";
        
        try {
            Connection conn = OracleConnection.Connection();
            
            //conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            Statement sta = conn.createStatement();
            
            ResultSet rs = sta.executeQuery(sql);
            
            while (rs.next())
            {
                MONAN ma = new MONAN ();
                ma.setMamon(rs.getInt(1));
                ma.setTen(rs.getString(2));
                ma.setGia(rs.getInt(3));
                ma.setTinhtrang(rs.getString(4));
                
                dsma.add(ma);
            }
            return dsma;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public MONAN getMonAnByTen(String tenmon){
        try{
            Connection conn = OracleConnection.Connection();
            PreparedStatement caSt = conn.prepareStatement("SELECT * FROM MONAN WHERE TENMON = ?");
            caSt.setString(1,tenmon);
            ResultSet rs = caSt.executeQuery();
            MONAN monan = new MONAN();
            while(rs.next()){
                monan.setMamon(rs.getInt("MAMON"));
                monan.setTen(rs.getString("TENMON"));
                monan.setGia(rs.getInt("GIAMON"));
                monan.setTinhtrang(rs.getString("TINHTRANG"));
            }
            caSt.close();
            conn.close();
            return monan;
        }catch(Exception e){
            return null;
        }
    }
    
    public boolean addMonAn(String tenmon, Long gia, String tinhtrang){
        try{
            Connection conn = OracleConnection.Connection();
            PreparedStatement caSt = conn.prepareStatement("INSERT INTO MONAN(MAMON, TENMON, GIAMON, TINHTRANG) VALUES(SEQ_MA.NEXTVAL,?,?,?)");
            caSt.setString(1,tenmon);
            caSt.setLong(2,gia);
            caSt.setString(3,tinhtrang);
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
    
    public boolean findMonAn(String tenmon) {
        try{
            Connection conn = OracleConnection.Connection();
            PreparedStatement caSt = conn.prepareStatement("SELECT COUNT(*) FROM MONAN WHERE TENMON = ?");
            caSt.setString(1,tenmon);
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
            e.printStackTrace();
            return false;
        }  
    }
    public boolean deleteMonAn(String tenmon){
        try{
            Connection conn = OracleConnection.Connection();
            PreparedStatement caSt= conn.prepareStatement("DELETE FROM MONAN WHERE TENMON = ?");
            caSt.setString(1,tenmon);
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
    
    public boolean updateMonAn(int ma,String ten, Long gia, String tinhtrang){
        try{
            Connection conn = OracleConnection.Connection();
            Thread.sleep(4000);
            PreparedStatement caSt= conn.prepareStatement("UPDATE MONAN SET TENMON = ?, GIAMON = ?, TINHTRANG = ? WHERE MAMON = ?");
            caSt.setInt(4, ma);
            caSt.setString(1,ten);
            caSt.setLong(2, gia);
            caSt.setString(3,tinhtrang);
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
