package it1.doan.webapp.admin.service;

import com.sun.istack.internal.NotNull;
import it1.doan.webapp.DTO.UserRegistraionDto;
import it1.doan.webapp.admin.dao.AdminNguoIDungDao;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserServiceImpl implements UserService {

    private AdminNguoIDungDao userDAO;

    @Autowired
    public UserServiceImpl(AdminNguoIDungDao userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public int save(@NotNull UserRegistraionDto userRegistraionDto) {
        NguoiDung user = new NguoiDung(userRegistraionDto.getName(),userRegistraionDto.getPhone(),
                userRegistraionDto.getEmail(),userRegistraionDto.getPassword());

        return userDAO.saveUser(user);
    }

    @Override
    public boolean checktypeUser(UserRegistraionDto userRegistraionDto, Model model) {
        boolean result=true;
        String email = userRegistraionDto.getEmail();
        String password = userRegistraionDto.getPassword();
        String cpassword = userRegistraionDto.getConfirmPassword();
        String phone = userRegistraionDto.getPhone();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String phonePattern = "d{10}$";
        if(!email.matches(emailPattern)){
            model.addAttribute("validateEmail", "nhập email không đúng chuẩn ");
            result=false;
        }
        if(!password.equals(cpassword)){
            model.addAttribute("confirmPassword", "nhập mật khẩu không chính xác");
            result=false;
        }
        if(!phone.matches(phonePattern)){
            model.addAttribute("validatePhone", "nhập phone không đúng chuẩn ");
            return result;
        }
        return result;
    }

    @Override
    public NguoiDung getUserByEmail(String email) {
        return userDAO.existUser(email);
    }
}
