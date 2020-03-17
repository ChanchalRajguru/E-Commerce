package com.classwork.csj.service;

import com.classwork.csj.entity.Cart;
import com.classwork.csj.entity.CartItem;
import com.classwork.csj.entity.Product;
import com.classwork.csj.repository.CartItemRepository;
import com.classwork.csj.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    CartItemRepository cartItemRepository;

    public Cart createCart(CartItem item) {
        Cart newCart = new Cart();
        newCart.setExpired(false);
        newCart.addItem(item);

        return cartRepository.save(newCart);
    }

    public Cart addCartItem(Product product, int quantity, int userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        CartItem newItem = new CartItem();
        newItem.setQuantity(quantity);
        newItem.setProduct(product);

        if (cart.isPresent()) {
            newItem.setCart(cart.get());

            cartItemRepository.save(newItem);
            return cart.get();
        } else {
            return createCart(newItem);
        }
    }
}
