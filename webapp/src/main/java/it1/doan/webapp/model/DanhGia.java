package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DanhGia {
    private String maSP;
    private String tenSP;
    private int motsao;
    private int haisao;
    private int basao;
    private int bonsao;
    private int namsao;
    private int binhluan;
}
