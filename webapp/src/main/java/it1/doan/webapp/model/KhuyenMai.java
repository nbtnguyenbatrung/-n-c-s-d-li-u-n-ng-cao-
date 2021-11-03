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
public class KhuyenMai {
    private String maKM;
    private String tenKM;
    private Float  giaKM;
    private Date ngayBD;
    private Date ngayKT;
    private Boolean type;
    private String moTa;
    private String image;
    private int songay;

    public KhuyenMai (String maKM){
        this.maKM = maKM;
    }

    public KhuyenMai(String maKM, String tenKM, Float giaKM, Date ngayBD, Date ngayKT, Boolean type, String moTa, String image) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.giaKM = giaKM;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.type = type;
        this.moTa = moTa;
        this.image = image;
    }
}
