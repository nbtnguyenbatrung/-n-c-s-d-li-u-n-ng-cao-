package it1.doan.webapp.user.controller;

import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.LoaiSanPham;
import it1.doan.webapp.model.NguoiDung;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControllerShop {

    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;

    @GetMapping("/shop")
    public String getShop(Model model, HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            String name = authentication.getName();
            NguoiDung user = userService.getUserByEmail(name);
            if(user!=null){
                String userName = (String) request.getSession().getAttribute("username");
                if(userName==null){
                    request.getSession().setAttribute("username",user.getHoten());
                }
                model.addAttribute("username",user.getHoten());
            }

        }

        List<LoaiSanPham> loaiSanPhams = adminService.getAllloaisp(1);
        model.addAttribute("loaisp" , loaiSanPhams);

        List<ThuongHieu> thuongHieus = adminService.getAllthuonghieu(1);
        model.addAttribute("thuongHieus",thuongHieus);

        return "shop";
    }
}
