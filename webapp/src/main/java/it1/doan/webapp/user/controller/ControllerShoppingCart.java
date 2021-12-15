package it1.doan.webapp.user.controller;

import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.*;
import it1.doan.webapp.user.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControllerShoppingCart {

    @Autowired
    HomeService homeService;
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;


    @GetMapping("/shopping-cart")
    public String getshoppingcart(Model model, HttpServletRequest request){

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
                List<GioHang> gioHangs = homeService.getghbynd(user.getID());
                model.addAttribute("giohang",gioHangs);
                model.addAttribute("sl",gioHangs.size());
                model.addAttribute("id",user.getID());
                float tong = 0 ;
                for (int i = 0 ; i<gioHangs.size() ; i++){
                    if(gioHangs.get(i).getGiaSau() > 0){
                        tong = tong + gioHangs.get(i).getGiaSau() * gioHangs.get(i).getSoluong();
                    }else{
                        tong = tong + gioHangs.get(i).getDongia() * gioHangs.get(i).getSoluong();
                    }
                }

                model.addAttribute("tong",tong);
            }

        }

        List<LoaiSanPham> loaiSanPhams = adminService.getAllloaisp(1);
        model.addAttribute("loaisp" , loaiSanPhams);

        List<ThuongHieu> thuongHieus = adminService.getAllthuonghieu(1);
        model.addAttribute("thuongHieus",thuongHieus);

        return "shopping-cart";
    }


    @GetMapping("/check-out")
    public String getcheckout(Model model, HttpServletRequest request,
                              @RequestParam(name = "id" ,required = false) int id ,
                              @RequestParam( name = "masp" ,required = false) String masp ,
                              @RequestParam( name = "masize" , required = false) String masize,
                              @RequestParam(name = "soluong" , required = false) int soluong){

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
                List<GioHang> gioHangs = homeService.getghbynd(user.getID());
                model.addAttribute("giohang",gioHangs);
                model.addAttribute("sl",gioHangs.size());
            }

        }

        List<LoaiSanPham> loaiSanPhams = adminService.getAllloaisp(1);
        model.addAttribute("loaisp" , loaiSanPhams);

        List<ThuongHieu> thuongHieus = adminService.getAllthuonghieu(1);
        model.addAttribute("thuongHieus",thuongHieus);

        if(masp != null && masize != null && soluong > 0 ){
            GioHang gioHang = new GioHang(id,masp,masize,soluong);
            if(homeService.getgiohang(gioHang)){
                homeService.updateadd(gioHang);
            }else{
                homeService.insert(gioHang);
            }
            List<GioHang> gioHang1 = homeService.getgiohangbyone(gioHang);
            model.addAttribute("gioHang1",gioHang1);
            float tong = 0 ;
            for (int i = 0 ; i<gioHang1.size() ; i++){
                if(gioHang1.get(i).getGiaSau() > 0){
                    tong = tong + gioHang1.get(i).getGiaSau() * gioHang1.get(i).getSoluong();
                }else{
                    tong = tong + gioHang1.get(i).getDongia() * gioHang1.get(i).getSoluong();
                }
            }

            model.addAttribute("tong",tong);
        }else{
            List<GioHang> gioHang1 = homeService.getghbynd(id);
            model.addAttribute("gioHang1",gioHang1);
            float tong = 0 ;
            for (int i = 0 ; i<gioHang1.size() ; i++){
                if(gioHang1.get(i).getGiaSau() > 0){
                    tong = tong + gioHang1.get(i).getGiaSau() * gioHang1.get(i).getSoluong();
                }else{
                    tong = tong + gioHang1.get(i).getDongia() * gioHang1.get(i).getSoluong();
                }
            }

            model.addAttribute("tong",tong);
        }

        return "check-out";
    }

    @RequestMapping("/admin/savesc")
    @ResponseBody
    public String insert(@RequestParam("id") int id , @RequestParam("masp") String masp,
                         @RequestParam("masize") String masize,@RequestParam("soluong") int soluong){
        GioHang gioHang = new GioHang(id,masp,masize,soluong);
        if(homeService.getgiohang(gioHang)){
            homeService.updateadd(gioHang);
        }else{
            homeService.insert(gioHang);
        }

        return " thêm vào giỏ hàng thành công ";
    }


    @RequestMapping("/admin/updatesc")
    @ResponseBody
    public void update(@RequestParam("id") int id , @RequestParam("masp") String masp,
                         @RequestParam("masize") String masize,@RequestParam("soluong") int soluong){
        GioHang gioHang = new GioHang(id,masp,masize,soluong);
        homeService.update(gioHang);
    }

    @GetMapping("/admin/deletesc")
    public void update(@RequestParam("id") int id , @RequestParam("masp") String masp,
                       @RequestParam("masize") String masize ){
        GioHang gioHang = new GioHang(id,masp,masize);
        homeService.delete(gioHang);
    }
}
