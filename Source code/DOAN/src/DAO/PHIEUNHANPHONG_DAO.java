/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.OracleConnection.Connection;
import java.util.List;
import DTO.PHIEUNHANPHONG;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;
/**
 *
 * @author HP
 */
public class PHIEUNHANPHONG_DAO {
    public List<PHIEUNHANPHONG> getListPhieuNhanPhong(){
        List<PHIEUNHANPHONG> pnp = new ArrayList<>();
        try{
           Connection conn = OracleConnection.Connection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT A.MAPHIEUNHAN, A.MAPHONG, A.SONGUOIDIKEM"
                    + ",A.NGAYDEN, A.NGAYROI, B.HOTEN, C.TENNV FROM"
                    + " PHIEUNHANPHONG A, NHANVIEN C, KHACHHANG B"
                    + " WHERE C.MANV = A.MANV AND B.MAKH = A.MAKH ORDER BY MAPHIEUNHAN ASC");
            while(rs.next()){
                PHIEUNHANPHONG pn = new PHIEUNHANPHONG();
                pn.setMAPHIEUNHAN(rs.getInt(1));
                pn.setMAPHONG(rs.getInt(2));
                pn.setSONGUOIDIKEM(rs.getInt(3));
                Date date = rs.getDate(4);
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-YYYY");
                String ngayden = dateformat.format(date);
                pn.setNGAYDEN(ngayden);
                date = rs.getDate(5);
                String ngayroi = dateformat.format(date);
                pn.setNGAYROI(ngayroi);
                pn.setTENKH(rs.getString(6));
                pn.setTENNV(rs.getString(7));
                pnp.add(pn);
            }
            st.close();
            conn.close();
            return pnp;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<PHIEUNHANPHONG> getListPhieuNhanPhongBy(String CMND){
        List<PHIEUNHANPHONG> pnp = new ArrayList<>();
        int maphong = Integer.parseInt(CMND);
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement prst = conn.prepareStatement("SELECT A.MAPHIEUNHAN, A.MAPHONG, A.SONGUOIDIKEM"
                    + ",A.NGAYDEN, A.NGAYROI, B.HOTEN, C.TENNV FROM"
                    + " PHIEUNHANPHONG A, NHANVIEN C, KHACHHANG B"
                    + " WHERE C.MANV = A.MANV AND B.MAKH = A.MAKH AND (B.CMND = ? OR A.MAPHONG = ?)");
            prst.setString(1,CMND);
            prst.setInt(2, maphong);
            ResultSet rs = prst.executeQuery();
            while(rs.next()){
                PHIEUNHANPHONG pn = new PHIEUNHANPHONG();
                pn.setMAPHIEUNHAN(rs.getInt(1));
                pn.setMAPHONG(rs.getInt(2));
                pn.setSONGUOIDIKEM(rs.getInt(3));
                Date date = rs.getDate(4);
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-YYYY");
                String ngayden = dateformat.format(date);
                pn.setNGAYDEN(ngayden);
                date = rs.getDate(5);
                String ngayroi = dateformat.format(date);
                pn.setNGAYROI(ngayroi);
                pn.setTENKH(rs.getString(6));
                pn.setTENNV(rs.getString(7));
                pnp.add(pn);
            }
            prst.close();
            conn.close();
            return pnp;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean findPhieuNhanPhong(String cmnd){
        int maphong = Integer.parseInt(cmnd);
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement prst = conn.prepareStatement("SELECT COUNT(*) FROM PHIEUNHANPHONG A INNER JOIN KHACHHANG B "
                    + "                         ON A.MAKH = B.MAKH WHERE(B.CMND = ? OR A.MAPHONG = ?)");
            prst.setString(1,cmnd);
            prst.setInt(2, maphong);
            ResultSet rs = prst.executeQuery();
            int n = 0;
            while(rs.next()){
                n = rs.getInt(1);
            }
            if(n > 0)
                return true;
            else
                return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public PHIEUNHANPHONG get1PhieuNhanPhong(int maphieunhan){
        PHIEUNHANPHONG pnp = new PHIEUNHANPHONG();
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement prst = conn.prepareStatement("SELECT * FROM PHIEUNHANPHONG WHERE MAPHIEUNHAN = ?");
            prst.setInt(1, maphieunhan);
            ResultSet rs = prst.executeQuery();
            while(rs.next()){
                pnp.setMAPHIEUNHAN(rs.getInt(1));
                pnp.setMAKH(rs.getInt(2));
                pnp.setMAPHONG(rs.getInt(3));
                pnp.setMANV(rs.getInt(4));
                pnp.setSONGUOIDIKEM(rs.getInt(5));
                Date date = rs.getDate(6);
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-YYYY");
                String ngayden = dateformat.format(date);
                pnp.setNGAYDEN(ngayden);
                date = rs.getDate(7);
                String ngayroi = dateformat.format(date);
                pnp.setNGAYROI(ngayroi);
            }
            prst.close();
            conn.close();
            return pnp;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean addPhieuNhanPhong(PHIEUNHANPHONG pnp){
        try{
          Connection conn = OracleConnection.Connection();
           PreparedStatement prst = conn.prepareStatement("INSERT INTO PHIEUNHANPHONG(MAPHIEUNHAN, MAKH, MAPHONG, MANV, SONGUOIDIKEM, NGAYDEN, NGAYROI)"
                   + "                  VALUES(SEQ_PHIEUNHAN.NEXTVAL,?,?,?,?,TO_DATE(?,'dd-MM-YYYY'),TO_DATE(?,'dd-MM-YYYY'))");
           prst.setInt(1, pnp.getMAKH());
           prst.setInt(2, pnp.getMAPHONG());
           prst.setInt(3, pnp.getMANV());
           prst.setInt(4, pnp.getSONGUOIDIKEM());
           prst.setString(5, pnp.getNGAYDEN());
           prst.setString(6, pnp.getNGAYROI());
           int n = prst.executeUpdate();
           prst.close();
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
    
    public boolean updatePhieuNhanPhong(PHIEUNHANPHONG pnp) throws SQLException{
       Connection conn = OracleConnection.Connection();
        CallableStatement cast = conn.prepareCall("{CALL PRO_UPDATE_PHIEUNHANPHONG(?,?,?,?,?,TO_DATE(?,'dd-MM-YYYY'), TO_DATE(?,'dd-MM-YYYY'))}");
        cast.setInt(1,pnp.getMAPHIEUNHAN());
        cast.setInt(2,pnp.getMAKH());
        cast.setInt(3, pnp.getMAPHONG());
        cast.setInt(4, pnp.getMANV());
        cast.setInt(5, pnp.getSONGUOIDIKEM());
        cast.setString(6,pnp.getNGAYDEN());
        cast.setString(7,pnp.getNGAYROI());
        int n = cast.executeUpdate();
        cast.close();
        conn.close();
        if(n > 0)
           return true;
       else
           return false;
    }
    
    public boolean deletePhieuNhanPhong(int maphieunhan){
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement prst = conn.prepareStatement("DELETE FROM PHIEUNHANPHONG WHERE MAPHIEUNHAN = ?");
            prst.setInt(1, maphieunhan);
            int n = prst.executeUpdate();
            prst.close();
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
