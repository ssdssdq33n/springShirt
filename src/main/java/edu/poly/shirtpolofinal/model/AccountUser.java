package edu.poly.shirtpolofinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUser implements Serializable {
    private String tendangnhap;
    private String matkhau;
}
