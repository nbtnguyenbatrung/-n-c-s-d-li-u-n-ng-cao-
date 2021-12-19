package it1.doan.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Size {
    private String maSize;
    private String tenSize;
    private int soluong;

    public Size(String maSize , String tenSize){
        this.maSize = maSize;
        this.tenSize = tenSize;
    }

    public Size(String maSize){
        this.maSize = maSize;
    }
}
