package it1.doan.webapp.admin.service.impl;

import it1.doan.webapp.model.HinhAnh;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminHinhAnhService {
    List<HinhAnh> getallHinhAnh(String masp);
    void Insert(String masp , List<String> list);
    void Delete(String maha);
    void Update(String maha , String hinhanh);
}
