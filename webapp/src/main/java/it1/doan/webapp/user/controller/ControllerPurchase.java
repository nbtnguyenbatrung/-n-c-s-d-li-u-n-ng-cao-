package it1.doan.webapp.user.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.function;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.*;
import it1.doan.webapp.user.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
public class ControllerPurchase {

    @Autowired
    HomeService homeService;
    @Autowired
    UserService userService;
    @Autowired
    AdminPagepml adminPage;
    @Autowired
    function function ;

    private int totalProductPage = 4;
    private  String check = "";
    @GetMapping("/purchase")
    public String getpurchase(Model model, HttpServletRequest request ,
                              @RequestParam(name = "id" , required = false) int id ) {
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


        List<purchase> purchases0 = homeService.getallpurchase(id,0);
        List<purchase> purchases1 = homeService.getallpurchase(id,1);
        List<purchase> purchases2 = homeService.getallpurchase(id,2);
        List<purchase> purchases3 = homeService.getallpurchase(id,-1);
        int totalData0 = purchases0.size();
        int totalData1 = purchases1.size();
        int totalData2 = purchases2.size();
        int totalData3 = purchases3.size();
        Pagination pagination0 = adminPage.GetInfoPage(totalData0,totalProductPage,1);
        Pagination pagination1 = adminPage.GetInfoPage(totalData1,totalProductPage,1);
        Pagination pagination2 = adminPage.GetInfoPage(totalData2,totalProductPage,1);
        Pagination pagination3 = adminPage.GetInfoPage(totalData3,totalProductPage,1);
        model.addAttribute("totalpage",pagination0.getTotalPage());
        model.addAttribute("totalpage1",pagination1.getTotalPage());
        model.addAttribute("totalpage2",pagination2.getTotalPage());
        model.addAttribute("totalpage3",pagination3.getTotalPage());

        return "purchase";
    }

    @RequestMapping("/purchase-all")
    @ResponseBody
    public List<purchase> getallpurchase (@RequestParam(name = "id" , required = false) int id ,
                        @RequestParam(name = "page") int currentpage,
                        @RequestParam(name = "type") int type){
        List<purchase> purchases1 = homeService.getallpurchase(id,type);
        int totalData = purchases1.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,currentpage);
        List<purchase> purchases = homeService.getdonhang(id,type,pagination.getStart(),totalProductPage);
        return purchases;
    }

    @RequestMapping(value = "/admin/updateuserdh",method = RequestMethod.GET)
    @ResponseBody
    public String update(@RequestParam("mahd") String mahd ,@RequestParam("masp") String masp,
                         @RequestParam("masize") String masize ){
        homeService.updateuserdh(mahd,masp,masize);
        return " Hủy đơn thành công ";
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

    @RequestMapping(value = "/admin/danhgiauser",method = RequestMethod.GET)
    @ResponseBody
    public String InsertDg(@RequestParam(name = "id") int id,
                           @RequestParam(name = "masp") String masp,
                           @RequestParam(name = "sosao") int sosao,
                           @RequestParam(name = "binhluan") String binhluan){
        List<DanhGiaByUser> danhGiaByUsers = homeService.getallDanhgia();
        String madg = "DG"+function.Laystt(danhGiaByUsers.get(0).getMaDG());
        homeService.Insert(madg,id,masp,sosao,binhluan);
        return "Đánh giá sản phẩm thành công ";
    }
}
