package com.classwork.csj.controller;

import com.classwork.csj.entity.Cart;
import com.classwork.csj.entity.Product;
import com.classwork.csj.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("add/{userId}/{quantity}")
    public ResponseEntity<Cart> addToCart(@RequestBody Product product, @PathVariable int quantity, @PathVariable int userId) {
        return new ResponseEntity<Cart>(cartService.addCartItem(product, quantity, userId), HttpStatus.CREATED);
    }
}
