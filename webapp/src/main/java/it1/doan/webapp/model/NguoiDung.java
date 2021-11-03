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
}
