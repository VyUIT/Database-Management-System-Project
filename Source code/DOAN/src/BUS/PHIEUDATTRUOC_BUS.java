/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.PHIEUDATTRUOC_DAO;
import DTO.LOAIPHONG;
import DTO.PHIEUDATTRUOC;
import COMBOBOX.PHONGTRONG;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public class PHIEUDATTRUOC_BUS {
    public List<PHIEUDATTRUOC> getListPhieuDatTruoc(){
        PHIEUDATTRUOC_DAO pdt = new PHIEUDATTRUOC_DAO();
        return pdt.getListPhieuDatTruoc();
    }
    
    public LOAIPHONG getTTLoaiPhong(int maphong){
        PHIEUDATTRUOC_DAO pdt = new PHIEUDATTRUOC_DAO();
        return pdt.getTTLoaiPhong(maphong);
    }
    
    public List<LOAIPHONG> getListLoaiPhong(){
        PHIEUDATTRUOC_DAO pdt = new PHIEUDATTRUOC_DAO();
        return pdt.getListLoaiPhong();
    }
    
    public List<Integer> getListPhongTrong(String ngayden, String ngayroi, int maphong){
        PHIEUDATTRUOC_DAO pdt = new PHIEUDATTRUOC_DAO();
        return pdt.getListPhongTrong(ngayden, ngayroi, maphong);
    }
    
    public List<Integer> getPhongTrong(String ngayden, String ngayroi, int maphong){
        PHIEUDATTRUOC_DAO pdt = new PHIEUDATTRUOC_DAO();
        return pdt.getPhongTrong(ngayden, ngayroi, maphong);
    }
    public List<PHIEUDATTRUOC> getPhieuDatTruoc(String ten){
        PHIEUDATTRUOC_DAO pdt = new PHIEUDATTRUOC_DAO();
        return pdt.getPhieuDatTruoc(ten);
    }
    
    public boolean findPhieuDatTruoc(String ten){
        PHIEUDATTRUOC_DAO pdt = new PHIEUDATTRUOC_DAO();
        return pdt.findPhieuDatTruoc(ten);
    }
    
    public boolean addPhieuDatTruoc(PHIEUDATTRUOC pdt){
        PHIEUDATTRUOC_DAO phieudat = new PHIEUDATTRUOC_DAO();
        return phieudat.addPhieuDatTruoc(pdt);
    }
    
    public boolean updatePhieuDatTruoc(PHIEUDATTRUOC pdt) throws SQLException{
        PHIEUDATTRUOC_DAO phieudat = new PHIEUDATTRUOC_DAO();
        return phieudat.updatePhieuDatTruoc(pdt);
    }
    
    public boolean deletePhieuDatTruoc(int a){
        PHIEUDATTRUOC_DAO pdt = new PHIEUDATTRUOC_DAO();
        return pdt.deletePhieuDatTruoc(a);
    }
    
    public PHIEUDATTRUOC get1PhieuDatTruoc(int maphieu){
        PHIEUDATTRUOC_DAO pdt = new PHIEUDATTRUOC_DAO();
        return pdt.get1PhieuDatTruoc(maphieu);
    }
}
