package it1.doan.webapp.user.service;


import it1.doan.webapp.model.KhuyenMai;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KhuyenMaiServiceHome {
    List<KhuyenMai> getallKM();
}
