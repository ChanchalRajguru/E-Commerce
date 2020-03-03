package com.classwork.csj.controller;


import com.classwork.csj.entity.Product;
import com.classwork.csj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
    }

    @GetMapping("get-all")
    public ResponseEntity<List<Product>> fetchProducts(){
        return new ResponseEntity<List<Product>>(productService.fetchAll(), HttpStatus.OK);
    }
}
