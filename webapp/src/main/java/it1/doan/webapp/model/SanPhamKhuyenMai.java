package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SanPhamKhuyenMai {
    private String  tenSP;
    private Float giaSP;
    private Float giakm;
    private Date ngaybd;
    private Date ngaykt;
    private Integer songay;
}
