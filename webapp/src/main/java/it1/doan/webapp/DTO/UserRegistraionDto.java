package it1.doan.webapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegistraionDto {

    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String phone;
}
