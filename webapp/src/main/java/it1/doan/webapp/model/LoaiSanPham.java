package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoaiSanPham {
    private String maLoaiSP;
    private String tenLoaiSP;
    private int soluong;

    public LoaiSanPham(String maLoaiSP){
        this.maLoaiSP = maLoaiSP;
    }

    public LoaiSanPham(String maLoaiSP,String tenLoaiSP){
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
    }
}
