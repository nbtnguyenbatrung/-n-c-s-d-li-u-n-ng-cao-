package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ThuongHieu {
    private String maHang ;
    private String tenHang;
    private String logo;
    public ThuongHieu(String maHang){
        this.maHang = maHang;
    }
}
