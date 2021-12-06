package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HomeSanPham {

    private String maSP;
    private String maHang;
    private String maKM;
    private String maLoaiSP;
    private String tenSP;
    private Float donGia;
    private int soluong;
    private String manHinh;
    private Float soSao;
    private String moTa;
    private int status;
    private String tenhang;
    private String tenloai;
    private Float giasau;

}
