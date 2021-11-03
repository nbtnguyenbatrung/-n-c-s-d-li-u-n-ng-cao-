package it1.doan.webapp.admin.service;

import it1.doan.webapp.model.DonHang;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HoaDonService {
    String Delete(String mahd);
    List<DonHang> getct(String mahd);
    List<DonHang> getctpage(String mahd , int start ,int end);

}
