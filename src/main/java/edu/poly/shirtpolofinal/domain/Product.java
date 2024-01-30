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
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(nullable = false)
    private  String name;
//    @Lob
//    @Column(nullable = false,length = 65350)
//    private String image;
    @Lob
    @Column(nullable = false,length = 65555)
    private String decription;
//    @Column(nullable = false)
//    private String size;
    @Column(nullable = false)
    private double sale;
    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "categoryId",nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<ImagesProduct> imagesProducts;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<Cart> carts;
}
