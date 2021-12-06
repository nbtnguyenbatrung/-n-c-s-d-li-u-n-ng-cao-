package it1.doan.webapp.admin.service.impl;

import com.sun.istack.internal.NotNull;
import it1.doan.webapp.DTO.UserRegistraionDto;
import it1.doan.webapp.model.NguoiDung;
import org.springframework.ui.Model;

public interface UserService {
    int save(@NotNull UserRegistraionDto userRegistraionDto);
    boolean checktypeUser(UserRegistraionDto userRegistraionDto, Model model);
    NguoiDung getUserByEmail(String email);
}
