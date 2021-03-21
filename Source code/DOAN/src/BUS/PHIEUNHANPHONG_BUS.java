/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.PHIEUDATTRUOC_DAO;
import DAO.PHIEUNHANPHONG_DAO;
import DTO.LOAIPHONG;
import DTO.PHIEUNHANPHONG;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public class PHIEUNHANPHONG_BUS {
    public List<PHIEUNHANPHONG> getListPhieuNhanPhong(){
        PHIEUNHANPHONG_DAO pnp = new PHIEUNHANPHONG_DAO();
        return pnp.getListPhieuNhanPhong();
    }
    
    public List<PHIEUNHANPHONG> getListPhieuNhanPhongByCMND(String CMND){
        PHIEUNHANPHONG_DAO pnp = new PHIEUNHANPHONG_DAO();
        return pnp.getListPhieuNhanPhongBy(CMND);
    }
    
    public boolean findPhieuNhanPhong(String CMND){
        PHIEUNHANPHONG_DAO pnp = new PHIEUNHANPHONG_DAO();
        return pnp.findPhieuNhanPhong(CMND);
    }
    
    public PHIEUNHANPHONG getTTPhieuNhanPhong(int maphieunhan){
        PHIEUNHANPHONG_DAO pnp = new PHIEUNHANPHONG_DAO();
        return pnp.get1PhieuNhanPhong(maphieunhan);
    }
    
    public boolean addPhieuNhanPhong(PHIEUNHANPHONG pnp){
        PHIEUNHANPHONG_DAO np = new PHIEUNHANPHONG_DAO();
        return np.addPhieuNhanPhong(pnp);
    }
    
    public boolean updatePhieuNhanPhong(PHIEUNHANPHONG pnp) throws SQLException{
        PHIEUNHANPHONG_DAO np = new PHIEUNHANPHONG_DAO();
        return np.updatePhieuNhanPhong(pnp);
    }
    
    public boolean deletePhieuNhanPhong(int maphieu){
        PHIEUNHANPHONG_DAO pnp = new PHIEUNHANPHONG_DAO();
        return pnp.deletePhieuNhanPhong(maphieu);
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
}
