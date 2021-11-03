package it1.doan.webapp.controller;


import it1.doan.webapp.model.KhuyenMai;
import it1.doan.webapp.model.SanPham;
import it1.doan.webapp.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ControllerHome {
    @Autowired
    HomeService homeService;


    @GetMapping(value = {"/home","/"})
    public String getHome(Model model){
        List<KhuyenMai> khuyenMais= homeService.getAllKhuyenMai();
        List<SanPham>  sanPhamsWomen= homeService.getAllSanPhamWomen();
        List<SanPham>  sanPhamsChild= homeService.getAllSanPhamChild();
        List<SanPham>  sanPhamsMen= homeService.getAllSanPhamMen();
//        for (SanPham sanPham:sanPhams
//             ) {
//            System.out.println(sanPham.toString());
//        }
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("khuyenmais",khuyenMais);
        model.addAttribute("sanphamwomens",sanPhamsWomen);
        model.addAttribute("sanphammens",sanPhamsMen);
        model.addAttribute("sanphamchilds",sanPhamsChild);
        return "index";
    }



}
