package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.AdminHoaDonDao;
import it1.doan.webapp.admin.service.impl.HoaDonService;
import it1.doan.webapp.model.DonHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HoaDonServicepml implements HoaDonService {

    @Autowired
    AdminHoaDonDao adminHoaDonDao;

    @Override
    public String Delete(String mahd) {
        return adminHoaDonDao.Delete(mahd);
    }

    @Override
    public List<DonHang> getct(String mahd) {
        return adminHoaDonDao.GetCT(mahd);
    }

    @Override
    public List<DonHang> getctpage(String mahd, int start, int end) {
        return adminHoaDonDao.GetCTPage(mahd,start,end);
    }

}
