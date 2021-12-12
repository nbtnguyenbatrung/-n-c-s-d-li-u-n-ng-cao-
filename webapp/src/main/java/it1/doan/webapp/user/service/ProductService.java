package it1.doan.webapp.user.service;

import it1.doan.webapp.model.BinhLuan;
import it1.doan.webapp.model.HinhAnh;
import it1.doan.webapp.model.HomeSanPham;
import it1.doan.webapp.model.Size;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    HomeSanPham getspbymasp(String masp);
    List<Size> getsizebymasp(String masp);
    List<HinhAnh> gethabymasp(String masp);
    List<BinhLuan> getallbl(String masp);
    List<BinhLuan> getpagebl(String masp , int start , int end);
}
