package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.NguoiDung;
import it1.doan.webapp.model.Pagination;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ControllerAdminNguoiDung {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminPagepml adminPage;
    @Autowired
    UserService userService;

    private int totalProductPage = 9;

    @GetMapping("/nguoidung")
    public String getAdminnguoidung(Model model ,@RequestParam(name = "page",required = false) String currentPage){
        if (currentPage == null) {
            currentPage = "1";
        }
        List<NguoiDung> nguoiDungs = adminService.getAllnd();
        int totalData = nguoiDungs.size();
        String baseUrl = "/nguoidung?page=";
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
        List<NguoiDung> nguoiDungs1 = adminService.getPagend(pagination.getStart(),totalProductPage);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("nguoidung",nguoiDungs1);
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        model.addAttribute("baseUrl",baseUrl);
        return "admin/nguoidung";
    }

    @RequestMapping(value = "/savend",method = {RequestMethod.GET})
    @ResponseBody
    public String add(@RequestParam("hoten") String hoten ,@RequestParam("sdt") String sdt,
                      @RequestParam("email") String email,@RequestParam("mk") String mk,
                      @RequestParam("status") int status , @RequestParam("quyen") String quyen){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword =encoder.encode(mk);
        NguoiDung nguoiDung = new NguoiDung(hoten,sdt,email,mk,status,quyen);
        nguoiDung.setMk(encodePassword);
        userService.save(nguoiDung);
        return " tạo tài khoản thành công ";
    }

    @RequestMapping(value = "/updatend",method = RequestMethod.GET)
    @ResponseBody
    public String update(@RequestParam("id") int id ,@RequestParam("hoten") String hoten ,
                         @RequestParam("sdt") String sdt,@RequestParam("email") String email,
                         @RequestParam("status") int status,@RequestParam("quyen") String quyen){

        NguoiDung nguoiDung = new NguoiDung(id,hoten,sdt,email,status,quyen);
        userService.update(nguoiDung);
        return " Cập nhật dữ liệu thành công ";
    }

    @GetMapping("/findOnend")
    @ResponseBody
    public NguoiDung get( @RequestParam(name = "id") int id){
        NguoiDung nguoiDung = userService.get(id);
        return nguoiDung;
    }

    @GetMapping("/deletend")
    public String delete(@RequestParam(name = "id") int id,
                         @RequestParam(name = "status") int status){
        userService.delete(id,status);
        return "redirect:/thuonghieu";
    }

}
