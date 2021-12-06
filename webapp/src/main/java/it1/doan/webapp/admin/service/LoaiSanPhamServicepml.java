package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.AdminLoaiSanPhamDao;
import it1.doan.webapp.admin.service.impl.LoaiSanPhamService;
import it1.doan.webapp.model.LoaiSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoaiSanPhamServicepml implements LoaiSanPhamService {

    @Autowired
    AdminLoaiSanPhamDao adminLoaiSanPhamDao;

    @Override
    public String Insert(LoaiSanPham loaiSanPham) {
        return adminLoaiSanPhamDao.Insert(loaiSanPham);
    }

    @Override
    public String Update(LoaiSanPham loaiSanPham) {
        return adminLoaiSanPhamDao.Update(loaiSanPham);
    }

    @Override
    public LoaiSanPham Get(String maloaisp) {
        return adminLoaiSanPhamDao.Get(maloaisp);
    }

    @Override
    public String Delete(String maloaisp) {
        return adminLoaiSanPhamDao.Delete(maloaisp);
    }
}
