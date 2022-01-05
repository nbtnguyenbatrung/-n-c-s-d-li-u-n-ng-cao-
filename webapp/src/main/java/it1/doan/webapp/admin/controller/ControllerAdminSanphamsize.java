package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.SanphamSizeService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.*;
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
public class ControllerAdminSanphamsize {
    @Autowired
    AdminService adminService;
    @Autowired
    SanphamSizeService sanphamSizeService;
    @Autowired
    AdminPagepml adminPage;
    private int totalProductPage = 9;
    @Autowired
    UserService userService;

    @GetMapping("/admin/sanphamsize")
    public String getAdminsanphamsize(Model model , HttpServletRequest request){

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

        List<SanPham> sanPhams = adminService.getAllsp(1);
        model.addAttribute("sanpham",sanPhams);
        List<Size> sizes = adminService.getAllsize(1);
        model.addAttribute("size",sizes);

        return "admin/sanphamsize";
    }

    @RequestMapping(value = "/admin/getallspsize",method = {RequestMethod.GET})
    @ResponseBody
    public List<SanPhamSize> getallspsize(@RequestParam(name = "page",required = false) int currentPage,
                                          @RequestParam(name = "keyword",required = false) String keyword){
        List<SanPhamSize> sanPhamSizes = adminService.getAllspsize(keyword);
        int totalData = sanPhamSizes.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,currentPage);
        List<SanPhamSize> sanPhamSizes1 = adminService.getPagespsize(pagination.getStart(),totalProductPage,keyword);
        return sanPhamSizes1;
    }

    @RequestMapping(value = "/savespsize",method = {RequestMethod.GET})
    @ResponseBody
    public String add(@RequestParam("maSP") String masp ,@RequestParam("maSize") String masize,
                      @RequestParam("soluong") String soluong){
        int soluongadd  = Integer.parseInt(soluong);
        SanPhamSize sanPhamSize = new SanPhamSize(masp,masize,soluongadd);
        sanphamSizeService.Insert(sanPhamSize);

        return " thêm dữ liệu thành công ";
    }


    @RequestMapping(value = "/updatespsize",method = RequestMethod.GET)
    @ResponseBody
    public String update(@RequestParam("maSP") String masp ,@RequestParam("maSize") String masize,
                         @RequestParam("soluong") String soluong ){
        int soluongadd  = Integer.parseInt(soluong);
        SanPhamSize sanPhamSize = new SanPhamSize(masp,masize,soluongadd);
        sanphamSizeService.Update(sanPhamSize);
        return " Cập nhật dữ liệu thành công ";
    }

    @GetMapping("/findOnespsize")
    @ResponseBody
    public SanPhamSize get( @RequestParam("id") String id, @RequestParam("id2") String id2  ){
        SanPhamSize sanPhamSize = sanphamSizeService.Get(id,id2);
        return sanPhamSize;
    }

    @GetMapping("/deletespsize")
    public String delete(@RequestParam(name = "id") String id , @RequestParam(name = "id2") String id2){
        sanphamSizeService.Delete(id,id2);
        return "redirect:/sanphamsize";
    }
}
