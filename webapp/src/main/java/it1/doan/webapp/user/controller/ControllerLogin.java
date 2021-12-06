package it1.doan.webapp.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

@Controller
public class ControllerLogin {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public String logou(HttpServletRequest request , HttpServletResponse response, Session session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            request.getSession().invalidate();
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "index";
    }

}
