/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO_NHANVIEN;
import DTO.NHANVIEN;
import Class.NHANVIEN_TT;
import java.util.List;

/**
 *
 * @author HP
 */
public class NHANVIEN_BUS {
    public List<Integer> getListLoaiTaiKhoan(){
        try{
            DAO_NHANVIEN nhanvien = new DAO_NHANVIEN();
            return nhanvien.getListLoaiTaiKhoan();
        }catch(Exception e){
            return null;
        }
    }
    
    public String getTenNhanVien(int manv){
        DAO_NHANVIEN nhanvien = new DAO_NHANVIEN();
        return nhanvien.getTenNhanVien(manv);
    }
    public List<NHANVIEN_TT> getTTNhanVien(){
        DAO_NHANVIEN nhanvien = new DAO_NHANVIEN();
        return nhanvien.listTTNhanVien();
    }
    public List<Integer> getListMaNV(){
        DAO_NHANVIEN nhanvien = new DAO_NHANVIEN();
        return nhanvien.getListMaNV();
    }
    public List<NHANVIEN> getListNhanVien(){
        try{
            DAO_NHANVIEN nhanvien = new DAO_NHANVIEN();
            return nhanvien.getListNhanVien();
        }catch(Exception e){
            return null;
        }
    }
    
    public NHANVIEN getNhanVienByCMND(String cmnd){
        try{
            DAO_NHANVIEN nhanvien = new DAO_NHANVIEN();
            return nhanvien.getNhanVienByCMND(cmnd);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean findNhanVien(String cmnd){
        try{
            DAO_NHANVIEN nhanvien = new DAO_NHANVIEN();
            return nhanvien.findNhanVien(cmnd);
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean addNhanVien(NHANVIEN nv){
        try{
            DAO_NHANVIEN nhanvien = new DAO_NHANVIEN();
            return nhanvien.addNhanVien(nv);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateNhanVien(NHANVIEN nv){
        try{
            DAO_NHANVIEN nhanvien = new DAO_NHANVIEN();
            return nhanvien.updateNhanVien(nv);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteNhanVien(String cmnd){
        try{
            DAO_NHANVIEN nhanvien = new DAO_NHANVIEN();
            return nhanvien.deleteNhanVien(cmnd);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
