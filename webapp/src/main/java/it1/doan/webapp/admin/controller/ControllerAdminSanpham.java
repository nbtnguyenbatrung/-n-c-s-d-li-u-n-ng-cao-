package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.excaption.NotFoundException;
import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.SanPhamService;
import it1.doan.webapp.admin.service.function;
import it1.doan.webapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ControllerAdminSanpham {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminPagepml adminPage;
    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    function function ;

    private int totalProductPage = 9;

    @GetMapping("/sanpham")
    public String getAdminsanpham(Model model ,@RequestParam(name = "page",required = false) String currentPage){
        if (currentPage == null) {
            currentPage = "1";
        }
        List<SanPham> sanPhams2 = adminService.getAllsp(2);
        List<SanPham> sanPhams = adminService.getAllsp(1);
        int totalData = sanPhams.size();
        String baseUrl = "/sanpham?page=";
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
        List<SanPham> sanPhams1 = adminService.getPagesp(pagination.getStart(),totalProductPage);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("sanphams",sanPhams1);
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        model.addAttribute("baseUrl",baseUrl);
        SanPham sanPham = new SanPham("SP"+function.Laystt(sanPhams2.get(0).getMaSP()));
        model.addAttribute("sanpham",sanPham);
        List<ThuongHieu> thuongHieus = adminService.getAllthuonghieu(1);
        model.addAttribute("thuonghieu",thuongHieus);
        List<KhuyenMai> khuyenMais = adminService.gethienKhuyenMai();
        model.addAttribute("khuyenmai",khuyenMais);
        List<LoaiSanPham> loaiSanPhams = adminService.getAllloaisp(1);
        model.addAttribute("loaisanpham",loaiSanPhams);

        return "admin/sanpham";
    }

    @RequestMapping(value = "/savesp",method = {RequestMethod.GET})
    @ResponseBody
    public String add(@RequestParam("maSP") String masp ,@RequestParam("maHang") String mahang,
                      @RequestParam("maLoaiSp") String maloaisp ,@RequestParam("maKM") String makm,
                      @RequestParam("tenSP") String tensp ,@RequestParam("donGia") String dongia ,
                      @RequestParam("manHinh") String manhinh ,@RequestParam("moTa") String mota){
        String manhinhadd = manhinh.substring(12);
        Float dongiaadd = Float.parseFloat(dongia);
        SanPham sanPham = new SanPham(masp,mahang,maloaisp,makm,tensp,dongiaadd,manhinhadd,mota);
        sanPhamService.Insert(sanPham);

        return " thêm dữ liệu thành công ";
    }


    @RequestMapping(value = "/updatesp",method = RequestMethod.GET)
    @ResponseBody
    public String update(@RequestParam("maSP") String masp ,@RequestParam("maHang") String mahang,
                         @RequestParam("maLoaiSp") String maloaisp ,@RequestParam("maKM") String makm,
                         @RequestParam("tenSP") String tensp ,@RequestParam("donGia") String dongia ,
                         @RequestParam("manHinh") String manhinh ,@RequestParam("moTa") String mota ){
        String manhinhadd = manhinh.substring(12);
        Float dongiaadd = Float.parseFloat(dongia);
        SanPham sanPham = new SanPham(masp,mahang,maloaisp,makm,tensp,dongiaadd,manhinhadd,mota);
        sanPhamService.Update(sanPham);
        return " Cập nhật dữ liệu thành công ";
    }

    @GetMapping("/findOnesp")
    @ResponseBody
    public SanPham get( @RequestParam(name = "id") String id){
        SanPham sanPham = sanPhamService.Get(id);
        if (sanPham.getMaSP() == id ) {
            return sanPham;
        }
        throw new NotFoundException("không có sản phẩm này trong hệ thống ");
    }

    @GetMapping("/deletesp")
    public String delete(@RequestParam(name = "id") String id){
        sanPhamService.Delete(id);
        return "redirect:/sanpham";
    }
}
