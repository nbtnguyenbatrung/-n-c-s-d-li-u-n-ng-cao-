package it1.doan.webapp.admin.service.impl;

import it1.doan.webapp.model.BinhLuan;
import it1.doan.webapp.model.DanhGia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DanhGiaService {
    List<DanhGia> getAllDanhGia(String masp);
    List<BinhLuan> getAllbl(String masp , int sosao , String bl , int start , int end);
}
