package it1.doan.webapp.user.service;

import it1.doan.webapp.common.ProductSearch;
import it1.doan.webapp.model.*;
import it1.doan.webapp.user.dao.GiaoHangDao;
import it1.doan.webapp.user.dao.HoaDonDao;
import it1.doan.webapp.user.dao.SanPhamDao;
import it1.doan.webapp.user.dao.purchaseDao;
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
    @Autowired
    purchaseDao purchaseDao;

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
    public void delete(int id) {
        giaoHangDao.delete(id);
    }

    @Override
    public void delete(List<GioHang> gioHangs) {
    giaoHangDao.delete(gioHangs);
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
    public List<HomeSanPham> getspSearch(ProductSearch productSearch, int start, int end ) {
        return sanPhamDao.getspSearch(productSearch, start, end);
    }

    @Override
    public List<HomeSanPham> getallsp(ProductSearch productSearch) {
        return sanPhamDao.getallsp(productSearch);
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

    @Override
    public void updatepass(int id, String passnew) {
        purchaseDao.updatepass(id , passnew);
    }

    @Override
    public List<purchase> getallpurchase(int id, int type) {
        return purchaseDao.getallpurchase(id,type);
    }

    @Override
    public List<purchase> getdonhang(int id,int type, int start, int end) {
        return purchaseDao.getdonhang(id,type,start,end);
    }

    @Override
    public void updateuserdh(String mahd, String masp, String masize) {
        purchaseDao.updateuserdh(mahd,masp,masize);
    }

    @Override
    public void Insert(String madg, int id, String masp, int sosao, String binhluan) {
        purchaseDao.Insert(madg, id, masp, sosao, binhluan);
    }

    @Override
    public List<DanhGiaByUser> getallDanhgia() {
        return purchaseDao.getallDanhgia();
    }
}
