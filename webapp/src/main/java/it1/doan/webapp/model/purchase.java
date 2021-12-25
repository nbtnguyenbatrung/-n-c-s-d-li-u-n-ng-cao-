package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class purchase {
        String maHD;
        String maSP;
        String maSize;
        int soluong ;
        Float dongia;
        String tensize;
        String tenSP;
        Float giagoc;
        String manHinh;
        Float tongtien;
        int trangthai;
        int songay;
}
