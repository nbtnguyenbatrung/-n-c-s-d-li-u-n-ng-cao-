package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.excaption.NotFoundException;
import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.AdminService;
import it1.doan.webapp.admin.service.DanhGiaService;
import it1.doan.webapp.admin.service.SanPhamService;
import it1.doan.webapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ControllerAdminSanPhamDanhGia {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminPagepml adminPage;
    @Autowired
    DanhGiaService danhGiaService;
    @Autowired
    SanPhamService sanPhamService;
    private int totalProductPage = 9;

    @GetMapping("/sanphamdanhgia")
    public String getAdminsanphamdanhgia(Model model , final HttpServletResponse response ,
                                         @RequestParam(name = "page",required = false) String currentPage,
                                        @RequestParam(name = "id",required = false) String masp,
                                         @RequestParam(name = "sosao",required = false) String sosao,
                                        @RequestParam(name = "bl",required = false) String bl){
        String maspview;
        float sosaoview;
        String blview;
        String sosaourl;

        if (currentPage==null){
            currentPage = "1";
        }
        List<SanPham> sanPham = adminService.getAllsp(1);
        if (masp == null) {
            maspview = sanPham.get(0).getMaSP();
        }else{
            maspview = masp;
        }
        if (sosao == null){
            sosaourl = "6";
        }else{
            sosaourl = sosao;
        }
        if (bl == null ){
            blview = "";
        }else{
            blview = bl;
        }

        List<DanhGia> danhGias = danhGiaService.getAllDanhGia(maspview);
        SanPham sanPhams = sanPhamService.Get(maspview);
        if (sanPhams == null){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            String url = "http://localhost:9999/sanphamdanhgia";
            model.addAttribute("url",url);
            return "admin/404";
        }
        if (sanPhams.getSoSao() == null){
            sosaoview = 0;
        }else{
            sosaoview = sanPhams.getSoSao();
        }
        model.addAttribute("sosao",sosaoview);
        model.addAttribute("sanpham",sanPham);
        model.addAttribute("namsao",danhGias.get(0).getNamsao());
        model.addAttribute("bonsao",danhGias.get(0).getBonsao());
        model.addAttribute("basao",danhGias.get(0).getBasao());
        model.addAttribute("haisao",danhGias.get(0).getHaisao());
        model.addAttribute("motsao",danhGias.get(0).getMotsao());
        model.addAttribute("bl",danhGias.get(0).getBinhluan());
        model.addAttribute("hien",danhGias.get(0).getMaSP());
        List<BinhLuan> binhLuans = danhGiaService.getAllbl(maspview,Integer.parseInt(sosaourl),bl,1,10000000);
        Pagination pagination = adminPage.GetInfoPage(binhLuans.size(),totalProductPage,Integer.parseInt(currentPage));
        List<BinhLuan> binhLuans1 = danhGiaService.getAllbl(maspview,Integer.parseInt(sosaourl),bl,pagination.getStart(),totalProductPage);
        model.addAttribute("binhluan",binhLuans1);
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        String baseUrl = "/sanphamdanhgia?id="+maspview+"&sosao="+sosaourl+"&bl="+blview+"&page=";
        model.addAttribute("baseUrl",baseUrl);
        model.addAttribute("sosaoif",sosao);
        model.addAttribute("blif",blview);
        String url = "/sanphamdanhgia?id="+maspview+"&sosao=";
        model.addAttribute("url",url);
        String url1 = "&bl="+blview+"&page=1";
        model.addAttribute("url1",url1);
        return "admin/sanphamdanhgia";
    }

}
