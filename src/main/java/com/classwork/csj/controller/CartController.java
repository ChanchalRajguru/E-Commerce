package com.classwork.csj.controller;

import com.classwork.csj.entity.Cart;
import com.classwork.csj.entity.Product;
import com.classwork.csj.exceptions.ItemNotFoundException;
import com.classwork.csj.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("{userId}/cart")
    public Cart getCart(@PathVariable int userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("{userId}/add/{quantity}")
    public ResponseEntity<Cart> addToCart(@Valid @RequestBody Product product, @PathVariable int quantity, @PathVariable int userId) {
        return new ResponseEntity<Cart>(cartService.addCartItem(product, quantity, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("{userId}/cart/{itemId}")
    public void removeCartItem(@PathVariable int itemId, @PathVariable int userId) {
        try {
            cartService.deleteCartItem(itemId, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Unable to remove cart item with id: " + itemId);
        }
    }

    @DeleteMapping("{userId}/cart/{cartId}")
    public void emptyCart(@PathVariable int cartId, @PathVariable int userId) {
        try {
            cartService.emptyCart(cartId, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Unable to empty cart");
        }
    }
}
