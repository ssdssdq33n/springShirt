package edu.poly.shirtpolofinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private int productId;
    private String name;
//    private String image;
    private String decription;
//    private String size;
    private double sale;
    private double price;
    private int categoryId;
}
