package edu.poly.shirtpolofinal.service;

import edu.poly.shirtpolofinal.model.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO getProductId(int productId);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(int productId,ProductDTO updatedProduct);
    void deleteProduct(int productId);
}
