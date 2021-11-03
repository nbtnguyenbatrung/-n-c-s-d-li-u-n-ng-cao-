package it1.doan.webapp.admin.service;

import it1.doan.webapp.model.LoaiSanPham;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoaiSanPhamService {
    String Insert(LoaiSanPham loaiSanPham);
    String Update(LoaiSanPham loaiSanPham);
    LoaiSanPham Get(String maloaisp);
    String Delete(String maloaisp);
}
