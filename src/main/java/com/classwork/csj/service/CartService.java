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
        Optional<Cart> cart = cartRepository.findByUserId(Long.valueOf(userId));
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

    public void updateCartItem(int quantity, int itemId, int userId) {
        Optional<CartItem> cartItem = cartItemRepository.findById(Long.valueOf(itemId));

        if (cartItem.get().getId() != null && cartItem.get().getCart().getUser().getId() != userId) {
            cartItem.get().setQuantity(quantity);
            cartItemRepository.save(cartItem.get());
        } else {
            throw new Error();
        }
    }

    public Cart getCart(int userId) {
        Optional<Cart> cart = cartRepository.findByUserId(Long.valueOf(userId));

        return cart.get();
    }

    public void deleteCartItem(int itemId, int userId) {
        Optional<CartItem> item = cartItemRepository.findById(Long.valueOf(itemId));

        if (item.get().getCart().getUser().getId() == userId) {
            item.get().getCart().removeItem(item.get());
            cartItemRepository.deleteById(Long.valueOf(itemId));
        } else {
            throw new Error();
        }
    }

    public void emptyCart(int userId) {
        Optional<Cart> cart = cartRepository.findByUserId(Long.valueOf(userId));

        if (cart.get().getId() != null) {
            cartRepository.deleteById(Long.valueOf(cart.get().getId()));
            cartItemRepository.deleteByCartId(cart.get().getId());
        } else {
            throw new Error();
        }
    }
}
