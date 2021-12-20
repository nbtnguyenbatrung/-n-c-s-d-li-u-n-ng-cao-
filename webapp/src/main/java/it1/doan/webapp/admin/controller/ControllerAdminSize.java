package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.SizeService;
import it1.doan.webapp.admin.service.function;
import it1.doan.webapp.model.Pagination;
import it1.doan.webapp.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ControllerAdminSize {
    @Autowired
    AdminService adminService;
    @Autowired
    SizeService sizeService;
    @Autowired
    AdminPagepml adminPage;
    private int totalProductPage = 9;
    @Autowired
    function function ;

    @GetMapping("/size")
    public String getAdminsize(Model model ,@RequestParam(name = "page",required = false) String currentPage){
        if (currentPage == null) {
            currentPage = "1";
        }
        List<Size> sizes2 = adminService.getAllsize(2);
        List<Size> sizes = adminService.getAllsize(1);
        int totalData = sizes.size();
        String baseUrl = "/size?page=";
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,Integer.parseInt(currentPage));
        List<Size> sizes1 = adminService.getPageSize(pagination.getStart(),totalProductPage);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("sizepage",sizes1);
        model.addAttribute("Page",pagination);
        model.addAttribute("totalpage",pagination.getTotalPage());
        model.addAttribute("CurrentPage",pagination.getCurrentPage());
        model.addAttribute("Previous",pagination.getCurrentPage()-1);
        model.addAttribute("Next",pagination.getCurrentPage()+1);
        model.addAttribute("PreviousLeft",pagination.getCurrentPage()-2);
        model.addAttribute("PreviousRight",pagination.getCurrentPage()+2);
        model.addAttribute("baseUrl",baseUrl);
        Size size = new Size(function.Laystt(sizes2.get(0).getMaSize()));
        model.addAttribute("size",size);
        return "admin/size";
    }


    @RequestMapping(value = "/savesize",method = {RequestMethod.GET})
    @ResponseBody
    public String add(@RequestParam("maSize") String masize ,@RequestParam("tenSize") String tenSize ){

        Size size = new Size(masize,tenSize);
        sizeService.Insert(size);

        return " thêm dữ liệu thành công ";
    }


    @RequestMapping(value = "/updatesize",method = RequestMethod.GET)
    @ResponseBody
    public String update(@RequestParam("maSize") String masize ,@RequestParam("tenSize") String tensize ){

        Size size = new Size(masize,tensize);
        sizeService.Update(size);
        return " Cập nhật dữ liệu thành công ";
    }

    @GetMapping("/findOnesize")
    @ResponseBody
    public Size get( @RequestParam(name = "id") String id){
        Size sizes = sizeService.Get(id);
        return sizes;
    }

    @GetMapping("/deletesize")
    public String delete(@RequestParam(name = "id") String id){
        sizeService.Delete(id);
        return "redirect:/size";
    }
}
