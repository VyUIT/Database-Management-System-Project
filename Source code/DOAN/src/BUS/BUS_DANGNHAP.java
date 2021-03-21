/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO_DANGNHAP;
import DTO.NHANVIEN;
import DTO.TAIKHOAN;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BUS_DANGNHAP {

    public NHANVIEN taikhoan (String ten, String mk)
    {
        DAO_DANGNHAP dn = new DAO_DANGNHAP();
        return dn.taikhoan(ten,mk);
    }
    
}
