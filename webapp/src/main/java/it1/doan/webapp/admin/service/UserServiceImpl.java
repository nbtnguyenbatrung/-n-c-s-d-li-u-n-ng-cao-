package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.AdminNguoIDungDao;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private AdminNguoIDungDao adminNguoIDungDao;

    @Autowired
    public UserServiceImpl(AdminNguoIDungDao adminNguoIDungDao) {
        this.adminNguoIDungDao = adminNguoIDungDao;
    }

    @Override
    public void save(NguoiDung nguoiDung) {
        adminNguoIDungDao.Insert(nguoiDung);
    }

    @Override
    public NguoiDung getUserByEmail(String email) {
        return adminNguoIDungDao.existUser(email);
    }

    @Override
    public String update(NguoiDung nguoiDung) {
        return adminNguoIDungDao.Update(nguoiDung);
    }

    @Override
    public NguoiDung get(int ID) {
        return adminNguoIDungDao.Get(ID);
    }

    @Override
    public String delete(int ID, int status) {
        return adminNguoIDungDao.Delete(ID,status);
    }

}
