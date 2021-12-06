package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.AdminDonHangDao;
import it1.doan.webapp.admin.service.impl.DonHangService;
import it1.doan.webapp.model.DonHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DonHangServicepml implements DonHangService {

    @Autowired
    AdminDonHangDao adminDonHangDao;
    @Override
    public DonHang Get(String mahd, String masp, String masize) {
        return adminDonHangDao.Get(mahd,masp,masize);
    }

    @Override
    public void Insert(int trangthai, List<String> list) {
        adminDonHangDao.Insert(trangthai,list);
    }
}
