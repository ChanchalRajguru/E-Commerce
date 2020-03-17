package com.classwork.csj.controller;

import com.classwork.csj.entity.Cart;
import com.classwork.csj.entity.CartItem;
import com.classwork.csj.entity.Product;
import com.classwork.csj.exceptions.ItemNotFoundException;
import com.classwork.csj.service.CartService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
//@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("{userId}/cart")
    public ResponseEntity<Cart> getCart(@PathVariable int userId) {
        return new ResponseEntity<Cart>(cartService.getCart(userId), HttpStatus.OK);
    }

    @PostMapping("{userId}/cart/{quantity}")
    public ResponseEntity addToCart(@Valid @RequestBody Product product, @PathVariable int quantity, @PathVariable int userId) {
        Cart cart = cartService.addCartItem(product, quantity, userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("{userId}/cart")
    public ResponseEntity updateCart(@Valid @RequestBody CartItem cartItem, @PathVariable int userId) {
        try {
            cartService.updateCartItem(cartItem, userId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Item not found in cart");
        }
    }

    @DeleteMapping("{userId}/cart/{itemId}")
    public void removeCartItem(@PathVariable int itemId, @PathVariable int userId) {
        try {
            cartService.deleteCartItem(itemId, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Unable to remove cart item with id: " + itemId);
        }
    }

    @DeleteMapping("{userId}/cart")
    public void emptyCart(@PathVariable int userId) {
        try {
            cartService.emptyCart(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Unable to empty cart");
        }
    }
}
