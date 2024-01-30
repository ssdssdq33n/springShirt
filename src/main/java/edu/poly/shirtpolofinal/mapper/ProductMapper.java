package edu.poly.shirtpolofinal.mapper;

import edu.poly.shirtpolofinal.domain.Category;
import edu.poly.shirtpolofinal.domain.Product;
import edu.poly.shirtpolofinal.model.ProductDTO;

public class ProductMapper {
    public static ProductDTO toMapperProductDTO(Product product){
        return new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getDecription(),
                product.getSale(),
                product.getPrice(),
                product.getCategory().getCategoryId()
        );
    }

    public static Product toMapperProduct(ProductDTO productDTO, Category category){
        return new Product(
                productDTO.getProductId(),
                productDTO.getName(),
                productDTO.getDecription(),
                productDTO.getSale(),
                productDTO.getPrice(),
                category,
                null,
                null
        );
    }
}
