package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.AdminSizeDao;
import it1.doan.webapp.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SizeServicepml implements SizeService{

    @Autowired
    AdminSizeDao adminSizeDao;

    @Override
    public String Insert(Size size) {
        return adminSizeDao.Insert(size);
    }

    @Override
    public String Update(Size size) {
        return adminSizeDao.Update(size);
    }

    @Override
    public Size Get(String masize) {
        return adminSizeDao.Get(masize);
    }

    @Override
    public String Delete(String masize) {
        return adminSizeDao.Delete(masize);
    }
}
