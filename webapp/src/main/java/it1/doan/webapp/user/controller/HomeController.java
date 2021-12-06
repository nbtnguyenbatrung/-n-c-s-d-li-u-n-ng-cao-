package it1.doan.webapp.user.controller;


import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.KhuyenMaiService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.*;
import it1.doan.webapp.user.service.HomeService;
import it1.doan.webapp.user.service.KhuyenMaiServiceHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    KhuyenMaiServiceHome khuyenMaiService;
    @Autowired
    HomeService homeService;
    @Autowired
    KhuyenMaiService khuyenMaiServiceadmin;
    @Autowired
    AdminService adminService;
    
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request){
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
        List<KhuyenMai> khuyenMais = khuyenMaiService.getallKM();
        model.addAttribute("khuyenmais",khuyenMais);
        List<HomeSanPham> sanPhamnu = homeService.getSanPhamByType("NỮ");
        HomeSanPham sanPham1 = sanPhamnu.get(0);
        KhuyenMai khuyenMai11=khuyenMaiServiceadmin.Get(sanPham1.getMaKM());
        int khuyenMai1 = 0;
        if (khuyenMai11 != null)
            khuyenMai1 = khuyenMai11.getSongay();

        model.addAttribute("sanPham1",sanPham1);
        model.addAttribute("khuyenMai1",khuyenMai1);
        List<HomeSanPham> sanPhamnam = homeService.getSanPhamByType("NAM");
        HomeSanPham sanPham2 = sanPhamnam.get(0);
        KhuyenMai khuyenMai12=khuyenMaiServiceadmin.Get(sanPham2.getMaKM());
        int khuyenMai2 = 0;
        if (khuyenMai12 != null)
            khuyenMai2 = khuyenMai12.getSongay();

        model.addAttribute("sanPham2",sanPham2);
        model.addAttribute("khuyenMai2",khuyenMai2);
        List<HomeSanPham> sanPhamtreem = homeService.getSanPhamByType("TRẺ EM");
        model.addAttribute("sanPhamnu",sanPhamnu);
        model.addAttribute("sanPhamnam",sanPhamnam);
        model.addAttribute("sanPhamtreem",sanPhamtreem);

        List<LoaiSanPham> loaiSanPhams = adminService.getAllloaisp(1);
        model.addAttribute("loaisp" , loaiSanPhams);

        List<ThuongHieu> thuongHieus = adminService.getAllthuonghieu(1);
        model.addAttribute("thuongHieus",thuongHieus);
        return "index";
    }

    @GetMapping("/contact")
    public String contact(){

        return "contact";
    }
}
