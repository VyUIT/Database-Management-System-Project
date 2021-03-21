/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LOAIPHONG;
import DTO.PHONG;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author Admin
 */
public class DAO_PHONG {
    
    public List<PHONG> getListPhong ()
    {
        List<PHONG> phs = new ArrayList <PHONG> ();
        String sql = "SELECT * FROM PHONG ORDER BY MAPHONG";
        try
        {
            Connection conn = OracleConnection.Connection();
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                PHONG p = new PHONG();
                p.setMaphong(rs.getInt(1));
                p.setTinhtrang(rs.getString(2));
                p.setLoaiphong(rs.getInt(3));
                phs.add(p);
            }
            return phs;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
        public boolean ADDPHONG (PHONG p) 
        {
            try 
            {
                Connection conn = OracleConnection.Connection();
                String sql = "INSERT INTO PHONG (MAPHONG, TINHTRANG, MALOAIPHONG) VALUES (?,?,?)";
                PreparedStatement pre = conn.prepareStatement(sql);
                
                pre.setInt(1, p.getMaphong());
                pre.setString(2, p.getTinhtrang());
                pre.setInt(3,p.getLoaiphong());
                
                pre.execute();
                pre.close();
                conn.close();
                return true;
            } catch (SQLException e)
            {
                e.printStackTrace();
                return false;
            }
        }
    public boolean DeletePhong (PHONG p) {
        try {
            Connection conn = OracleConnection.Connection();           
            String sql = "DELETE FROM PHONG WHERE MAPHONG = ?";
            
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, p.getMaphong());
            
            pre.execute();
            
            pre.close();
            conn.close();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean UpdatePhong (PHONG p) {
        try {
            Connection conn = OracleConnection.Connection();
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            //conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            String sql = "UPDATE PHONG SET TINHTRANG = ?, MALOAIPHONG = ? WHERE MAPHONG = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1,p.getTinhtrang());
            pre.setInt(2, p.getLoaiphong());
            pre.setInt(3, p.getMaphong());
            int sl = pre.executeUpdate();
            pre.close();
            conn.close();
            if (sl>0)
            {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }
        return false;
    }
    
    public List<PHONG> FilterPhong (String ND, String NR) {
        List<PHONG> lp = new ArrayList <PHONG> ();
        
        
        try
        {
            Connection conn = OracleConnection.Connection();
            String sql = "SELECT MAPHONG,TINHTRANG,MALOAIPHONG  FROM (\n" +
"                SELECT MAPHONG,TINHTRANG,MALOAIPHONG FROM PHONG\n" +
"                MINUS\n" +
"                (SELECT DISTINCT PDT.MAPHONG, P.TINHTRANG,P.MALOAIPHONG FROM PHIEUDATTRUOC PDT INNER JOIN PHONG P\n" +
"                ON PDT.MAPHONG = P.MAPHONG WHERE (TO_DATE(?,'DD-MM-YYYY') <= NGAYDEN AND NGAYDEN <= TO_DATE(?,'DD-MM-YYYY'))\n" +
"                                    OR (TO_DATE(?,'DD-MM-YYYY') <= NGAYDEN AND NGAYROI <= TO_DATE(?,'DD-MM-YYYY'))\n" +
"                                    OR (TO_DATE(?,'DD-MM-YYYY') <= NGAYROI AND NGAYROI <= TO_DATE(?,'DD-MM-YYYY'))"
                    + " UNION " +
"                SELECT DISTINCT PNP.MAPHONG, P1.TINHTRANG,P1.MALOAIPHONG FROM PHIEUNHANPHONG PNP INNER JOIN PHONG P1\n" +
"                ON PNP.MAPHONG = P1.MAPHONG WHERE ((TO_DATE(?,'DD-MM-YYYY') <= NGAYDEN) AND (NGAYDEN <= TO_DATE(?,'DD-MM-YYYY')))\n" +
"                                    OR (TO_DATE(?,'DD-MM-YYYY') <= NGAYDEN AND NGAYROI <= TO_DATE(?,'DD-MM-YYYY'))\n" +
"                                    OR (TO_DATE(?,'DD-MM-YYYY') <= NGAYROI AND NGAYROI <= TO_DATE(?,'DD-MM-YYYY'))" +
"                ))";
            
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1,ND);
            pre.setString(2,NR);
            pre.setString(3,ND);
            pre.setString(4,NR);
            pre.setString(5,ND);
            pre.setString(6,NR);
            pre.setString(7,ND);
            pre.setString(8,NR);
            pre.setString(9,ND);
            pre.setString(10,NR);
            pre.setString(11,ND);
            pre.setString(12,NR);
            
            

            ResultSet rs = pre.executeQuery();
            
            while(rs.next())
            {
                PHONG p = new PHONG ();
                p.setMaphong(rs.getInt(1));
                p.setTinhtrang(rs.getString(2));
                p.setLoaiphong(rs.getInt(3)); 
                lp.add(p);
            }
            
            pre.close();
            conn.close();
            return lp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        
        
        
    }

    }
    
