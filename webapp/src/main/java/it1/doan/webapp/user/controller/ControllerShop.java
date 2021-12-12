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
import org.springframework.web.bind.annotation.RequestParam;

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

    private int totalProductPage = 1;
    private boolean check[] = {false,false,false,false,false};
    @GetMapping("/shop")
    public String getShop(Model model, HttpServletRequest request,
                          @RequestParam(name = "page",required = false) String currentPage ){
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

        if (currentPage == null) {
            currentPage = "1";
            check[0] = false;
        }
        if(mahang == "null" || mahang == "" || request.getParameter("mahang") == null){
            productSearch.setMahang("");
        }
        if(maloaisp == "null" || maloaisp == "" || request.getParameter("maloaisp") == null){
            productSearch.setMaloaisp("");
        }
        if(masize == "null" || masize == "" || request.getParameter("masize") == null){
            productSearch.setMasize("");
        }
        if(tensp == "null" || tensp == "" || request.getParameter("tensp") == null){
            productSearch.setTensp("");
        }

        List<HomeSanPham> homeSanPhams = homeService.getallsp(productSearch,check);
        int totalData = homeSanPhams.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
        List<HomeSanPham> sanPhams = homeService.getspSearch(productSearch,pagination.getStart(),totalProductPage,check);
        model.addAttribute("sanPhams",sanPhams);
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        String baseUrl = "/shop?mahang="+productSearch.getMahang()+"&maloaisp="+productSearch.getMaloaisp()+"&masize="+productSearch.getMasize()+"&tensp="+productSearch.getTensp()+"&page=";
        model.addAttribute("baseUrl",baseUrl);
        String urlmahang = "/shop?mahang=";
        String urlmaloaisp = productSearch.getMahang()+"&maloaisp=";
        String urlmasize = productSearch.getMahang()+"&maloaisp="+productSearch.getMaloaisp()+"&masize=";
        String urltensp = productSearch.getMahang()+"&maloaisp="+productSearch.getMaloaisp()+"&masize="+productSearch.getMasize()+"&tensp=";
        String urlphumahang ="&maloaisp="+productSearch.getMaloaisp()+"&masize="+productSearch.getMasize()+"&tensp="+productSearch.getTensp();
        String urlphumaloaisp ="&masize="+productSearch.getMasize()+"&tensp="+productSearch.getTensp();
        String urlphumasize ="&tensp="+productSearch.getTensp();
        model.addAttribute("urlmahang",urlmahang);
        model.addAttribute("urlmaloaisp",urlmaloaisp);
        model.addAttribute("urlmasize",urlmasize);
        model.addAttribute("urltensp",urltensp);
        model.addAttribute("urlphumahang",urlphumahang);
        model.addAttribute("urlphumaloaisp",urlphumaloaisp);
        model.addAttribute("urlphumasize",urlphumasize);
        model.addAttribute("mahang",mahang);
        model.addAttribute("maloaisp",maloaisp);
        model.addAttribute("masize",masize);
        return "shop";
    }

}
