package edu.poly.shirtpolofinal.service.Impl;

import edu.poly.shirtpolofinal.domain.Cart;
import edu.poly.shirtpolofinal.domain.Product;
import edu.poly.shirtpolofinal.repository.CartRepository;
import edu.poly.shirtpolofinal.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart createCart(Product product, String size) {
        System.out.println(product);
        Cart cart=new Cart();
        cart.setProduct(product);
        cart.setSize(size);
        List<Cart> carts=cartRepository.findAll();
        Cart cartSave = new Cart();
       if(!carts.isEmpty()){
           for(Cart item:carts){
               if(item.getSize().equals(size) && item.getProduct().getProductId()== product.getProductId()){
                   item.setQuantity(item.getQuantity()+1);
                   cartSave=item;
               }else{
                   cart.setQuantity(1);
                   cartSave=cartRepository.save(cart);
               }
           }
       }else{
           cart.setQuantity(1);
           cartSave=cartRepository.save(cart);
       }
        return cartSave;
    }

    @Override
    public Cart createCartAndQuantity(Product product, String size, int quantity) {
        List<Cart> carts=cartRepository.findAll();
        Cart cart=new Cart();
        cart.setProduct(product);
        cart.setSize(size);
        if(!carts.isEmpty()){
            for(Cart item:carts){
                if(item.getSize().equals(size) && item.getProduct().getProductId()== product.getProductId()){
                    item.setQuantity(item.getQuantity()+quantity);
                    cartRepository.save(item);
                    return item;
                }
                cart.setQuantity(quantity);
                Cart cartSave=cartRepository.save(cart);
                return cartSave;
            }
        }
        cart.setQuantity(quantity);
        Cart cartSave=cartRepository.save(cart);
        return cartSave;
    }
}
