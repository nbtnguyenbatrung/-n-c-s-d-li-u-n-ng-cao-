package it1.doan.webapp.user.service;

import it1.doan.webapp.common.ProductSearch;
import it1.doan.webapp.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeService {
    List<HomeSanPham> getSanPhamByType(String type);
    List<GioHang> getghbynd(int id);
    void insert(GioHang gioHang);
    void update(GioHang gioHang);
    void delete(GioHang gioHang);
    void delete(int id);
    void delete(List<GioHang> gioHangs);
    void updateadd(GioHang gioHang);
    boolean getgiohang(GioHang gioHang );
    List<GioHang> getgiohangbyone(GioHang gioHang);
    List<HomeSanPham> getspSearch(ProductSearch productSearch, int start , int end, boolean check[]);
    List<HomeSanPham> getallsp(ProductSearch productSearch,boolean check[]);

    List<HoaDon> getallhd();
    void Insert(HoaDon hoaDon);
    void Insert(List<ChiTietHoaDon> chiTietHoaDon);


    void updatepass(int id , String passnew);
    List<purchase> getallpurchase(int id,int type);
    List<purchase> getdonhang(int id, int type , int start , int end);
}
