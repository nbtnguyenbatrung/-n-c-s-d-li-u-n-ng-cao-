package it1.doan.webapp.user.controller;

import it1.doan.webapp.admin.service.function;
import it1.doan.webapp.model.ChiTietHoaDon;
import it1.doan.webapp.model.GioHang;
import it1.doan.webapp.model.HoaDon;
import it1.doan.webapp.user.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerCart {

    @Autowired
    function function ;
    @Autowired
    HomeService homeService;

    @RequestMapping("/admnin/cart")
    public void cart(@RequestParam(name = "id") int id ,
                     @RequestParam(name = "name") String name ,
                     @RequestParam(name = "address") String address,
                     @RequestParam(name = "phone") String phone,
                     @RequestParam(name = "masp") String masp,
                     @RequestParam(name = "masize") String masize){
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
        }
    }
}
