package it1.doan.webapp.controller;


import it1.doan.webapp.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class test {

    @GetMapping("/home")
    public String getHome(){
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }


    @GetMapping("/contact")
    public String getContact(){
        return "contact";
    }


    @GetMapping("/shopping-cart")
    public String getShopping_cart(){
        return "shopping-cart";
    }

    @GetMapping("/check-out")
    public String getCheck_out(){
        return "check-out";
    }

    @GetMapping("/shop")
    public String getShop(){
        return "shop";
    }

    @GetMapping("/admin")
    public String getAdmin(){ return "admin/index"; }



//    @Autowired
//    FileUploadService fileUploadService;
//
//    @PostMapping("/testfile")
//    public void uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {
//        fileUploadService.upload(file);
//        System.out.println(file.toString());
//    }


}
