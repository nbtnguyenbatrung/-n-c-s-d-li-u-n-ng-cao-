package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.AdminSanPhamSizeDao;
import it1.doan.webapp.admin.service.impl.SanphamSizeService;
import it1.doan.webapp.model.SanPhamSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SanphamSizeServicepml implements SanphamSizeService {

    @Autowired
    AdminSanPhamSizeDao adminSanPhamSizeDao;

    @Override
    public String Insert(SanPhamSize sanPhamSize) {
        return adminSanPhamSizeDao.Insert(sanPhamSize);
    }

    @Override
    public String Update(SanPhamSize sanPhamSize) {
        return adminSanPhamSizeDao.Update(sanPhamSize);
    }

    @Override
    public SanPhamSize Get(String masize, String masp) {
        return adminSanPhamSizeDao.Get(masize,masp);
    }

    @Override
    public String Delete(String masize, String masp) {
        return adminSanPhamSizeDao.Delete(masize,masp);
    }
}
