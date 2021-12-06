package it1.doan.webapp.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class ControllerAdmin {
    @GetMapping("/admin")
    public String admin(){
        return "admin/admin";
    }
}
