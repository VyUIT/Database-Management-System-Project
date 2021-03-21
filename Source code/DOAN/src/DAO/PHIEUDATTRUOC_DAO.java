/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.OracleConnection.Connection;
import DTO.LOAIPHONG;
import DTO.PHIEUDATTRUOC;
import COMBOBOX.PHONGTRONG;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.CallableStatement;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
/**
 *
 * @author HP
 */
public class PHIEUDATTRUOC_DAO {
    public List<PHIEUDATTRUOC> getListPhieuDatTruoc(){
        List<PHIEUDATTRUOC> pdt = new ArrayList<>();
        try{
           Connection conn = OracleConnection.Connection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT A.MAKH, A.MANV, A.NGAYDEN, A.NGAYROI, A.MAPHONG, A.MAPHIEU"
                    + ", B.TENNV, C.HOTEN, A.TINHTRANG, A.SONGUOIDIKEM FROM PHIEUDATTRUOC A, NHANVIEN B, "
                    + "KHACHHANG C WHERE A.MAKH = C.MAKH AND A.MANV = B.MANV ORDER BY A.MAPHIEU ASC");
            while(rs.next()){
                PHIEUDATTRUOC dt = new PHIEUDATTRUOC();
                dt.setMAPHIEU(rs.getInt(6));
                dt.setMAKH(rs.getInt(1));
                dt.setMANV(rs.getInt(2));
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-YYYY");
                Date date = rs.getDate(3);
                String ngayden = dateformat.format(date);
                dt.setNGAYDEN(ngayden);
                date = rs.getDate(4);
                String ngayroi = dateformat.format(date);
                dt.setNGAYROI(ngayroi);
                dt.setMAPHONG(rs.getInt(5));
                dt.setTENNV(rs.getString(7));
                dt.setTENKH(rs.getString(8));
                dt.setTINHTRANG(rs.getString(9));
                dt.setSONGUOIDIKEM(rs.getInt(10));
                pdt.add(dt);
            }
            st.close();
            conn.close();
            return pdt;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<LOAIPHONG> getListLoaiPhong(){
        List<LOAIPHONG> lp = new ArrayList<>();
        try{
           Connection conn = OracleConnection.Connection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM LOAIPHONG");
            while(rs.next()){
                LOAIPHONG loai = new LOAIPHONG();
                loai.setMALOAIPHONG(rs.getInt(1));
                loai.setTENLOAIPHONG(rs.getString(2));
                loai.setSOLUONGNGUOI(rs.getInt(3));
                loai.setGIAPHONG(rs.getLong(4));
                lp.add(loai);
            }
            st.close();
            conn.close();
            return lp;
        }catch(Exception e){
            e.printStackTrace();
            return null;  
        }
    }
    
    public LOAIPHONG getTTLoaiPhong(int maphong){
        LOAIPHONG lp = new LOAIPHONG();
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement prst = conn.prepareStatement("SELECT  A.MALOAIPHONG, A.TENLOAIPHONG, A.SLNGUOI "
                    + "FROM LOAIPHONG A INNER JOIN PHONG B ON A.MALOAIPHONG = B.MALOAIPHONG WHERE MAPHONG = ?");
            prst.setInt(1,maphong);
            ResultSet rs = prst.executeQuery();
            while(rs.next()){
                lp.setMALOAIPHONG(rs.getInt(1));
                lp.setTENLOAIPHONG(rs.getString(2));
                lp.setSOLUONGNGUOI(rs.getInt(3));
            }
            prst.close();
            conn.close();
            return lp;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Integer> getPhongTrong(String ngayden, String ngayroi, int maphong){
        List<Integer> phong = new ArrayList<>();
        try{
            Connection conn = OracleConnection.Connection();
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            //conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            CallableStatement cal = conn.prepareCall("{CALL PRO_DANHSACH_PHONGTRONG(TO_DATE(?,'dd-MM-YYYY'),TO_DATE(?,'dd-MM-YYYY'),?,?)}");
            cal.setString(1,ngayden);
            cal.setString(2,ngayroi);
            cal.setInt(3, maphong);
            cal.registerOutParameter(4,OracleTypes.CURSOR);
            cal.execute();
            ResultSet rs = (ResultSet) cal.getObject(4);
            while(rs.next()){
                int n = rs.getInt(1);
                phong.add(n);
            }
            cal.close();
            conn.close();
            return phong;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Integer> getListPhongTrong(String ngayden, String ngayroi, int maphong){
        List<Integer> phong = new ArrayList<>();
        try{
            Connection conn = OracleConnection.Connection();
            PreparedStatement  prst = conn.prepareStatement("SELECT MAPHONG  FROM (\n" +
                "    SELECT MALOAIPHONG,MAPHONG FROM PHONG WHERE TINHTRANG <> 'SỬA CHỮA'\n" +
                "    MINUS\n" +
                "    (SELECT DISTINCT P.MALOAIPHONG, PDT.MAPHONG FROM PHIEUDATTRUOC PDT INNER JOIN PHONG P\n" +
                "      ON PDT.MAPHONG = P.MAPHONG WHERE (TO_DATE(?,'DD-MM-YYYY') <= NGAYDEN AND NGAYDEN <= TO_DATE(?,'DD-MM-YYYY'))\n" +
"                                    OR (TO_DATE(?,'DD-MM-YYYY') <= NGAYDEN AND NGAYROI <= TO_DATE(?,'DD-MM-YYYY'))\n" +
"                                    OR (TO_DATE(?,'DD-MM-YYYY') <= NGAYROI AND NGAYROI <= TO_DATE(?,'DD-MM-YYYY'))" +
                "    UNION\n" +
                "    SELECT DISTINCT P1.MALOAIPHONG, PNP.MAPHONG FROM PHIEUNHANPHONG PNP INNER JOIN PHONG P1\n" +
                "      ON PNP.MAPHONG = P1.MAPHONG WHERE ((TO_DATE(?,'DD-MM-YYYY') <= NGAYDEN) AND (NGAYDEN <= TO_DATE(?,'DD-MM-YYYY')))\n" +
"                                    OR (TO_DATE(?,'DD-MM-YYYY') <= NGAYDEN AND NGAYROI <= TO_DATE(?,'DD-MM-YYYY'))\n" +
"                                    OR (TO_DATE(?,'DD-MM-YYYY') <= NGAYROI AND NGAYROI <= TO_DATE(?,'DD-MM-YYYY'))" +
                "    )\n" +
                "  ) A\n" +
                "  WHERE MALOAIPHONG = ?");
            prst.setString(1, ngayden);
            prst.setString(2, ngayroi);
            prst.setString(3, ngayden);
            prst.setString(4, ngayroi);
            prst.setString(5, ngayden);
            prst.setString(6,ngayroi);
            prst.setString(7, ngayden);
            prst.setString(8, ngayroi);
            prst.setString(9, ngayden);
            prst.setString(10, ngayroi);
            prst.setString(11, ngayden);
            prst.setString(12,ngayroi);
            prst.setInt(13, maphong);
            ResultSet rs = prst.executeQuery();
            while(rs.next()){
                int phongtrong = rs.getInt(1);
                phong.add(phongtrong);
            }   
            prst.close();
            conn.close();
            return phong;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<PHIEUDATTRUOC> getPhieuDatTruoc(String ten){
        List<PHIEUDATTRUOC> dt = new ArrayList<>();
        try{
            Connection conn = OracleConnection.Connection();
            int maphong = Integer.parseInt(ten);
            PreparedStatement pst = conn.prepareStatement("SELECT A.MAKH, A.MANV,"
                    + " A.NGAYDEN, A.NGAYROI, A.MAPHONG, A.MAPHIEU" 
                    + ",B.TENNV, C.HOTEN, A.TINHTRANG,A.SONGUOIDIKEM FROM PHIEUDATTRUOC A, NHANVIEN B,"
                    + " KHACHHANG C WHERE C.MAKH = A.MAKH AND B.MANV = A.MANV"
                    + " AND (C.CMND = ? OR MAPHONG = ?)");
            pst.setString(1,ten);
            pst.setInt(2, maphong);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
               PHIEUDATTRUOC pdt = new PHIEUDATTRUOC();
               pdt.setMAKH(rs.getInt(1));
               pdt.setMANV(rs.getInt(2));
               SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-YYYY");
               Date date = rs.getDate(3);
               String ngayden = dateformat.format(date);
               pdt.setNGAYDEN(ngayden);
               date = rs.getDate(4);
               String ngayroi = dateformat.format(date);
               pdt.setNGAYROI(ngayroi);              
               pdt.setMAPHONG(rs.getInt(5));
               pdt.setMAPHIEU(rs.getInt(6));
               pdt.setTENNV(rs.getString(7));
               pdt.setTENKH(rs.getString(8));
               pdt.setTINHTRANG(rs.getString(9));
               pdt.setSONGUOIDIKEM(rs.getInt(10));
               dt.add(pdt);
           }
           pst.close();
           conn.close();
           return dt;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean findPhieuDatTruoc(String a){
        try{
           Connection conn = OracleConnection.Connection();
            int maphong = Integer.parseInt(a);
            PreparedStatement prst = conn.prepareStatement("SELECT COUNT(*) FROM PHIEUDATTRUOC "
                    + "INNER JOIN KHACHHANG ON PHIEUDATTRUOC.MAKH = KHACHHANG.MAKH"
                    + " WHERE KHACHHANG.CMND = ? OR PHIEUDATTRUOC.MAPHONG = ? ");
            prst.setString(1,a);
            prst.setInt(2, maphong);
            ResultSet rs = prst.executeQuery();
            int n = 0;
            while(rs.next()){
                n = rs.getInt(1);
            }
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
    
    public PHIEUDATTRUOC get1PhieuDatTruoc(int maphieunhan){
        PHIEUDATTRUOC pdt = new PHIEUDATTRUOC();
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement prst = conn.prepareStatement("SELECT * FROM PHIEUDATTRUOC WHERE MAPHIEU = ?");
            prst.setInt(1, maphieunhan);
            ResultSet rs = prst.executeQuery();
            while(rs.next()){
                pdt.setMAPHIEU(rs.getInt(1));
                pdt.setMAPHONG(rs.getInt(2));
                pdt.setMAKH(rs.getInt(3));
                pdt.setMANV(rs.getInt(4));
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-YYYY");
                Date date = rs.getDate(5);
                String ngayden = dateformat.format(date);
                pdt.setNGAYDEN(ngayden);
                date = rs.getDate(6);
                String ngayroi = dateformat.format(date);
                pdt.setNGAYROI(ngayroi);
                pdt.setTINHTRANG(rs.getString(7));
                pdt.setSONGUOIDIKEM(rs.getInt(8));
            }
            prst.close();
            conn.close();
            return pdt;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean addPhieuDatTruoc(PHIEUDATTRUOC pdt){
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement prst = conn.prepareStatement("INSERT INTO PHIEUDATTRUOC(MAPHIEU, MAPHONG, MAKH, MANV, "
                    + "NGAYDEN, NGAYROI, TINHTRANG, SONGUOIDIKEM) VALUES(SEQ_PHIEUDAT.NEXTVAL,?,?,?,TO_DATE(?,'dd-MM-YYYY'),TO_DATE(?,'dd-MM-YYYY'),'CHƯA NHẬN', ?)");
            prst.setInt(1, pdt.getMAPHONG());
            prst.setInt(2,pdt.getMAKH());
            prst.setInt(3, pdt.getMANV());
            prst.setString(4,pdt.getNGAYDEN());
            prst.setString(5,pdt.getNGAYROI());
            prst.setInt(6, pdt.getSONGUOIDIKEM());
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
    
    public boolean updatePhieuDatTruoc(PHIEUDATTRUOC pdt) throws SQLException{
            Connection conn = OracleConnection.Connection();
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            //conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            CallableStatement prst = conn.prepareCall("{CALL PRO_UPDATE_PHIEUDATTRUOC(?,?,?,?,TO_DATE(?,'dd-MM-YYYY'),TO_DATE(?,'dd-MM-YYYY'),?,?)}");
            prst.setInt(1,pdt.getMAPHIEU());
            prst.setInt(2, pdt.getMAPHONG());
            prst.setInt(3, pdt.getMAKH());
            prst.setInt(4, pdt.getMANV());
            prst.setString(5,pdt.getNGAYDEN());
            prst.setString(6, pdt.getNGAYROI());
            prst.setString(8, pdt.getTINHTRANG());
            prst.setInt(7,pdt.getSONGUOIDIKEM());
            int rs = prst.executeUpdate();
            conn.setAutoCommit(false);
            prst.close();
            conn.close();
            if(rs > 0)
                return true;
            else
                return false;
    }
    
    public boolean deletePhieuDatTruoc(int maphieu){
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement prst = conn.prepareStatement("DELETE FROM PHIEUDATTRUOC WHERE MAPHIEU = ?");
            prst.setInt(1,maphieu);
            int rs = prst.executeUpdate();
            prst.close();
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
