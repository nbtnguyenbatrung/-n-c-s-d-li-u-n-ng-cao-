package it1.doan.webapp.user.controller;

import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.*;
import it1.doan.webapp.user.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControllerShoppingCart {

    @Autowired
    HomeService homeService;
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;

    @GetMapping("/shopping-cart")
    public String getshoppingcart(Model model, HttpServletRequest request){

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
                List<GioHang> gioHangs = homeService.getghbynd(user.getID());
                model.addAttribute("giohang",gioHangs);
                model.addAttribute("sl",gioHangs.size());
            }

        }

        List<LoaiSanPham> loaiSanPhams = adminService.getAllloaisp(1);
        model.addAttribute("loaisp" , loaiSanPhams);

        List<ThuongHieu> thuongHieus = adminService.getAllthuonghieu(1);
        model.addAttribute("thuongHieus",thuongHieus);

        return "shopping-cart";
    }


    @GetMapping("/check-out")
    public String getcheckout(Model model, HttpServletRequest request){

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
                List<GioHang> gioHangs = homeService.getghbynd(user.getID());
                model.addAttribute("giohang",gioHangs);
                model.addAttribute("sl",gioHangs.size());
            }

        }

        List<LoaiSanPham> loaiSanPhams = adminService.getAllloaisp(1);
        model.addAttribute("loaisp" , loaiSanPhams);

        List<ThuongHieu> thuongHieus = adminService.getAllthuonghieu(1);
        model.addAttribute("thuongHieus",thuongHieus);

        return "check-out";
    }
}
