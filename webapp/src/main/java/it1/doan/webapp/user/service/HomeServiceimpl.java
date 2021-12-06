package it1.doan.webapp.user.service;

import it1.doan.webapp.model.HomeSanPham;
import it1.doan.webapp.model.SanPham;
import it1.doan.webapp.user.dao.SanPhamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomeServiceimpl implements HomeService{

    @Autowired
    SanPhamDao sanPhamDao;
    @Override
    public List<HomeSanPham> getSanPhamByType(String type) {
        return sanPhamDao.gettypesp(type);
    }
}
