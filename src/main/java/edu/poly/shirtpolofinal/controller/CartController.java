package edu.poly.shirtpolofinal.controller;

import edu.poly.shirtpolofinal.domain.Cart;
import edu.poly.shirtpolofinal.domain.Category;
import edu.poly.shirtpolofinal.domain.Product;
import edu.poly.shirtpolofinal.mapper.ProductMapper;
import edu.poly.shirtpolofinal.model.ProductDTO;
import edu.poly.shirtpolofinal.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("carts")
public class CartController {
    @Autowired
    public CartService cartService;

    @PostMapping("{size}")
    public ResponseEntity<Cart> createCart(@RequestBody ProductDTO productDTO,@PathVariable("size") String size){
        Category category=new Category();
        Product product= ProductMapper.toMapperProduct(productDTO,category);
        Cart cart=cartService.createCart(product,size);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PostMapping("{size}/{quantity}")
    public ResponseEntity<Cart> createCartAndQuantity(@RequestBody ProductDTO productDTO,@PathVariable("size") String size,@PathVariable("quantity") int quantity){
        Category category=new Category();
        Product product= ProductMapper.toMapperProduct(productDTO,category);
        Cart cart=cartService.createCartAndQuantity(product,size,quantity);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }
}
