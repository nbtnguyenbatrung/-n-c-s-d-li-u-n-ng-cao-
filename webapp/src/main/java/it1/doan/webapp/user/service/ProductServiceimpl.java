package it1.doan.webapp.user.service;

import it1.doan.webapp.model.BinhLuan;
import it1.doan.webapp.model.HinhAnh;
import it1.doan.webapp.model.HomeSanPham;
import it1.doan.webapp.model.Size;
import it1.doan.webapp.user.dao.ProductDao;
import it1.doan.webapp.user.dao.SanPhamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceimpl implements ProductService{
    @Autowired
    SanPhamDao sanPhamDao;
    @Autowired
    ProductDao productDao;
    @Override
    public HomeSanPham getspbymasp(String masp) {
        return sanPhamDao.getspbymasp(masp);
    }

    @Override
    public List<Size> getsizebymasp(String masp) {
        return productDao.getsizebymasp(masp);
    }

    @Override
    public List<HinhAnh> gethabymasp(String masp) {
        return productDao.gethabymasp(masp);
    }

    @Override
    public List<BinhLuan> getallbl(String masp) {
        return productDao.getAllbl(masp);
    }

    @Override
    public List<BinhLuan> getpagebl(String masp, int start, int end) {
        return productDao.getpagebl(masp, start, end);
    }
}
