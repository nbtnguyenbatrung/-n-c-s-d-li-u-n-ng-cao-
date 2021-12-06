package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.LoaiSanPhamService;
import it1.doan.webapp.admin.service.function;
import it1.doan.webapp.model.LoaiSanPham;
import it1.doan.webapp.model.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private int totalProductPage = 9;

    @GetMapping("/loaisanpham")
    public String getAdminloaisanpham(Model model ,@RequestParam(name = "page",required = false) String currentPage){
        if (currentPage == null) {
            currentPage = "1";
        }
        List<LoaiSanPham> loaiSanPhams2 = adminService.getAllloaisp(2);
        List<LoaiSanPham> loaiSanPhams = adminService.getAllloaisp(1);
        int totalData = loaiSanPhams.size();
        String baseUrl = "/loaisanpham?page=";
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
        List<LoaiSanPham> loaiSanPhams1 = adminService.getPageloaisp(pagination.getStart(),totalProductPage);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("loaisp",loaiSanPhams1);
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        model.addAttribute("baseUrl",baseUrl);
        LoaiSanPham loaiSanPham = new  LoaiSanPham("LO"+function.Laystt(loaiSanPhams2.get(0).getMaLoaiSP()));
        model.addAttribute("loaisanpham",loaiSanPham);
        return "admin/loaisanpham";
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
