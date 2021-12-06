package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.AdminThuongHieuDAO;
import it1.doan.webapp.admin.service.impl.ThuonghieuService;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ThuonghieuServicepml implements ThuonghieuService {

    @Autowired
    AdminThuongHieuDAO adminThuongHieuDAO;

    @Override
    public String Insert(ThuongHieu thuongHieu) {
        return adminThuongHieuDAO.Insert(thuongHieu);
    }

    @Override
    public String Update(ThuongHieu thuongHieu) {
        return adminThuongHieuDAO.Update(thuongHieu);
    }

    @Override
    public ThuongHieu Get(String mahang) {
        return adminThuongHieuDAO.Get(mahang);
    }

    @Override
    public String Delete(String mahang) {
        return adminThuongHieuDAO.Delete(mahang);
    }
}
