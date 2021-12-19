package it1.doan.webapp.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControllerPurchase {

    @GetMapping("/purchase")
    public String getpurchase(){

        return "purchase";
    }
}
