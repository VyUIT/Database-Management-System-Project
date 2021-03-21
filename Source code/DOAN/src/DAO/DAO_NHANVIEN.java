/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.OracleConnection.Connection;
import DTO.LTK;
import DTO.NHANVIEN;
import Class.NHANVIEN_TT;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author HP
 */
public class DAO_NHANVIEN {
    public List<Integer> getListLoaiTaiKhoan() throws SQLException{
        List<Integer> ltk = new ArrayList<>();
       Connection conn = OracleConnection.Connection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT MALOAITK FROM LOAITAIKHOAN");
        while(rs.next()){
            int a = rs.getInt("MALOAITK");
            ltk.add(a);
        }
        st.close();
        conn.close();
        return ltk;
    }
    
    public String getTenNhanVien(int manv){
        try{
           Connection conn = OracleConnection.Connection();
            PreparedStatement prst = conn.prepareStatement("SELECT TENNV FROM NHANVIEN WHERE MANV = ?");
            prst.setInt(1,manv);
            ResultSet rs = prst.executeQuery();
            String tennv = null;
            while(rs.next()){
                tennv = rs.getString(1);
            }
            prst.close();
            conn.close();
            return tennv;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }  
    }
    public List<Integer> getListMaNV(){
        List<Integer> manv = new ArrayList<>();
        try{
           Connection conn = OracleConnection.Connection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MANV FROM NHANVIEN");
            while(rs.next()){
                int nv = rs.getInt(1);
                manv.add(nv);
            }
            st.close();
            conn.close();
            return manv;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<NHANVIEN_TT> listTTNhanVien(){
        List<NHANVIEN_TT> nv = new ArrayList<>();
        try{
           Connection conn = OracleConnection.Connection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MANV, TENNV FROM NHANVIEN");
            while(rs.next()){
                NHANVIEN_TT nhanvien = new NHANVIEN_TT(rs.getInt(1), rs.getString(2));
                nv.add(nhanvien);
            }
            st.close();
            conn.close();
            return nv;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<NHANVIEN> getListNhanVien() throws SQLException{
        List<NHANVIEN> nhanvien = new ArrayList<>();
       Connection conn = OracleConnection.Connection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT A.TENNV, A.NGAYSINH, A.GIOITINH, A.CMND, A.DIACHI,"
                + " A.SDT, A.NGAYVAOLAM, B.TENNV FROM NHANVIEN A LEFT JOIN NHANVIEN B ON A.MAQL = B.MANV");
        while(rs.next()){
            NHANVIEN nv = new NHANVIEN();
            nv.setTENNV(rs.getString(1));
            Date date = rs.getDate(2);
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-YYYY");
            String ngaysinh = dateformat.format(date);
            nv.setNAMSINH(ngaysinh);
            nv.setGIOITINH(rs.getString(3));
            nv.setCMND(rs.getString(4));
            nv.setDIACHI(rs.getString(5));
            nv.setSDT(rs.getString(6));
            date = rs.getDate(7);
            String ngayvaolam = dateformat.format(date);
            nv.setNGAYVAOLAM(ngayvaolam);
            nv.setTENQL(rs.getString(8));
            nhanvien.add(nv);
        }
        st.close();
        conn.close();
        return nhanvien;
    }
    
    public NHANVIEN getNhanVienByCMND(String cmnd) throws SQLException{
        NHANVIEN nv = new NHANVIEN();
       Connection conn = OracleConnection.Connection();
        PreparedStatement prst = conn.prepareStatement("SELECT a.MANV, a.TENNV, a.NGAYSINH, a.GIOITINH, a.CMND, a.DIACHI, a.SDT, a.EMAIL, "
                + "                     a.NGAYVAOLAM, a.TENTAIKHOAN,a.MATKHAU,a.MAQL, b.TENNV, TENLOAITK, a.MALOAITK"
                + "                     FROM NHANVIEN a INNER JOIN LOAITAIKHOAN  ON "
                + "                     LOAITAIKHOAN.MALOAITK = a.MALOAITK LEFT JOIN NHANVIEN b ON a.MAQL = b.MANV WHERE a.CMND = ?");
        prst.setString(1,cmnd);
        ResultSet rs = prst.executeQuery();
        while(rs.next()){
            nv.setMANV(rs.getInt(1));
            nv.setTENNV(rs.getString(2));
            nv.setGIOITINH(rs.getString(4));
            nv.setCMND(rs.getString(5));
            nv.setDIACHI(rs.getString(6));
            nv.setSDT(rs.getString(7));
            nv.setEMAIL(rs.getString(8));
            Date date = rs.getDate(3);
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-YYYY");
            String ngaysinh = dateformat.format(date);
            nv.setNAMSINH(ngaysinh);
            date = rs.getDate(9);
            String ngayvaolam = dateformat.format(date);
            nv.setNGAYVAOLAM(ngayvaolam);
            nv.setTENTAIKHOAN(rs.getString(10));
            nv.setMATKHAU(rs.getString(11));
            nv.setNGUOIQUANLY(rs.getInt(12));
            nv.setTENQL(rs.getString(13));
            nv.setTENLOAITK(rs.getString(14));
            nv.setMALOAITK(rs.getInt(15));
        }
        prst.close();
        conn.close();
        return nv;
    }
    
    public boolean findNhanVien(String CMND) throws SQLException{
       Connection conn = OracleConnection.Connection();
        PreparedStatement prst = conn.prepareStatement("SELECT COUNT(*) FROM NHANVIEN WHERE CMND = ?");
        prst.setString(1, CMND);
        ResultSet rs = prst.executeQuery();
        int count = 0;
        while(rs.next()){
            count = rs.getInt(1);
        }
        prst.close();
        conn.close();
        if(count >=1)
            return true;
        else 
            return false;
    }
    
    public boolean addNhanVien(NHANVIEN nv) throws SQLException{
       Connection conn = OracleConnection.Connection();
        PreparedStatement cast = conn.prepareStatement("INSERT INTO NHANVIEN(MANV,MAQL, MALOAITK, TENNV, NGAYSINH, GIOITINH, CMND, DIACHI, SDT, EMAIL,NGAYVAOLAM, TENTAIKHOAN, MATKHAU)"
                + "VALUES(SEQ_NV.NEXTVAL,?,?,?,TO_DATE(?,'dd-MM-YYYY'),?,?,?,?,?,TO_DATE(?,'dd-MM-YYYY'),?,?)");
        cast.setString(3,nv.getTENNV());
        cast.setString(4,nv.getNAMSINH());
        cast.setString(5,nv.getGIOITINH());
        cast.setString(6,nv.getCMND());
        cast.setString(7,nv.getDIACHI());
        cast.setString(8,nv.getSDT());
        cast.setString(9,nv.getEMAIL());
        cast.setString(10,nv.getNGAYVAOLAM());
        cast.setString(11,nv.getTENTAIKHOAN());
        cast.setString(12,nv.getMATKHAU());
        cast.setInt(1,nv.getNGUOIQUANLY());
        cast.setInt(2,nv.getMALOAITK());
        int rs = cast.executeUpdate();
        cast.close();
        conn.close();
        if(rs > 0)
            return true;
        else 
            return false;
    }
    
    public boolean updateNhanVien(NHANVIEN nv) throws SQLException{
       Connection conn = OracleConnection.Connection();
        PreparedStatement cast = conn.prepareStatement("UPDATE NHANVIEN SET MAQL = ?, MALOAITK = ?, TENNV = ?, NGAYSINH = TO_DATE(?, 'dd-MM-YYYY'),"
                + "                     GIOITINH = ?, DIACHI = ?, SDT = ?, EMAIL = ?, NGAYVAOLAM = TO_DATE(?,'dd-MM-YYYY'), TENTAIKHOAN = ?, MATKHAU = ? WHERE MANV = ?");

        cast.setInt(12,nv.getMANV());
        cast.setString(3,nv.getTENNV());
        cast.setString(4,nv.getNAMSINH());
        cast.setString(5,nv.getGIOITINH());
        cast.setString(6,nv.getDIACHI());
        cast.setString(7,nv.getSDT());
        cast.setString(8,nv.getEMAIL());
        cast.setString(9,nv.getNGAYVAOLAM());
        cast.setString(10,nv.getTENTAIKHOAN());
        cast.setString(11,nv.getMATKHAU());
        cast.setInt(1,nv.getNGUOIQUANLY());
        cast.setInt(2,nv.getMALOAITK());
        int rs = cast.executeUpdate();
        cast.close();
        conn.close();
        if(rs > 0)
            return true;
        else 
            return false;
    }
    
    public boolean deleteNhanVien(String cmnd) throws SQLException{
       Connection conn = OracleConnection.Connection();
        PreparedStatement cast = conn.prepareStatement("DELETE FROM NHANVIEN WHERE CMND = ?");
        cast.setString(1,cmnd);
        int rs = cast.executeUpdate();
        cast.close();
        conn.close();
        if(rs > 0)
            return true;
        else            
            return false;
    }
    
}
