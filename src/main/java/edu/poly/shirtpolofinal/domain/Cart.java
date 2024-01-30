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
@Table(name = "cart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    @Column(nullable = false)
    private  int quantity;
    @Column(nullable = false)
    private  String size;

    @ManyToOne
    @JoinColumn(name = "productId",nullable = false)
    private Product product;
}
