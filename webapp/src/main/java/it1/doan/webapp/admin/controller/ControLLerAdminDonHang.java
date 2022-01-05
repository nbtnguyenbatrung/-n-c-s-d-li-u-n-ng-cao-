package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.DonHangService;
import it1.doan.webapp.admin.service.impl.HoaDonService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class ControLLerAdminDonHang {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminPagepml adminPage;
    @Autowired
    HoaDonService hoaDonService;
    @Autowired
    DonHangService donHangServicel;
    @Autowired
    UserService userService;
    private int totalProductPage = 9;

    @GetMapping("/admin/donhang")
    public String getAdmindonhang(Model model ,@RequestParam(name = "page",required = false) String currentPage,
                        HttpServletRequest request){

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
        List<DonHang> donHangs = adminService.getAlldh();
        int totalData = donHangs.size();
        String baseUrl = "/donhang?page=";
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
        List<DonHang> donHangs1 = adminService.getPagedh(pagination.getStart(),totalProductPage);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("donhang",donHangs);
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        model.addAttribute("baseUrl",baseUrl);
        return "admin/donhang";
    }

    @GetMapping("/admin/hoadon")
    public String getAdminhoadon(Model model ,
                                HttpServletRequest request ){

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

        return "admin/hoadon";
    }

    @RequestMapping(value = "/admin/hoadonall",method = {RequestMethod.GET})
    @ResponseBody
    public List<DonHang> getallhoadon(@RequestParam(name = "page",required = false) int currentPage,
                                     @RequestParam(name = "startdate",required = false) Date startdate ,
                                     @RequestParam(name = "enddate",required = false) Date enddate){
        List<DonHang> donHangs = adminService.getAllhd(startdate,enddate);
        int totalData = donHangs.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,currentPage);
        List<DonHang> donHangs1 = adminService.getPagehd(pagination.getStart(),totalProductPage,startdate,enddate);

        return donHangs1;
    }

    @GetMapping("/deletehd")
    public String delete(@RequestParam(name = "id") String id){
        hoaDonService.Delete(id);
        return "redirect:/hoadon";
    }

    @RequestMapping("/chitiethoadon")
    public String chitiethoadon(Model model , @RequestParam(name = "id") String id ,
                                @RequestParam(name = "page",required = false) String currentPage,
                                HttpServletRequest request){

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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        List<DonHang> donHangs = hoaDonService.getct(id);
        int totalData = donHangs.size();
        String baseUrl = "/chitiethoadon?page=";
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
        List<DonHang> donHangs1 = hoaDonService.getctpage(id,pagination.getStart(),totalProductPage);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("cthoadon",donHangs1);
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        model.addAttribute("baseUrl",baseUrl);
        model.addAttribute("mahd",donHangs.get(0).getMaHD());
        model.addAttribute("ngaylap",formatter.format(donHangs.get(0).getNgaylap() ) );
        return "admin/chitiethoadon";
    }

    @GetMapping("/findOnect")
    @ResponseBody
    public DonHang getct(@RequestParam(name = "mahd") String mahd , @RequestParam(name = "masp") String masp,
                        @RequestParam(name = "masize") String masize ){
        DonHang donHang = donHangServicel.Get(mahd,masp,masize);
        return donHang;
    }

    @GetMapping("/savedh")
    @ResponseBody
    public String Insert(@RequestParam(name = "key") Integer trangthai ,
                         @RequestParam(name = "list") List<String> dk ){
        donHangServicel.Insert(trangthai,dk);
        return " Duyệt Đơn thành công ";
    }
}
