package com.classwork.csj.controller;


import com.classwork.csj.entity.Product;
import com.classwork.csj.exceptions.BadRequestException;
import com.classwork.csj.exceptions.ItemNotFoundException;
import com.classwork.csj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/")
    public ResponseEntity createProduct(@Valid @RequestBody Product product){
        productService.create(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam(value = "name", required = false, defaultValue = "") String name){
        List<Product> foundProduct = productService.findByNameLike(name);

        if(foundProduct == null){
            throw new ItemNotFoundException("Could not find product with name "+ name);
        }
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){
        Product foundProduct = productService.find(id);

        if(foundProduct == null){
            throw new ItemNotFoundException("Could not find product with id "+ id);
        }

        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> fetchProducts(){
        return new ResponseEntity<List<Product>>(productService.fetchAll(), HttpStatus.OK);
    }

    @GetMapping("/all/paginate")
    public Page<Product> getProductsByPage(Pageable pageable){
       Page<Product> productPage =  productService.findAll(pageable);
       return productPage;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getProductCount(){
        return new ResponseEntity<Long>(productService.getCount(), HttpStatus.OK);
    }

    @GetMapping("/count/{productName}")
    public ResponseEntity<Long> getProductCount(@PathVariable String productName){
        return new ResponseEntity<Long>(productService.getCount(productName), HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Boolean> deleteAll(){
        return new ResponseEntity<>(productService.deleteAll(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/clear/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id){
        try{
            productService.delete(id);
        }catch (EmptyResultDataAccessException ex){
            throw  new  ItemNotFoundException("No product found with id "+ id);
        }

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @GetMapping("/generate/{number}")
    public ResponseEntity<List<Product>> generateProducts(@PathVariable int number){
        productService.generateSampleProducts(number);
        return new ResponseEntity<List<Product>>(productService.fetchAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("id") Long id){
        if(product.getId() != id){
            throw new BadRequestException("Update not allowed due to Id mismatch");
        }

        return new ResponseEntity<Product>(productService.update(product), HttpStatus.OK);
    }

}
