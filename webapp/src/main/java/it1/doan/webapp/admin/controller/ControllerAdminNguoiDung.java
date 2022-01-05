package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.NguoiDung;
import it1.doan.webapp.model.Pagination;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/admin/nguoidung")
    public String getAdminnguoidung(Model model , HttpServletRequest request){

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

        return "admin/nguoidung";
    }

    @RequestMapping(value = "/admin/nguoidungall",method = {RequestMethod.GET})
    @ResponseBody
    public List<NguoiDung> getallnguoidung(@RequestParam(name = "page",required = false) int currentPage,
                                             @RequestParam(name = "keyword",required = false) String keyword){
        List<NguoiDung> nguoiDungs = adminService.getAllnd(keyword);
        int totalData = nguoiDungs.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,currentPage);
        List<NguoiDung> nguoiDungs1 = adminService.getPagend(pagination.getStart(),totalProductPage,keyword);

        return nguoiDungs1;
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
