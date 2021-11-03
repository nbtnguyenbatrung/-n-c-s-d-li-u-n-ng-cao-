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

    public Size(String maSize){
        this.maSize = maSize;
    }
}
