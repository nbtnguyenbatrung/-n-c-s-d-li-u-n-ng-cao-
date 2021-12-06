package it1.doan.webapp.admin.service.impl;

import it1.doan.webapp.model.SanPham;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.stereotype.Service;

@Service
public interface SanPhamService {
    String Insert(SanPham sanPham);
    String Update(SanPham sanPham);
    SanPham Get(String masp);
    String Delete(String masp);
}
