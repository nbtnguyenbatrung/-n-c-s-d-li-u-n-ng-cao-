package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SanPhamSize {
    private String  maSP;
    private String tenSP;
    private String maSize;
    private String tenSize;
    private int soluong;

    public SanPhamSize(String maSP , String maSize , int soluong){
        this.maSP = maSP;
        this.maSize = maSize;
        this.soluong = soluong;
    }

}
