package it1.doan.webapp.user.service;

import it1.doan.webapp.admin.dao.AdminKhuyenMaiDao;
import it1.doan.webapp.model.KhuyenMai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KhuyenMaiServiceimpl implements KhuyenMaiServiceHome {

    @Autowired
    AdminKhuyenMaiDao adminKhuyenMaiDao;

    @Override
    public List<KhuyenMai> getallKM() {
        return adminKhuyenMaiDao.gethienKhuyenMai();
    }
}
