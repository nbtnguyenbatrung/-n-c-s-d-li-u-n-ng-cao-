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
public class NguoiDung {
    private int ID;
    private String hoten;
    private String sdt;
    private String email;
    private String mk;
    private int Status;
    private String quyen;
    private Date ngaytao;

    public NguoiDung(String hoten, String sdt, String email, String mk, int status , String quyen) {
        this.hoten = hoten;
        this.sdt = sdt;
        this.email = email;
        this.mk = mk ;
        this.Status = status;
        this.quyen = quyen;
    }

    public NguoiDung(int ID, String hoten, String sdt, String email , int status , String quyen) {
        this.ID = ID;
        this.hoten = hoten;
        this.sdt = sdt;
        this.email = email;
        this.Status = status;
        this.quyen = quyen;
    }
}
