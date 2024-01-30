package edu.poly.shirtpolofinal.controller;

import edu.poly.shirtpolofinal.model.ProductDTO;
import edu.poly.shirtpolofinal.service.ProductService;
import edu.poly.shirtpolofinal.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    public ProductService productService;

    @Autowired
    public StorageService storageService;

//    @GetMapping("images/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
//        Resource file=storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getFilename() + "\"").body(file);
//    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO productDTOsave=productService.createProduct(productDTO);
        return new ResponseEntity<>(productDTOsave, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        List<ProductDTO> productDTOS=productService.getAllProducts();
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") int productId){
        ProductDTO productDTO=productService.getProductId(productId);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") int productId,@RequestBody ProductDTO productDTO){
        System.out.println(productDTO);
        ProductDTO productDTOupdate=productService.updateProduct(productId,productDTO);
        return ResponseEntity.ok(productDTOupdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("thanh cong");
    }
}
