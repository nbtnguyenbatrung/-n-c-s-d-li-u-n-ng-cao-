package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DanhGiaByUser {
    private String maDG;
    private int id ;
    private String maSP;
    private int sosao ;
    private String binhluan;
}
