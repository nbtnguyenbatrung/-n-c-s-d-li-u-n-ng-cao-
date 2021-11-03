package it1.doan.webapp.service;

import it1.doan.webapp.admin.dao.AdminSanPhamDao;
import it1.doan.webapp.dao.KhuyenMaiDAO;
import it1.doan.webapp.dao.SanPhamDAO;
import it1.doan.webapp.model.KhuyenMai;
import it1.doan.webapp.model.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class HomeServiceIpml implements HomeService {

    @Autowired
    KhuyenMaiDAO khuyenMaiDAO;

    @Autowired
    SanPhamDAO sanPhamDAO;

    @Override
    public List<KhuyenMai> getAllKhuyenMai() {
        return khuyenMaiDAO.getAllKhuyenMai();
    }

    @Override
    public List<SanPham> getAllSanPhamChild() {
        return sanPhamDAO.getAllSpTheoLoai("CHILD");
    }

    @Override
    public List<SanPham> getAllSanPhamMen() {
        return sanPhamDAO.getAllSpTheoLoai("MEN");
    }

    @Override
    public List<SanPham> getAllSanPhamWomen() {
        return sanPhamDAO.getAllSpTheoLoai("WOMEN");
    }

}
