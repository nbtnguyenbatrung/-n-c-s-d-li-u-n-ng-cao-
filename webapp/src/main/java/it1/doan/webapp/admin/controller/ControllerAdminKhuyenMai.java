package it1.doan.webapp.admin.controller;

import it1.doan.webapp.admin.service.AdminPagepml;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.KhuyenMaiService;
import it1.doan.webapp.admin.service.function;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.KhuyenMai;
import it1.doan.webapp.model.NguoiDung;
import it1.doan.webapp.model.Pagination;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class ControllerAdminKhuyenMai {

    @Autowired
    AdminService adminService;
    @Autowired
    AdminPagepml adminPage;
    @Autowired
    KhuyenMaiService khuyenMaiService;
    @Autowired
    function function ;
    @Autowired
    UserService userService;

    private int totalProductPage = 9;

    @GetMapping("/admin/khuyenmai")
    public String getAdminKhuyenmai(Model model , HttpServletRequest request){

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

        List<KhuyenMai> khuyenMais = adminService.getAllKhuyenMai();
        KhuyenMai khuyenMai = new KhuyenMai("KM"+function.Laystt(khuyenMais.get(0).getMaKM()));
        model.addAttribute("khuyenmaiadd",khuyenMai);
        return "admin/khuyenmai";
    }

    @RequestMapping(value = "/admin/khuyenmaiall",method = {RequestMethod.GET})
    @ResponseBody
    public List<KhuyenMai> getallkhuyemai(@RequestParam(name = "page",required = false) int currentPage,
                                             @RequestParam(name = "keyword",required = false) String keyword){
        List<KhuyenMai> khuyenMais = adminService.getAllKhuyenMai(keyword);
        int totalData = khuyenMais.size();
        Pagination pagination = adminPage.GetInfoPage(totalData,totalProductPage,currentPage);
        List<KhuyenMai> khuyenMais1 = adminService.getPagekm(pagination.getStart(),totalProductPage,keyword);

        return khuyenMais1;
    }

    @RequestMapping(value = "/savekm",method = {RequestMethod.GET})
    @ResponseBody
    public String add(@RequestParam("maKM") String makm ,@RequestParam("tenKM") String tenkm,
                      @RequestParam("giaKM") String giakm,@RequestParam("ngayBD") String ngaybd,
                      @RequestParam("ngayKT") String ngaykt,@RequestParam("type") int type,
                      @RequestParam("moTa") String mota,@RequestParam("image") String image) throws ParseException {
        String imageadd = image.substring(12);
        Float giakmadd = Float.parseFloat(giakm);
        Date ngaybdadd = new SimpleDateFormat("dd/MM/yyyy").parse(ngaybd);
        Date ngayktadd = new SimpleDateFormat("dd/MM/yyyy").parse(ngaykt);
        boolean typeadd;
        if (type == 1){
            typeadd =true;
        }else {
            typeadd=false;
        }
        KhuyenMai khuyenMai = new KhuyenMai(makm,tenkm,giakmadd,ngaybdadd,ngayktadd,typeadd,mota,imageadd);
        khuyenMaiService.Insert(khuyenMai);

        return " thêm dữ liệu thành công ";
    }


    @RequestMapping(value = "/updatekm",method = RequestMethod.GET)
    @ResponseBody
    public String update(@RequestParam("maKM") String makm ,@RequestParam("tenKM") String tenkm,
                         @RequestParam("giaKM") String giakm,@RequestParam("ngayBD") String ngaybd,
                         @RequestParam("ngayKT") String ngaykt,@RequestParam("type") int type,
                         @RequestParam("moTa") String mota,@RequestParam("image") String image ) throws ParseException {
        String imageadd = image.substring(12);
        Float giakmadd = Float.parseFloat(giakm);
        Date ngaybdadd = new SimpleDateFormat("dd/MM/yyyy").parse(ngaybd);
        Date ngayktadd = new SimpleDateFormat("dd/MM/yyyy").parse(ngaykt);
        boolean typeadd;
        if (type == 1){
            typeadd =true;
        }else {
            typeadd=false;
        }
        KhuyenMai khuyenMai = new KhuyenMai(makm,tenkm,giakmadd,ngaybdadd,ngayktadd,typeadd,mota,imageadd);
        khuyenMaiService.Update(khuyenMai);
        return " Cập nhật dữ liệu thành công ";
    }

    @GetMapping("/findOnekm")
    @ResponseBody
    public KhuyenMai get( @RequestParam(name = "id") String id){
        KhuyenMai khuyenMai = khuyenMaiService.Get(id);
        return khuyenMai;
    }

    @GetMapping("/deletekm")
    public String delete(@RequestParam(name = "id") String id){
        khuyenMaiService.Delete(id);
        return "redirect:/khuyenmai";
    }
}
