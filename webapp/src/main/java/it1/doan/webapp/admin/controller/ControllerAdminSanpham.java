package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.excaption.NotFoundException;
import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.SanPhamService;
import it1.doan.webapp.admin.service.function;
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
public class ControllerAdminSanpham {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminPagepml adminPage;
    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    function function ;
    @Autowired
    UserService userService;

    private int totalProductPage = 9;

    @GetMapping("/admin/sanpham")
    public String getAdminsanpham(Model model , HttpServletRequest request){

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

        List<SanPham> sanPhams2 = adminService.getAllsp(2);
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

    @RequestMapping(value = "/admin/sanphamall",method = {RequestMethod.GET})
    @ResponseBody
    public List<SanPham> getallsanpham(@RequestParam(name = "page",required = false) int currentPage,
                                             @RequestParam(name = "keyword",required = false) String keyword){
        List<SanPham> sanPhams = adminService.getallsp(keyword);
        int totalData = sanPhams.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,currentPage);
        List<SanPham> sanPhams1 = adminService.getPagesp(pagination.getStart(),totalProductPage,keyword);

        return sanPhams1;
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

            return sanPham;
    }

    @GetMapping("/deletesp")
    public String delete(@RequestParam(name = "id") String id){
        sanPhamService.Delete(id);
        return "redirect:/sanpham";
    }
}
