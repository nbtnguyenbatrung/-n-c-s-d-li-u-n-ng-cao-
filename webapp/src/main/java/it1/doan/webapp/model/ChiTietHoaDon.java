package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChiTietHoaDon {

    private String maHD;
    private String maSP;
    private String maSize;
    private int soluong;
    private Float dongia;

}
