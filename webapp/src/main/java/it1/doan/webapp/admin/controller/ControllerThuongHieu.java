package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.*;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.ThuonghieuService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.NguoiDung;
import it1.doan.webapp.model.Pagination;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;


@Controller
public class ControllerThuongHieu {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminPagepml adminPage;
    @Autowired
    ThuonghieuService thuonghieuService;
    @Autowired
    function function ;
    @Autowired
    UserService userService;

    private int totalProductPage = 9;

    @GetMapping( "/admin/thuonghieu")
    public String getAdminthuonghieu(Model model , HttpServletRequest request ,
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
            }

        }

        if (currentPage == null) {
            currentPage = "1";
        }
        String baseUrl = "/thuonghieu?page=";
        List<ThuongHieu> thuongHieus2 = adminService.getAllthuonghieu(2);
        List<ThuongHieu> thuongHieus = adminService.getAllthuonghieu(1);
        int totalData = thuongHieus.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
        List<ThuongHieu> thuongHieus1 = adminService.getPagethuonghieu(pagination.getStart(),totalProductPage);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("thuonghieupage",thuongHieus1);
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        model.addAttribute("baseUrl",baseUrl);
        ThuongHieu thuongHieu = new ThuongHieu("HA"+function.Laystt(thuongHieus2.get(0).getMaHang()));
        model.addAttribute("thuonghieu",thuongHieu);
        return "admin/thuonghieu";
    }

    @RequestMapping(value = "/saveth",method = {RequestMethod.GET})
    @ResponseBody
    public String add(@RequestParam("maHang") String mahang ,@RequestParam("tenHang") String tenhang,
                                @RequestParam("logo") String logo){
        String logoadd = logo.substring(12);
        ThuongHieu thuongHieu = new ThuongHieu(mahang,tenhang,logoadd);
        thuonghieuService.Insert(thuongHieu);

        return " thêm dữ liệu thành công ";
    }


    @RequestMapping(value = "/updateth",method = RequestMethod.GET)
    @ResponseBody
    public String update(@RequestParam("maHang") String mahang ,@RequestParam("tenHang") String tenhang,
                                   @RequestParam("logo") String logo ){
        String logoedit = logo.substring(12);
        ThuongHieu thuongHieu = new ThuongHieu(mahang,tenhang,logoedit);
        thuonghieuService.Update(thuongHieu);
        return " Cập nhật dữ liệu thành công ";
    }

    @GetMapping("/findOneth")
    @ResponseBody
    public ThuongHieu get( @RequestParam(name = "id") String id){
            ThuongHieu thuongHieus = thuonghieuService.Get(id);
        return thuongHieus;
    }

    @GetMapping("/deleteth")
    public String delete(@RequestParam(name = "id") String id){
        thuonghieuService.Delete(id);
        return "redirect:/admin/thuonghieu";
    }

}
