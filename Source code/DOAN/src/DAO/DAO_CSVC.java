/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import java.sql.*;
import static DAO.OracleConnection.Connection;
import DTO.CSVC;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author HP
 */
public class DAO_CSVC {
    public List<CSVC> getListCSVC(){
        List<CSVC> csvc = new ArrayList<>();
        try{
            Connection conn = OracleConnection.Connection();
                    
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CSVC");
            while(rs.next()){
                CSVC coso = new CSVC();
                coso.setMACSVC(rs.getInt("MACSVC"));
                coso.setTENCSVC(rs.getString("TENCSVC"));
                coso.setGIACSVC(rs.getInt("GIACSVC"));
                csvc.add(coso);
            }
            st.close();
            conn.close();
            return csvc;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public CSVC getCSVCByName(String ten){
        CSVC csvc = new CSVC();
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement peSt = conn.prepareStatement("SELECT * FROM CSVC WHERE TENCSVC = ?");
            peSt.setString(1, ten);
            ResultSet rs = peSt.executeQuery();
            while(rs.next()){
                csvc.setMACSVC(rs.getInt("MACSVC"));
                csvc.setTENCSVC(rs.getString("TENCSVC"));
                csvc.setGIACSVC(rs.getInt("GIACSVC"));
            }
            peSt.close();
            conn.close();
            return csvc;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean findCSVCByName(String ten){
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement peSt = conn.prepareStatement("SELECT COUNT(*) FROM CSVC WHERE TENCSVC = ?");
            peSt.setString(1, ten);
            ResultSet rs = peSt.executeQuery();
            int count = 0;
            while(rs.next()){
                count = rs.getInt(1);
            }
            if(count >= 1)
                return true;
            else 
                return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addCSVC(CSVC cs){
        try{
            System.out.println(cs.getGIACSVC());
           Connection conn = OracleConnection.Connection();
            PreparedStatement peSt = conn.prepareStatement("INSERT INTO CSVC(MACSVC, TENCSVC, GIACSVC) VALUES(SEQ_CSVC.NEXTVAL,?,?)");
            peSt.setString(1, cs.getTENCSVC());
            peSt.setLong(2,cs.getGIACSVC());
            int n = peSt.executeUpdate();
            peSt.close();
            conn.close();
            if(n > 0 )
                return true;
            else
                return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateCSVC(CSVC cs){
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement pest = conn.prepareStatement("UPDATE CSVC SET TENCSVC = ?, GIACSVC = ? WHERE MACSVC = ?");
            pest.setString(1, cs.getTENCSVC());
            pest.setLong(2,cs.getGIACSVC());
            pest.setInt(3, cs.getMACSVC());
            int rs = pest.executeUpdate();
            pest.close();
            conn.close();
            if(rs > 0)
                return true;
            else
                return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteCSVC(String ten){
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement pest = conn.prepareStatement("DELETE FROM CSVC WHERE TENCSVC = ?");
            pest.setString(1, ten);
            int rs = pest.executeUpdate();
            pest.close();
            conn.close();
            if(rs > 0)
                return true;
            else
                return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
