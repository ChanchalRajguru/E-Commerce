package com.classwork.csj.service;

import com.classwork.csj.entity.Cart;
import com.classwork.csj.entity.CartItem;
import com.classwork.csj.entity.Product;
import com.classwork.csj.entity.User;
import com.classwork.csj.repository.CartItemRepository;
import com.classwork.csj.repository.CartRepository;
import com.classwork.csj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    CartItemRepository cartItemRepository;
    UserRepository userRepository;

    public void createCart(CartItem item, int userId) {
        Optional<User> user = userRepository.findById(Long.valueOf(userId));

        if (user.isPresent()) {
            Cart newCart = new Cart();
            newCart.setExpired(false);
            newCart.setUser(user.get());
            newCart.addItem(item);

            cartRepository.save(newCart);
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }

    public void addCartItem(Product product, int quantity, int userId) {
        Optional<Cart> cart = cartRepository.findByUserId(Long.valueOf(userId));
        CartItem newCartItem = new CartItem();
        newCartItem.setQuantity(quantity);
        newCartItem.setProduct(product);

        if (cart.isPresent()) {
            newCartItem.setCart(cart.get());
            cartItemRepository.save(newCartItem);
        } else {
            createCart(newCartItem, userId);
        }
    }

    public void updateCartItem(int quantity, int itemId, int userId) {
        Optional<CartItem> cartItem = cartItemRepository.findById(Long.valueOf(itemId));

        if (cartItem.isPresent() && cartItem.get().getCart().getUser().getId() != userId) {
            cartItem.get().setQuantity(quantity);
            cartItemRepository.save(cartItem.get());
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }

    public Cart getCart(int userId) {
        Optional<Cart> cart = cartRepository.findByUserId(Long.valueOf(userId));

        if (cart.isPresent()) {
            return cart.get();
        } else {
            return new Cart();
        }
    }

    public void deleteCartItem(int itemId, int userId) {
        Optional<CartItem> item = cartItemRepository.findById(Long.valueOf(itemId));

        if (item.isPresent() && item.get().getCart().getUser().getId() == userId) {
            item.get().getCart().removeItem(item.get());
            cartItemRepository.deleteById(Long.valueOf(itemId));
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }

    public void emptyCart(int userId) {
        Optional<Cart> cart = cartRepository.findByUserId(Long.valueOf(userId));

        if (cart.isPresent()) {
            cartRepository.deleteById(Long.valueOf(cart.get().getId()));
            cartItemRepository.deleteByCartId(cart.get().getId());
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }
}
