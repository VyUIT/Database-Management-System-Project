/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO_KHACHHANG;
import DTO.KHACHHANG;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class KHACHHANG_BUS {
    public List<KHACHHANG> getListKhachHang(){
        DAO_KHACHHANG khachhang = new DAO_KHACHHANG();
        return khachhang.getListKhachHang();
    }
    
    public KHACHHANG getKhachHangByCMND(String CMND){
        DAO_KHACHHANG khachhang = new DAO_KHACHHANG();
        return khachhang.getKhachHangByCMND(CMND);
    }
    
    public boolean findKhachHang(String CMND){
        DAO_KHACHHANG khachhang = new DAO_KHACHHANG();
        return khachhang.findKhachHang(CMND);
    }
    
    public boolean addKhachHang(String hoten,String ngaysinh, String cmnd, String diachi, String sdt, String email, String gioitinh){
        DAO_KHACHHANG khachhang = new DAO_KHACHHANG();
        return khachhang.addKhachHang(hoten, ngaysinh, cmnd, diachi, sdt, email, gioitinh);
    }
    
    public boolean updateKhachHang(int ma,String hoten,String ngaysinh, String diachi, String sdt, String email, String gioitinh){
        DAO_KHACHHANG khachhang = new DAO_KHACHHANG();
        return khachhang.updateKhachHang(ma, hoten, ngaysinh, diachi, sdt, email, gioitinh);
    }
    
    public boolean deleteKhachHang(String CMND){
        DAO_KHACHHANG khachhang = new DAO_KHACHHANG();
        return khachhang.deleteKhachhang(CMND);

    }
}
