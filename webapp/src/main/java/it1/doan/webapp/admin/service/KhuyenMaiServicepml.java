package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.AdminKhuyenMaiDao;
import it1.doan.webapp.admin.service.impl.KhuyenMaiService;
import it1.doan.webapp.model.KhuyenMai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KhuyenMaiServicepml implements KhuyenMaiService {

    @Autowired
    AdminKhuyenMaiDao adminKhuyenMaiDao;

    @Override
    public String Insert(KhuyenMai khuyenMai) {
        return adminKhuyenMaiDao.Insert(khuyenMai);
    }

    @Override
    public String Update(KhuyenMai khuyenMai) {
        return adminKhuyenMaiDao.Update(khuyenMai);
    }

    @Override
    public KhuyenMai Get(String makm) {
        return adminKhuyenMaiDao.Get(makm);
    }

    @Override
    public String Delete(String makm) {
        return adminKhuyenMaiDao.Delete(makm);
    }
}
