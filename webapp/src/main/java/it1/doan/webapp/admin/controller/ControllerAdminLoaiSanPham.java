package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.LoaiSanPhamService;
import it1.doan.webapp.admin.service.function;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.LoaiSanPham;
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
public class ControllerAdminLoaiSanPham {
    @Autowired
    AdminService adminService;
    @Autowired
    LoaiSanPhamService loaiSanPhamService;
    @Autowired
    AdminPagepml adminPage;
    @Autowired
    function function;
    @Autowired
    UserService userService;

    private int totalProductPage = 9;

    @GetMapping("/admin/loaisanpham")
    public String getAdminloaisanpham(Model model , HttpServletRequest request){

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

        List<LoaiSanPham> loaiSanPhams2 = adminService.getAllloaisp(2);

        LoaiSanPham loaiSanPham = new  LoaiSanPham("LO"+function.Laystt(loaiSanPhams2.get(0).getMaLoaiSP()));
        model.addAttribute("loaisanpham",loaiSanPham);
        return "admin/loaisanpham";
    }

    @RequestMapping(value = "/admin/loaispall",method = {RequestMethod.GET})
    @ResponseBody
    public List<LoaiSanPham> getallloaisp(@RequestParam(name = "page",required = false) int currentPage,
                                             @RequestParam(name = "keyword",required = false) String keyword){
        List<LoaiSanPham> loaiSanPhams = adminService.getAllloaisp(keyword);
        int totalData = loaiSanPhams.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,currentPage);
        List<LoaiSanPham> loaiSanPhams1 = adminService.getPageloaisp(pagination.getStart(),totalProductPage,keyword);

        return loaiSanPhams1;
    }

    @RequestMapping(value = "/saveloaisp",method = {RequestMethod.GET})
    @ResponseBody
    public String add(@RequestParam("maLoaiSP") String maloaisp ,@RequestParam("tenLoaiSP") String tenloaisp){
        LoaiSanPham loaiSanPham = new LoaiSanPham(maloaisp,tenloaisp);
        loaiSanPhamService.Insert(loaiSanPham);

        return " thêm dữ liệu thành công ";
    }


    @RequestMapping(value = "/updateloaisp",method = RequestMethod.GET)
    @ResponseBody
    public String update(@RequestParam("maLoaiSP") String maloaisp ,@RequestParam("tenLoaiSP") String tenloaisp){

        LoaiSanPham loaiSanPham = new LoaiSanPham(maloaisp,tenloaisp);
        loaiSanPhamService.Update(loaiSanPham);
        return " Cập nhật dữ liệu thành công ";
    }

    @GetMapping("/findOneloaisp")
    @ResponseBody
    public LoaiSanPham get( @RequestParam(name = "id") String id){
        LoaiSanPham loaiSanPham = loaiSanPhamService.Get(id);
        return loaiSanPham;
    }

    @GetMapping("/deleteloaisp")
    public String delete(@RequestParam(name = "id") String id){
        loaiSanPhamService.Delete(id);
        return "redirect:/thuonghieu";
    }
}
