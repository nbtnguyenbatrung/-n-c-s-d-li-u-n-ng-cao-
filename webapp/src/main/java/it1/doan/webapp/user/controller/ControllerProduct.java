package it1.doan.webapp.user.controller;

import it1.doan.webapp.admin.service.impl.AdminPage;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.*;
import it1.doan.webapp.user.service.HomeService;
import it1.doan.webapp.user.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class ControllerProduct {

    @Autowired
    HomeService homeService;
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    ProductService productService;
    @Autowired
    AdminPage adminPage;

    private int totalProductPage = 4;
    @RequestMapping("/product")
    public String getProduct(Model model, HttpServletRequest request ,
                             @RequestParam(name = "masp",required = false) String masp,
                             @RequestParam(name = "page",required = false) String currentPage){

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
            }

        }

        List<LoaiSanPham> loaiSanPhams = adminService.getAllloaisp(1);
        model.addAttribute("loaisp" , loaiSanPhams);

        List<ThuongHieu> thuongHieus = adminService.getAllthuonghieu(1);
        model.addAttribute("thuongHieus",thuongHieus);


        HomeSanPham homeSanPham = productService.getspbymasp(masp);
        model.addAttribute("homeSanPham",homeSanPham);

        List<Size> sizes = productService.getsizebymasp(masp);
        model.addAttribute("sizes",sizes);

        List<HinhAnh> hinhAnhs = productService.gethabymasp(masp);
        model.addAttribute("hinhAnhs" , hinhAnhs);


        if (currentPage==null){
            currentPage = "1";
        }

        List<BinhLuan> binhLuans = productService.getallbl(masp);
        Pagination pagination = adminPage.GetInfoPage(binhLuans.size(),totalProductPage,Integer.parseInt(currentPage));
        List<BinhLuan> binhLuans1 = productService.getpagebl(masp,pagination.getStart(),totalProductPage);
        model.addAttribute("binhluan",binhLuans1);
        model.addAttribute("sl",binhLuans.size());
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        String baseUrl = "/product?masp="+masp+"&page=";
        model.addAttribute("baseUrl",baseUrl);

        return "product";
    }
}
