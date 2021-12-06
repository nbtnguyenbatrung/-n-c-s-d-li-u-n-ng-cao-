package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.AdminSanPhamDao;
import it1.doan.webapp.admin.service.impl.SanPhamService;
import it1.doan.webapp.model.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SanPhamServicepml implements SanPhamService {

    @Autowired
    AdminSanPhamDao adminSanPhamDao;

    @Override
    public String Insert(SanPham sanPham) {
        return adminSanPhamDao.Insert(sanPham);
    }

    @Override
    public String Update(SanPham sanPham) {
        return adminSanPhamDao.Update(sanPham);
    }

    @Override
    public SanPham Get(String masp) {
        return adminSanPhamDao.Get(masp);
    }

    @Override
    public String Delete(String masp) {
        return adminSanPhamDao.Delete(masp);
    }
}
