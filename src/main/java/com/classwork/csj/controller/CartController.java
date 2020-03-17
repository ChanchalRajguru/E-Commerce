package com.classwork.csj.controller;

import com.classwork.csj.entity.Cart;
import com.classwork.csj.entity.CartItem;
import com.classwork.csj.entity.Product;
import com.classwork.csj.exceptions.ItemNotFoundException;
import com.classwork.csj.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("list")
    public ResponseEntity<Cart> getCart(@RequestHeader("user-id") int userId) {
        return new ResponseEntity<Cart>(cartService.getCart(userId), HttpStatus.OK);
    }

    @PostMapping("add/{quantity}")
    public ResponseEntity addToCart(@Valid @RequestBody Product product, @PathVariable int quantity, @RequestHeader("user-id") int userId) {
        Cart cart = cartService.addCartItem(product, quantity, userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("update/{itemId}")
    public ResponseEntity updateCartItem(@Valid @RequestBody int quantity, @PathVariable int itemId, @RequestHeader("user-id") int userId) {
        try {
            cartService.updateCartItem(quantity, itemId, userId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Item not found in cart");
        }
    }

    @DeleteMapping("remove/{itemId}")
    public void removeCartItem(@PathVariable int itemId, @RequestHeader("user-id") int userId) {
        try {
            cartService.deleteCartItem(itemId, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Unable to remove cart item with id: " + itemId);
        }
    }

    @DeleteMapping("empty")
    public void emptyCart(@RequestHeader("user-id") int userId) {
        try {
            cartService.emptyCart(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Unable to empty cart");
        }
    }
}
