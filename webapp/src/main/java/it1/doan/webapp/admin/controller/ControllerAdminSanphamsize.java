package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.AdminService;
import it1.doan.webapp.admin.service.SanphamSizeService;
import it1.doan.webapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/sanphamsize")
    public String getAdminsanphamsize(Model model ,@RequestParam(name = "page",required = false) String currentPage){
        if (currentPage == null) {
            currentPage = "1";
        }
        List<SanPhamSize> sanPhamSizes = adminService.getAllspsize();
        int totalData = sanPhamSizes.size();
        String baseUrl = "/sanphamsize?page=";
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
        List<SanPhamSize> sanPhamSizes1 = adminService.getPagespsize(pagination.getStart(),totalProductPage);
        List<SanPham> sanPhams = adminService.getAllsp(1);
        model.addAttribute("sanpham",sanPhams);
        List<Size> sizes = adminService.getAllsize(1);
        model.addAttribute("size",sizes);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("sanphamsize",sanPhamSizes1);
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        model.addAttribute("baseUrl",baseUrl);
        return "admin/sanphamsize";
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
