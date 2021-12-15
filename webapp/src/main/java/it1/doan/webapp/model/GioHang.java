package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GioHang {

    private int ID;
    private String maSP;
    private String maSize;
    private int soluong;
    private String Image;
    private String TenSP;
    private String TenSize;
    private Float Dongia;
    private Float GiaSau;

    public GioHang(int id, String masp , String masize ,int soluong){
        this.ID = id;
        this.maSP = masp;
        this.maSize = masize;
        this.soluong = soluong;
    }

    public GioHang(int id, String masp , String masize ){
        this.ID = id;
        this.maSP = masp;
        this.maSize = masize;
    }
}
