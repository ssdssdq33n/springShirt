package edu.poly.shirtpolofinal.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(nullable = false)
    private  String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false,unique = true)
    private String tendangnhap;
    @Column(nullable = false)
    private String matkhau;

    @ManyToOne
    @JoinColumn(name = "id",nullable = false)
    private PhanQuyen phanQuyen;
}
