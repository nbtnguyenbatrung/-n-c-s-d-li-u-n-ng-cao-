package it1.doan.webapp.admin.service;

import it1.doan.webapp.model.ThuongHieu;
import org.springframework.stereotype.Service;


@Service
public interface ThuonghieuService {

    String Insert(ThuongHieu thuongHieu);
    String Update(ThuongHieu thuongHieu);
    ThuongHieu Get(String mahang);
    String Delete(String mahang);
}
