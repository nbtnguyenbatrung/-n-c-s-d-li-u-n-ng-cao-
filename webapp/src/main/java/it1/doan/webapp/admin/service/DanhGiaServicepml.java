package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.AdminBinhLuanDao;
import it1.doan.webapp.admin.dao.AdminDanhGiaDao;
import it1.doan.webapp.model.BinhLuan;
import it1.doan.webapp.model.DanhGia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DanhGiaServicepml implements DanhGiaService{

    @Autowired
    AdminDanhGiaDao adminDanhGiaDao;
    @Autowired
    AdminBinhLuanDao adminBinhLuanDao;
    @Override
    public List<DanhGia> getAllDanhGia(String masp) {
        return adminDanhGiaDao.getAllDanhgia(masp);
    }

    @Override
    public List<BinhLuan> getAllbl(String masp, int sosao, String bl, int start, int end) {
        return adminBinhLuanDao.getAllbl(masp, sosao, bl, start, end);
    }
}
