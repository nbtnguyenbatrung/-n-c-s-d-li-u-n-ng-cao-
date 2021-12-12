package it1.doan.webapp.admin.service.impl;

import com.sun.istack.internal.NotNull;
import it1.doan.webapp.DTO.UserRegistraionDto;
import it1.doan.webapp.model.NguoiDung;
import org.springframework.ui.Model;

public interface UserService {
    void save(NguoiDung nguoiDung);
    NguoiDung getUserByEmail(String email);
    String update(NguoiDung nguoiDung);
    NguoiDung get(int ID);
    String delete(int ID , int status );
}
