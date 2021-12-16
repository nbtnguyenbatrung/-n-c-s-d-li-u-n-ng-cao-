package it1.doan.webapp.user.service;

import it1.doan.webapp.common.ProductSearch;
import it1.doan.webapp.model.ChiTietHoaDon;
import it1.doan.webapp.model.GioHang;
import it1.doan.webapp.model.HoaDon;
import it1.doan.webapp.model.HomeSanPham;
import it1.doan.webapp.user.dao.GiaoHangDao;
import it1.doan.webapp.user.dao.HoaDonDao;
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
    @Autowired
    HoaDonDao hoaDonDao;

    @Override
    public List<HomeSanPham> getSanPhamByType(String type) {
        return sanPhamDao.gettypesp(type);
    }

    @Override
    public List<GioHang> getghbynd(int id) {
        return giaoHangDao.getghbynd(id);
    }

    @Override
    public void insert(GioHang gioHang) {
        giaoHangDao.Insert(gioHang);
    }

    @Override
    public void update(GioHang gioHang) {
        giaoHangDao.update(gioHang);
    }

    @Override
    public void delete(GioHang gioHang) {
        giaoHangDao.delete(gioHang);
    }

    @Override
    public void updateadd(GioHang gioHang) {
        giaoHangDao.updateadd(gioHang);
    }

    @Override
    public boolean getgiohang(GioHang gioHang) {
        if(giaoHangDao.getgiohang(gioHang)){
            return true;
        }
        return false;
    }

    @Override
    public List<GioHang> getgiohangbyone(GioHang gioHang) {
        return giaoHangDao.getgiohangbyone(gioHang);
    }

    @Override
    public List<HomeSanPham> getspSearch(ProductSearch productSearch, int start, int end , boolean check[]) {
        return sanPhamDao.getspSearch(productSearch, start, end,check);
    }

    @Override
    public List<HomeSanPham> getallsp(ProductSearch productSearch,boolean check[]) {
        return sanPhamDao.getallsp(productSearch,check);
    }

    @Override
    public List<HoaDon> getallhd() {
        return hoaDonDao.getallhd();
    }

    @Override
    public void Insert(HoaDon hoaDon) {
        hoaDonDao.Insert(hoaDon);
    }

    @Override
    public void Insert(List<ChiTietHoaDon> chiTietHoaDon) {
        hoaDonDao.Insert(chiTietHoaDon);
    }
}
