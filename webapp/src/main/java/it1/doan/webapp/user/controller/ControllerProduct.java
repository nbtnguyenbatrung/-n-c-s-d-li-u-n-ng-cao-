package it1.doan.webapp.user.controller;

import it1.doan.webapp.admin.service.impl.AdminPage;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.LoaiSanPhamService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.*;
import it1.doan.webapp.user.service.HomeService;
import it1.doan.webapp.user.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    @Autowired
    LoaiSanPhamService loaiSanPhamService;

    List<HomeSanPham> homeSanPhams1  = new ArrayList<>();

    private int totalProductPage = 4;
    @RequestMapping("/product")
    public String getProduct(Model model, HttpServletRequest request ,
                             @RequestParam(name = "masp",required = false) String masp){

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
                model.addAttribute("id",user.getID());
                model.addAttribute("sl",gioHangs.size());
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


        List<BinhLuan> binhLuans = productService.getallbl(masp);
        model.addAttribute("slbl",binhLuans.size());

        LoaiSanPham loaiSanPham = loaiSanPhamService.Get(homeSanPham.getMaLoaiSP());
        List<HomeSanPham> homeSanPhams = homeService.getSanPhamByType(loaiSanPham.getTenLoaiSP());
        Random  random = new Random();
        for(int i = 0 ; i< 4 ; i++){
            int index = random.nextInt(homeSanPhams.size());
            homeSanPhams1.add(homeSanPhams.get(index));
        }
        model.addAttribute("homeSanPhams1",homeSanPhams1);

        return "product";
    }

    @RequestMapping("/danhgiaproduct")
    @ResponseBody
    public List<BinhLuan> getallbinhluan(@RequestParam(name = "masp" , required = false) String masp ,
                                         @RequestParam(name = "page") int currentpage){
        List<BinhLuan> binhLuanList = productService.getallbl(masp);
        Pagination pagination = adminPage.GetInfoPage(binhLuanList.size(),totalProductPage,currentpage);
        List<BinhLuan> binhLuans1 = productService.getpagebl(masp,pagination.getStart(),totalProductPage);
        return binhLuans1;
    }
}
