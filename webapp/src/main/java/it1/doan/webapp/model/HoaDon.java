package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HoaDon {
    private String maHD;
    private int ID;
    private String nguoinhan;
    private String sdt;
    private String diachi;

}
