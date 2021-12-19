package it1.doan.webapp.user.controller;

import it1.doan.webapp.admin.service.function;
import it1.doan.webapp.model.ChiTietHoaDon;
import it1.doan.webapp.model.GioHang;
import it1.doan.webapp.model.HoaDon;
import it1.doan.webapp.user.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerCart {

    @Autowired
    function function ;
    @Autowired
    HomeService homeService;

    @RequestMapping( "/cart" )
    public String getcart(
                          @RequestParam(name = "id" , required = false) int id ,
                          @RequestParam(name = "name" , required = false) String name ,
                          @RequestParam(name = "address" , required = false) String address,
                          @RequestParam(name = "phone" ,required = false) String phone,
                          @RequestParam(name = "masp" ,required = false) String masp,
                          @RequestParam(name = "masize" ,required = false) String masize){
        List<HoaDon> hoaDons = homeService.getallhd();
        String mahd = "HD" + function.Laystt(hoaDons.get(0).getMaHD());
        HoaDon hoaDon = new HoaDon(mahd,id,name,phone,address);
        homeService.Insert(hoaDon);

        if(masp != null && masize != null ){
            GioHang gioHang = new GioHang(id,masp,masize);
            List<GioHang> gioHangs = homeService.getgiohangbyone(gioHang);
            List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
            for (int i = 0 ; i < gioHangs.size() ; i++){
                if (gioHangs.get(i).getGiaSau() > 0 ){
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(mahd,gioHangs.get(i).getMaSP(),gioHangs.get(i).getMaSize(),gioHangs.get(i).getSoluong(),gioHangs.get(i).getGiaSau());
                    chiTietHoaDons.add(chiTietHoaDon);
                }else {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(mahd, gioHangs.get(i).getMaSP(), gioHangs.get(i).getMaSize(), gioHangs.get(i).getSoluong(), gioHangs.get(i).getDongia());
                    chiTietHoaDons.add(chiTietHoaDon);
                }
            }

            homeService.Insert(chiTietHoaDons);
            homeService.delete(gioHangs);

        }else{
            List<GioHang> gioHangs = homeService.getghbynd(id);
            List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
            for (int i = 0 ; i < gioHangs.size() ; i++){
                if (gioHangs.get(i).getGiaSau() > 0 ){
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(mahd,gioHangs.get(i).getMaSP(),gioHangs.get(i).getMaSize(),gioHangs.get(i).getSoluong(),gioHangs.get(i).getGiaSau());
                    chiTietHoaDons.add(chiTietHoaDon);
                }else {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(mahd, gioHangs.get(i).getMaSP(), gioHangs.get(i).getMaSize(), gioHangs.get(i).getSoluong(), gioHangs.get(i).getDongia());
                    chiTietHoaDons.add(chiTietHoaDon);
                }
            }

            homeService.Insert(chiTietHoaDons);
            homeService.delete(id);
        }
        return "redirect:/purchase?id="+id;
    }
}
