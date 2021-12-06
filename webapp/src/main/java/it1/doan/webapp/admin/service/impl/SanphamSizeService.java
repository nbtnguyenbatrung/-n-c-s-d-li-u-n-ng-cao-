package it1.doan.webapp.admin.service.impl;

import it1.doan.webapp.model.SanPhamSize;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.stereotype.Service;

@Service
public interface SanphamSizeService {
    String Insert(SanPhamSize sanPhamSize);
    String Update(SanPhamSize sanPhamSize);
    SanPhamSize Get(String masize , String masp);
    String Delete(String masize , String masp);
}
