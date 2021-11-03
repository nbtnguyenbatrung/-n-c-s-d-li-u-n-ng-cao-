package it1.doan.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ControllerProduct {

    @GetMapping(value = "product")
    public String getProduct(@RequestParam(name = "id") String id){
        System.out.println(id);
        return "product";
    }
}
