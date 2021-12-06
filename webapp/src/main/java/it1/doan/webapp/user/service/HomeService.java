package it1.doan.webapp.user.service;

import it1.doan.webapp.model.HomeSanPham;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeService {
    List<HomeSanPham> getSanPhamByType(String type);
}
