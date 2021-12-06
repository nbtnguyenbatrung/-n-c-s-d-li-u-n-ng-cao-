package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.model.NguoiDung;
import it1.doan.webapp.model.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ControllerAdminNguoiDung {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminPagepml adminPage;
    private int totalProductPage = 9;

    @GetMapping("/nguoidung")
    public String getAdminnguoidung(Model model ,@RequestParam(name = "page",required = false) String currentPage){
        if (currentPage == null) {
            currentPage = "1";
        }
        List<NguoiDung> nguoiDungs = adminService.getAllnd();
        int totalData = nguoiDungs.size();
        String baseUrl = "/nguoidung?page=";
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
        List<NguoiDung> nguoiDungs1 = adminService.getPagend(pagination.getStart(),totalProductPage);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("nguoidung",nguoiDungs1);
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        model.addAttribute("baseUrl",baseUrl);
        return "admin/nguoidung";
    }
}
