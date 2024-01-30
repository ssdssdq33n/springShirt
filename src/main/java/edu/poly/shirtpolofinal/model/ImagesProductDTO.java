package edu.poly.shirtpolofinal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagesProductDTO implements Serializable {

    private int imageId;
    private  String imageProduct;
    private int productId;
}
