package it1.doan.webapp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductSearch {
    private String mahang;
    private String maloaisp;
    private String masize;
    private String tensp;

}
