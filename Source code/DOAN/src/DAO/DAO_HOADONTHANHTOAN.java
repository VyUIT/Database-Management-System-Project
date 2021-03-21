/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.HOADONTHANHTOAN_DTO;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DAO_HOADONTHANHTOAN {
    public List<HOADONTHANHTOAN_DTO> getListHD ()
    {
        try
        { 
            List<HOADONTHANHTOAN_DTO> hd_list = new ArrayList <> ();
            SimpleDateFormat std = new SimpleDateFormat ("dd-MM-YYYY");
            String sql = "SELECT H.MAHD, H.MANV, H.MAPHIEUNHAN, N.MAPHONG, H.TIENPHONG, H.TIENDICHVU, H.TIENMONAN, H.TIENDENBU, H.TIENGIAM, H.NGAYDEN, H.NGAYROI, H.TONGTIEN, H.TRANGTHAI  FROM HOADONTHANHTOAN H JOIN PHIEUNHANPHONG N ON H.MAPHIEUNHAN = N.MAPHIEUNHAN";
            Connection conn = OracleConnection.Connection ();
            Statement sta = conn.createStatement();
            
            // Thực thi câu lệnh SQL trả về đối tương ResultSet
            ResultSet rs = sta.executeQuery(sql);
            
            while(rs.next())
            {
                HOADONTHANHTOAN_DTO hd = new HOADONTHANHTOAN_DTO();
                hd.setMAHD(rs.getInt(1));
                hd.setMANV(rs.getInt(2));
                hd.setMAPHIEUNHAN(rs.getInt(3));
                hd.setMaphong(rs.getInt(4));
                hd.setTIENPHONG(rs.getInt(5));
                hd.setTIENDICHVU(rs.getInt(6));
                hd.setTIENMONAN(rs.getInt(7));
                hd.setTIENDENBU(rs.getInt(8));
                hd.setTIENGIAM(rs.getInt(9));
                hd.setNGAYDEN(std.format(rs.getDate(10)));
                hd.setNGAYROI(std.format(rs.getDate(11)));
                hd.setTONGTIEN(rs.getInt(12));
                hd.setTRANGTHAI(rs.getNString(13));
                
                hd_list.add(hd);
            }
            
            return hd_list;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
   public HOADONTHANHTOAN_DTO getHDbyMAPHONG(int MAPHONG)
    {
        try
        {
            Connection conn = OracleConnection.Connection ();
            HOADONTHANHTOAN_DTO hd = new HOADONTHANHTOAN_DTO();
            SimpleDateFormat std = new SimpleDateFormat ("dd-MM-YYYY");
            String sql = "SELECT H.MAHD, H.MANV, H.MAPHIEUNHAN, N.MAPHONG, H.TIENPHONG, H.TIENDICHVU, H.TIENMONAN, H.TIENDENBU, H.TIENGIAM, H.NGAYDEN, H.NGAYROI, H.TONGTIEN, H.TRANGTHAI FROM HOADONTHANHTOAN H JOIN PHIEUNHANPHONG N ON H.MAPHIEUNHAN = N.MAPHIEUNHAN WHERE N.MAPHONG = ? AND TRANGTHAI = 'CHUA THANH TOAN'";

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, MAPHONG);

            // Thực thi câu lệnh sql trả về đối tượng ResultSet
            ResultSet rs = pre.executeQuery();

            // Gán dữ liệu vào đối tượng hd
            while(rs.next())
            {
                hd.setMAHD(rs.getInt(1));
                hd.setMANV(rs.getInt(2));
                hd.setMAPHIEUNHAN(rs.getInt(3));
                hd.setMaphong(rs.getInt(4));
                hd.setTIENPHONG(rs.getInt(5));
                hd.setTIENDICHVU(rs.getInt(6));
                hd.setTIENMONAN(rs.getInt(7));
                hd.setTIENDENBU(rs.getInt(8));
                hd.setTIENGIAM(rs.getInt(9));
                hd.setNGAYDEN(std.format(rs.getDate(10)));
                hd.setNGAYROI(std.format(rs.getDate(11)));
                hd.setTONGTIEN(rs.getInt(12));
                hd.setTRANGTHAI(rs.getNString(13));
            }
            
            pre.close();
            conn.close();
            return hd;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    
   
    public boolean UPDATE_HOADONTHANHTOAN (HOADONTHANHTOAN_DTO hd)
    {
        try
        {
            Connection conn = OracleConnection.Connection();
            String sql = "UPDATE HOADONTHANHTOAN SET TIENPHONG=?, TIENDICHVU=?, TIENMONAN=?, TIENDENBU=?, TIENGIAM=?, TONGTIEN=?, TRANGTHAI=?, WHERE MAHD = ?;";

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, hd.getTIENPHONG());
            pre.setInt(2, hd.getTIENDICHVU());
            pre.setInt(3, hd.getTIENMONAN());
            pre.setInt(4, hd.getTIENDENBU());
            pre.setInt(5, hd.getTIENGIAM());
            pre.setInt(6, hd.getTONGTIEN());
            pre.setString(7, hd.getTRANGTHAI());
            pre.setInt(8, hd.getMAHD());
            
            int sl = pre.executeUpdate();
            
            if (sl > 0)
            {
                conn.close();
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean DELETE_HOADONTHANHTOAN (HOADONTHANHTOAN_DTO hd)
    {
        try
        {
            Connection conn = OracleConnection.Connection();
            String sql = "DELETE FROM HOADONTHANHTOAN WHERE MAHD = ?";
        
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, hd.getMAHD());
            
            pre.execute();
            conn.close();
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }
    
    public boolean SEARCH_HOADONTHANHTOAN (int MaPhong)
    {
        try
        {
            HOADONTHANHTOAN_DTO hd = new HOADONTHANHTOAN_DTO();
            DAO_HOADONTHANHTOAN hds = new DAO_HOADONTHANHTOAN();
            hd = hds.getHDbyMAPHONG(MaPhong);
            if(hd.getMAHD()== 0)
                return false;
            else
                return true;
        }
        catch(Exception e)
        {
            return false;
        }        
    }
    
    public boolean UPDATE_HOADONTHANHTOAN_TRANGTHAI (int Ma_HD)
    {
        try
        {
            Connection conn = OracleConnection.Connection();
            String sql = "UPDATE HOADONTHANHTOAN SET TRANGTHAI = 'DA THANH TOAN' WHERE MAHD = Ma_HD;";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, Ma_HD);
            
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
    
    public boolean CHECK_THANHTOAN(int Ma_HD)
    {
        try
        {
            Connection conn = OracleConnection.Connection();
            CallableStatement stmt = conn.prepareCall("{call PROD_HOADONTHANHTOAN_TONGTIEN (?)}");
            stmt.setInt(1, Ma_HD);
  
            stmt.execute();
            
            conn.close();
            stmt.close();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
        public int TIENPHONG(int Ma_HD)
    {
        try {
        Connection conn = OracleConnection.Connection();
        CallableStatement stmt = conn.prepareCall("{? = call FUNC_HOADON_TIENPHONG (?)}");
        
        stmt.registerOutParameter(1, Types.INTEGER);
        stmt.setInt(2, Ma_HD);
        stmt.execute();
        
        int tp = stmt.getInt(1);
        conn.close();
        stmt.close();
        return tp;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
         public int TIENDV(int Ma_HD)
    {
        try {
        Connection conn = OracleConnection.Connection();
        CallableStatement stmt = conn.prepareCall("{? = call FUNC_HOADON_DICHVU (?)}");
        
        stmt.registerOutParameter(1, Types.INTEGER);
        stmt.setInt(2, Ma_HD);
        stmt.execute();
        
        int tdv = stmt.getInt(1);
        conn.close();
        stmt.close();
        return tdv;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
         public int TIENMA(int Ma_HD)
    {
        try {
        Connection conn = OracleConnection.Connection();
        CallableStatement stmt = conn.prepareCall("{? = call FUNC_HOADON_MONAN (?)}");
        
        stmt.registerOutParameter(1, Types.INTEGER);
        stmt.setInt(2, Ma_HD);
        stmt.execute();
        
        int tma = stmt.getInt(1);
        conn.close();
        stmt.close();
        return tma;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
          public int TIENDENBU(int Ma_HD)
    {
        try {
        Connection conn = OracleConnection.Connection();
        CallableStatement stmt = conn.prepareCall("{? = call FUNC_HOADON_DENBU (?)}");
        stmt.registerOutParameter(1, Types.INTEGER);
        stmt.setInt(2, Ma_HD);
        stmt.execute();
        
        int tdb = stmt.getInt(1);
        conn.close();
        stmt.close();
        return tdb;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
            public int TIENGIAM(int Ma_HD)
    {
        try {
        Connection conn = OracleConnection.Connection();
        CallableStatement stmt = conn.prepareCall("{? = call  FUNC_HOADON_TIENGIAM (?)}");
        stmt.registerOutParameter(1, Types.INTEGER);
        stmt.setInt(2, Ma_HD);
        stmt.execute();
        
        int tg = stmt.getInt(1);
        conn.close();
        stmt.close();
        return tg;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    public int TONG_TIEN(int Ma_HD)
    {
        try {
        Connection conn = OracleConnection.Connection();
        CallableStatement stmt = conn.prepareCall("{? = call FUNC_HOADONTHANHTOAN_TONGTIEN(?)}");
        
        stmt.registerOutParameter(1, Types.INTEGER);
        stmt.setInt(2, Ma_HD);
        stmt.execute();
        
        int tt = stmt.getInt(1);
        conn.close();
        stmt.close();
        return tt;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
