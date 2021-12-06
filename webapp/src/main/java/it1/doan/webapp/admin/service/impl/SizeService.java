package it1.doan.webapp.admin.service.impl;

import it1.doan.webapp.model.Size;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.stereotype.Service;

@Service
public interface SizeService {
    String Insert(Size size);
    String Update(Size size);
    Size Get(String masize);
    String Delete(String masize);
}
