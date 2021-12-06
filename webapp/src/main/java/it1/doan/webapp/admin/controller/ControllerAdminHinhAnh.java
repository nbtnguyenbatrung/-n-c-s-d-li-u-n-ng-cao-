package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.impl.AdminHinhAnhService;
import it1.doan.webapp.model.HinhAnh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ControllerAdminHinhAnh {
    @Autowired
    AdminHinhAnhService adminHinhAnhService;


    @RequestMapping("/hinhanh")
    public String gethinhanh(Model model , @RequestParam(name = "id") String id){
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
        return "redirect:/hinhanh?id="+masp;
    }

    @GetMapping("/updateha")
    @ResponseBody
    public String Update(@RequestParam(name = "id") String id ,
                         @RequestParam(name = "image") String image){
        adminHinhAnhService.Update(id,image);
        return " Cập nhật thành công ảnh ";
    }
}
