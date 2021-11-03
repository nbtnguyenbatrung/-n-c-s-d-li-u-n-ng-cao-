package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.AdminService;
import it1.doan.webapp.admin.service.KhuyenMaiService;
import it1.doan.webapp.admin.service.function;
import it1.doan.webapp.model.KhuyenMai;
import it1.doan.webapp.model.Pagination;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class ControllerAdminKhuyenMai {

    @Autowired
    AdminService adminService;
    @Autowired
    AdminPagepml adminPage;
    @Autowired
    KhuyenMaiService khuyenMaiService;
    @Autowired
    function function ;

    private int totalProductPage = 9;

    @GetMapping("/khuyenmai")
    public String getAdminKhuyenmai(Model model,@RequestParam(name = "page",required = false) String currentPage){
        if (currentPage == null) {
            currentPage = "1";
        }
        String baseUrl = "/khuyenmai?page=";
        List<KhuyenMai> khuyenMais = adminService.getAllKhuyenMai();
        int totalData = khuyenMais.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
        List<KhuyenMai> khuyenMais1 = adminService.getPagekm(pagination.getStart(),totalProductPage);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("khuyenmai",khuyenMais1);
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        model.addAttribute("baseUrl",baseUrl);
        KhuyenMai khuyenMai = new KhuyenMai("KM"+function.Laystt(khuyenMais1.get(0).getMaKM()));
        model.addAttribute("khuyenmaiadd",khuyenMai);
        return "admin/khuyenmai";
    }

    @RequestMapping(value = "/savekm",method = {RequestMethod.GET})
    @ResponseBody
    public String add(@RequestParam("maKM") String makm ,@RequestParam("tenKM") String tenkm,
                      @RequestParam("giaKM") String giakm,@RequestParam("ngayBD") String ngaybd,
                      @RequestParam("ngayKT") String ngaykt,@RequestParam("type") int type,
                      @RequestParam("moTa") String mota,@RequestParam("image") String image) throws ParseException {
        String imageadd = image.substring(12);
        Float giakmadd = Float.parseFloat(giakm);
        Date ngaybdadd = new SimpleDateFormat("dd/MM/yyyy").parse(ngaybd);
        Date ngayktadd = new SimpleDateFormat("dd/MM/yyyy").parse(ngaykt);
        boolean typeadd;
        if (type == 1){
            typeadd =true;
        }else {
            typeadd=false;
        }
        KhuyenMai khuyenMai = new KhuyenMai(makm,tenkm,giakmadd,ngaybdadd,ngayktadd,typeadd,mota,imageadd);
        khuyenMaiService.Insert(khuyenMai);

        return " thêm dữ liệu thành công ";
    }


    @RequestMapping(value = "/updatekm",method = RequestMethod.GET)
    @ResponseBody
    public String update(@RequestParam("maKM") String makm ,@RequestParam("tenKM") String tenkm,
                         @RequestParam("giaKM") String giakm,@RequestParam("ngayBD") String ngaybd,
                         @RequestParam("ngayKT") String ngaykt,@RequestParam("type") int type,
                         @RequestParam("moTa") String mota,@RequestParam("image") String image ) throws ParseException {
        String imageadd = image.substring(12);
        Float giakmadd = Float.parseFloat(giakm);
        Date ngaybdadd = new SimpleDateFormat("dd/MM/yyyy").parse(ngaybd);
        Date ngayktadd = new SimpleDateFormat("dd/MM/yyyy").parse(ngaykt);
        boolean typeadd;
        if (type == 1){
            typeadd =true;
        }else {
            typeadd=false;
        }
        KhuyenMai khuyenMai = new KhuyenMai(makm,tenkm,giakmadd,ngaybdadd,ngayktadd,typeadd,mota,imageadd);
        khuyenMaiService.Update(khuyenMai);
        return " Cập nhật dữ liệu thành công ";
    }

    @GetMapping("/findOnekm")
    @ResponseBody
    public KhuyenMai get( @RequestParam(name = "id") String id){
        KhuyenMai khuyenMai = khuyenMaiService.Get(id);
        return khuyenMai;
    }

    @GetMapping("/deletekm")
    public String delete(@RequestParam(name = "id") String id){
        khuyenMaiService.Delete(id);
        return "redirect:/khuyenmai";
    }
}
