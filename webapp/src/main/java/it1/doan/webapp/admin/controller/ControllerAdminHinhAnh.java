package it1.doan.webapp.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerAdminHinhAnh {

    @RequestMapping("/hinhanh")
    public String gethinhanh(Model model , @RequestParam(name = "id") String id){
        model.addAttribute("masp",id);
        return "admin/hinhanh";
    }
}
