package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.SizeService;
import it1.doan.webapp.admin.service.function;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.NguoiDung;
import it1.doan.webapp.model.Pagination;
import it1.doan.webapp.model.Size;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    UserService userService;

    @GetMapping("/admin/size")
    public String getAdminsize(Model model , HttpServletRequest request){

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

        List<Size> sizes2 = adminService.getAllsize(2);
        Size size = new Size("SI"+function.Laystt(sizes2.get(0).getMaSize()));
        model.addAttribute("size",size);
        return "admin/size";
    }

    @RequestMapping(value = "/admin/getallsize",method = {RequestMethod.GET})
    @ResponseBody
    public List<Size> getallsize(@RequestParam(name = "page",required = false) int currentPage,
                                 @RequestParam(name = "keyword",required = false) String keyword){
        List<Size> sizes = adminService.getallsize(keyword);
        int totalData = sizes.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,currentPage);
        List<Size> sizes1 = adminService.getPageSize(pagination.getStart(),totalProductPage,keyword);

        return sizes1;
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
