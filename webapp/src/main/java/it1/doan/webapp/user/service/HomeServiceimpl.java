package it1.doan.webapp.user.service;

import it1.doan.webapp.common.ProductSearch;
import it1.doan.webapp.model.GioHang;
import it1.doan.webapp.model.HomeSanPham;
import it1.doan.webapp.user.dao.GiaoHangDao;
import it1.doan.webapp.user.dao.SanPhamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomeServiceimpl implements HomeService{

    @Autowired
    SanPhamDao sanPhamDao;
    @Autowired
    GiaoHangDao giaoHangDao;
    @Override
    public List<HomeSanPham> getSanPhamByType(String type) {
        return sanPhamDao.gettypesp(type);
    }

    @Override
    public List<GioHang> getghbynd(int id) {
        return giaoHangDao.getghbynd(id);
    }

    @Override
    public List<HomeSanPham> getspSearch(ProductSearch productSearch, int start, int end , boolean check[]) {
        return sanPhamDao.getspSearch(productSearch, start, end,check);
    }

    @Override
    public List<HomeSanPham> getallsp(ProductSearch productSearch,boolean check[]) {
        return sanPhamDao.getallsp(productSearch,check);
    }
}
