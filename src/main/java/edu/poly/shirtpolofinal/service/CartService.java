package edu.poly.shirtpolofinal.service;

import edu.poly.shirtpolofinal.domain.Cart;
import edu.poly.shirtpolofinal.domain.PhanQuyen;
import edu.poly.shirtpolofinal.domain.Product;
import edu.poly.shirtpolofinal.model.UserDTO;

public interface CartService {
    Cart createCart(Product product,String size);
    Cart createCartAndQuantity(Product product,String size,int quantity);
}
