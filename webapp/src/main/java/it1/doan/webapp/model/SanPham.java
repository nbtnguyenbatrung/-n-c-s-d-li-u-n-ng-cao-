package it1.doan.webapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SanPham {
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

    public SanPham(String maSP) {
        this.maSP = maSP;
    }

    public SanPham(String maSP, String maHang, String maLoaiSP, String maKM, String tenSP, Float donGia, String manHinh, String moTa) {
        this.maSP = maSP;
        this.maHang = maHang;
        this.maLoaiSP = maLoaiSP;
        this.maKM = maKM;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.manHinh = manHinh;
        this.moTa = moTa;
    }
}
