package it1.doan.webapp.admin.service.impl;

import it1.doan.webapp.model.KhuyenMai;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.stereotype.Service;

@Service
public interface KhuyenMaiService {
    String Insert(KhuyenMai khuyenMai);
    String Update(KhuyenMai khuyenMai);
    KhuyenMai Get(String makm);
    String Delete(String makm);
}
