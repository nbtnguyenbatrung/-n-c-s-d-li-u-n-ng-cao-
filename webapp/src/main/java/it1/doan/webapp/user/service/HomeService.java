package it1.doan.webapp.user.service;

import it1.doan.webapp.common.ProductSearch;
import it1.doan.webapp.model.GioHang;
import it1.doan.webapp.model.HomeSanPham;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeService {
    List<HomeSanPham> getSanPhamByType(String type);
    List<GioHang> getghbynd(int id);
    List<HomeSanPham> getspSearch(ProductSearch productSearch, int start , int end, boolean check[]);
    List<HomeSanPham> getallsp(ProductSearch productSearch,boolean check[]);

}
