package edu.poly.shirtpolofinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private int userId;
    private String name;
    private String email;
    private String tendangnhap;
    private String matkhau;
    private int id;
}
