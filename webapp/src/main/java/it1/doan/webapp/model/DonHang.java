package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DonHang {
    private String  maHD;
    private String maSP;
    private String maSize;
    private Float donGia;
    private int soluong;
    private int trangthai;
    private String moTa;
    private Date ngaylap;
    private String diachi;
    private String nguoinhan;
    private String sdt;
    private String tenSP;
    private String tenSize;
    private Float giatri;
    public DonHang(String maHD , Date ngaylap , Float giatri){
        this.maHD = maHD;
        this.ngaylap = ngaylap;
        this.giatri = giatri;
    }

}
