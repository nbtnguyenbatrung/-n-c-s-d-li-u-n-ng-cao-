package it1.doan.webapp.user.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.GioHang;
import it1.doan.webapp.model.NguoiDung;
import it1.doan.webapp.model.Pagination;
import it1.doan.webapp.model.purchase;
import it1.doan.webapp.user.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControllerPurchase {

    @Autowired
    HomeService homeService;
    @Autowired
    UserService userService;
    @Autowired
    AdminPagepml adminPage;

    private int totalProductPage = 4;
    private  String check = "";
    @GetMapping("/purchase")
    public String getpurchase(Model model, HttpServletRequest request ,
                              @RequestParam(name = "id" , required = false) int id ,
                              @RequestParam(name = "type" , required = false) Integer type ,
                              @RequestParam(name = "page",required = false) String currentPage) {
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
        if (currentPage == null) {
            currentPage = "1";
        }
        if (type == -1){
            String baseUrl = "/purchase?id="+id+"&type="+type+"&page=";
            List<purchase> purchases = homeService.getallpurchase(id,type);
            int totalData = purchases.size();
            Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
            List<purchase> purchases1 = homeService.getdonhang(id,type,pagination.getStart(),totalProductPage);
            model.addAttribute("purchases1",purchases1);
            model.addAttribute("Page",pagination);
            model.addAttribute("totalpage",pagination.getTotalPage());
            model.addAttribute("CurrentPage",pagination.getCurrentPage());
            model.addAttribute("Previous",pagination.getCurrentPage()-1);
            model.addAttribute("Next",pagination.getCurrentPage()+1);
            model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
            model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
            model.addAttribute("baseUrl",baseUrl);
            check = "all";
        }

        if (type == 0){
            String baseUrl = "/purchase?id="+id+"&type="+type+"&page=";
            List<purchase> purchases = homeService.getallpurchase(id,type);
            int totalData = purchases.size();
            Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
            List<purchase> purchases1 = homeService.getdonhang(id,type,pagination.getStart(),totalProductPage);
            model.addAttribute("purchases1",purchases1);
            model.addAttribute("Page",pagination);
            model.addAttribute("totalpage",pagination.getTotalPage());
            model.addAttribute("CurrentPage",pagination.getCurrentPage());
            model.addAttribute("Previous",pagination.getCurrentPage()-1);
            model.addAttribute("Next",pagination.getCurrentPage()+1);
            model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
            model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
            model.addAttribute("baseUrl",baseUrl);
            check = "confirmation";
        }

        if (type == 1){
            String baseUrl = "/purchase?id="+id+"&type="+type+"&page=";
            List<purchase> purchases = homeService.getallpurchase(id,type);
            int totalData = purchases.size();
            Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
            List<purchase> purchases1 = homeService.getdonhang(id,type,pagination.getStart(),totalProductPage);
            model.addAttribute("purchases1",purchases1);
            model.addAttribute("Page",pagination);
            model.addAttribute("totalpage",pagination.getTotalPage());
            model.addAttribute("CurrentPage",pagination.getCurrentPage());
            model.addAttribute("Previous",pagination.getCurrentPage()-1);
            model.addAttribute("Next",pagination.getCurrentPage()+1);
            model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
            model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
            model.addAttribute("baseUrl",baseUrl);
            check = "delivered";
        }

        if (type == 2){
            String baseUrl = "/purchase?id="+id+"&type="+type+"&page=";
            List<purchase> purchases = homeService.getallpurchase(id,type);
            int totalData = purchases.size();
            Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
            List<purchase> purchases1 = homeService.getdonhang(id,type,pagination.getStart(),totalProductPage);
            model.addAttribute("purchases1",purchases1);
            model.addAttribute("Page",pagination);
            model.addAttribute("totalpage",pagination.getTotalPage());
            model.addAttribute("CurrentPage",pagination.getCurrentPage());
            model.addAttribute("Previous",pagination.getCurrentPage()-1);
            model.addAttribute("Next",pagination.getCurrentPage()+1);
            model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
            model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
            model.addAttribute("baseUrl",baseUrl);
            check = "cancelled";
        }

        model.addAttribute("check",check);

        return "purchase";
    }

    @GetMapping("/requestpass")
    public String requestpass(@RequestParam(name = "id") int id,
                              @RequestParam(name = "passold") String passold,
                              @RequestParam(name = "passnew") String passnew){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswordnew =encoder.encode(passnew);
        String encodePasswordold =encoder.encode(passold);
        NguoiDung nguoiDung = userService.get(id);
        if(encodePasswordold == nguoiDung.getMk()){
            homeService.updatepass(id,encodePasswordnew);
            return "thay đổi mật khâu thành công ";
        }
        else{
            return "mật khẩu hiện tại không đúng ";
        }
    }
}
