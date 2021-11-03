package it1.doan.webapp.admin.service;

import it1.doan.webapp.model.DonHang;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DonHangService {
    DonHang Get(String mahd , String masp , String masize);
    void Insert(int trangthai , List<String> list);
}
