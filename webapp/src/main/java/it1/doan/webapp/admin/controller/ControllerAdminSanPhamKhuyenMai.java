package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.NguoiDung;
import it1.doan.webapp.model.Pagination;
import it1.doan.webapp.model.SanPhamKhuyenMai;
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
public class ControllerAdminSanPhamKhuyenMai {

    @Autowired
    AdminService adminService;
    @Autowired
    AdminPagepml adminPage;
    private int totalProductPage = 9;
    @Autowired
    UserService userService;

    @GetMapping("/admin/sanphamkhuyenmai")
    public String getAdminsanphamkhuyemai(Model model , HttpServletRequest request){

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

        return "admin/sanphamkhuyenmai";
    }

    @RequestMapping(value = "/admin/spkmall",method = {RequestMethod.GET})
    @ResponseBody
    public List<SanPhamKhuyenMai> getallspkm(@RequestParam(name = "page",required = false) int currentPage,
                                             @RequestParam(name = "keyword",required = false) String keyword){
        List<SanPhamKhuyenMai> sanPhamKhuyenMais = adminService.getAllspkm(keyword);
        int totalData = sanPhamKhuyenMais.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,currentPage);
        List<SanPhamKhuyenMai> sanPhamKhuyenMais1 = adminService.getPagespkm(pagination.getStart(),totalProductPage,keyword);

        return sanPhamKhuyenMais1;
    }
}
