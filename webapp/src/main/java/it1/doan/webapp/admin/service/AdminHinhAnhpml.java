package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.AdminHinhAnhDao;
import it1.doan.webapp.model.HinhAnh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminHinhAnhpml implements AdminHinhAnhService{
    @Autowired
    AdminHinhAnhDao adminHinhAnhDao;
    @Override
    public List<HinhAnh> getallHinhAnh(String masp) {
        return adminHinhAnhDao.getallHinhAnh(masp);
    }

    @Override
    public void Insert(String masp, List<String> list) {
        adminHinhAnhDao.Insert(masp ,list);
    }

    @Override
    public void Delete(String maha) {
        adminHinhAnhDao.Delete(maha);
    }

    @Override
    public void Update(String maha ,String hinhanh) {
        adminHinhAnhDao.Update(maha,hinhanh);
    }
}
