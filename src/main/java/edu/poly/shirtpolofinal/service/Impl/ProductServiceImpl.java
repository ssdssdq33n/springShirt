package edu.poly.shirtpolofinal.service.Impl;

import edu.poly.shirtpolofinal.domain.Category;
import edu.poly.shirtpolofinal.domain.Product;
import edu.poly.shirtpolofinal.exception.ResoureNotFoundException;
import edu.poly.shirtpolofinal.mapper.CategoryMapper;
import edu.poly.shirtpolofinal.mapper.ProductMapper;
import edu.poly.shirtpolofinal.model.CategoryDTO;
import edu.poly.shirtpolofinal.model.ProductDTO;
import edu.poly.shirtpolofinal.repository.ProductRepository;
import edu.poly.shirtpolofinal.service.CategoryService;
import edu.poly.shirtpolofinal.service.ProductService;
import edu.poly.shirtpolofinal.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        CategoryDTO categoryDTO =categoryService.getCategoryById(productDTO.getCategoryId());
        Category category= CategoryMapper.toMapperCategory(categoryDTO);
        Product product= ProductMapper.toMapperProduct(productDTO,category);
        Product productSave=productRepository.save(product);
        return ProductMapper.toMapperProductDTO(productSave);
    }

    @Override
    public ProductDTO getProductId(int productId) {
        Product product=productRepository.findById(productId).orElseThrow(()->new ResoureNotFoundException("Product is not exisst with given id"));
        return ProductMapper.toMapperProductDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products=productRepository.findAll();
        return products.stream().map(product -> ProductMapper.toMapperProductDTO(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(int productId, ProductDTO updatedProduct) {
        CategoryDTO categoryDTO=categoryService.getCategoryById(updatedProduct.getCategoryId());
        Category category=CategoryMapper.toMapperCategory(categoryDTO);
        Product product=productRepository.findById(productId).orElseThrow(()->new ResoureNotFoundException("Product is not exisst with given id"));
        product.setName(updatedProduct.getName());
        product.setSale(updatedProduct.getSale());
        product.setPrice(updatedProduct.getPrice());
        product.setDecription(updatedProduct.getDecription());
        product.setCategory(category);
         Product productUpdate =productRepository.save(product);
        return ProductMapper.toMapperProductDTO(productUpdate);
    }

    @Override
    public void deleteProduct(int productId) {
        Product product=productRepository.findById(productId).orElseThrow(()->new ResoureNotFoundException("Product is not exisst with given id"));
        productRepository.delete(product);
    }
}
