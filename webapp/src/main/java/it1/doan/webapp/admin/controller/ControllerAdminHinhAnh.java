package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.impl.AdminHinhAnhService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.HinhAnh;
import it1.doan.webapp.model.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControllerAdminHinhAnh {
    @Autowired
    AdminHinhAnhService adminHinhAnhService;

    @Autowired
    UserService userService;

    @RequestMapping("/admin/hinhanh")
    public String gethinhanh(Model model , @RequestParam(name = "id") String id,
                             HttpServletRequest request){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            String name = authentication.getName();
            NguoiDung user = userService.getUserByEmail(name);
            if(user!=null){
                String userName = (String) request.getSession().getAttribute("username");
                if(userName==null){
                    request.getSession().setAttribute("username",user.getHoten());
                }
                model.addAttribute("username",user.getHoten());
            }

        }

        List<HinhAnh> hinhAnhs = adminHinhAnhService.getallHinhAnh(id);
        model.addAttribute("hinhanh",hinhAnhs);
        model.addAttribute("masp",id);
        return "admin/hinhanh";
    }

    @GetMapping("/saveha")
    @ResponseBody
    public String Insert(@RequestParam(name = "id") String id ,
                         @RequestParam(name = "list") List<String> list){
        adminHinhAnhService.Insert(id,list);
        return "Thêm thành công hình ảnh " ;
    }

    @GetMapping("/deleteha")
    public String Delete(@RequestParam(name = "id") String id,
                         @RequestParam(name = "id2") String masp){
        adminHinhAnhService.Delete(id);
        return "redirect:/admin/hinhanh?id="+masp;
    }

    @GetMapping("/updateha")
    @ResponseBody
    public String Update(@RequestParam(name = "id") String id ,
                         @RequestParam(name = "image") String image){
        adminHinhAnhService.Update(id,image);
        return " Cập nhật thành công ảnh ";
    }
}
