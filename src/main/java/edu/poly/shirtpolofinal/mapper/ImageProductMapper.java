package edu.poly.shirtpolofinal.mapper;

import edu.poly.shirtpolofinal.domain.ImagesProduct;
import edu.poly.shirtpolofinal.domain.Product;
import edu.poly.shirtpolofinal.model.ImagesProductDTO;

public class ImageProductMapper {
    public static ImagesProductDTO toImageProductDTO(ImagesProduct imagesProduct){
        return new ImagesProductDTO(
                imagesProduct.getImageId(),
                imagesProduct.getImageProduct(),
                imagesProduct.getProduct().getProductId()
        );
    }

    public  static ImagesProduct toImageProduct(ImagesProductDTO imagesProductDTO, Product product){
        return new ImagesProduct(
                imagesProductDTO.getImageId(),
                imagesProductDTO.getImageProduct(),
                product
        );
    }
}
