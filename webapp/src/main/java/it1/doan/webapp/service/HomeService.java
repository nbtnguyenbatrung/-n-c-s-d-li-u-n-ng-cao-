package it1.doan.webapp.service;

import it1.doan.webapp.model.KhuyenMai;
import it1.doan.webapp.model.SanPham;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HomeService {

    List<KhuyenMai> getAllKhuyenMai();

    List<SanPham> getAllSanPhamChild();

    List<SanPham> getAllSanPhamMen();
    List<SanPham> getAllSanPhamWomen();

}
