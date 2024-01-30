package edu.poly.shirtpolofinal.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "imageproduct")
public class ImagesProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;
    @Lob
    @Column(nullable = false,length = 65350)
    private  String imageProduct;

    @ManyToOne
    @JoinColumn(name = "productId",nullable = false)
    private Product product;
}
