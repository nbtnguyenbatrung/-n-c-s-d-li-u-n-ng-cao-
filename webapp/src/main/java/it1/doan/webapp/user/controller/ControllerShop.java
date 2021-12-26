package it1.doan.webapp.user.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.common.ProductSearch;
import it1.doan.webapp.model.*;
import it1.doan.webapp.user.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControllerShop {

    @Autowired
    HomeService homeService;
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    AdminPagepml adminPage;

    private int totalProductPage = 3;

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
                List<GioHang> gioHangs = homeService.getghbynd(user.getID());
                model.addAttribute("giohang",gioHangs);
                model.addAttribute("sl",gioHangs.size());
            }

        }

        List<LoaiSanPham> loaiSanPhams = adminService.getAllloaisp(1);
        model.addAttribute("loaisp" , loaiSanPhams);

        List<ThuongHieu> thuongHieus = adminService.getAllthuonghieu(1);
        model.addAttribute("thuongHieus",thuongHieus);

        List<Size> sizes = adminService.getAllsize(1);
        model.addAttribute("sizes",sizes);

        String mahang = request.getParameter("mahang");
        String maloaisp = request.getParameter("maloaisp");
        String masize = request.getParameter("masize");
        String tensp = request.getParameter("tensp");
        ProductSearch productSearch = new ProductSearch(mahang,maloaisp,masize,tensp);

        List<HomeSanPham> homeSanPhams = homeService.getallsp(productSearch);
        int totalData = homeSanPhams.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,1);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("mahang",mahang);
        model.addAttribute("maloaisp",maloaisp);
        model.addAttribute("tensp",tensp);
        return "shop";
    }

    @RequestMapping("/productall")
    @ResponseBody
    public List<HomeSanPham> getallsp(HttpServletRequest request,
                                      @RequestParam(name = "page",required = false) int currentPage){
        String mahang = request.getParameter("mahang");
        String maloaisp = request.getParameter("maloaisp");
        String masize = request.getParameter("masize");
        String tensp = request.getParameter("tensp");
        ProductSearch productSearch = new ProductSearch(mahang,maloaisp,masize,tensp);
        List<HomeSanPham> homeSanPhams = homeService.getallsp(productSearch);
        int totalData = homeSanPhams.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,currentPage);
        List<HomeSanPham> sanPhams = homeService.getspSearch(productSearch,pagination.getStart(),totalProductPage);
        return sanPhams;
    }

}
