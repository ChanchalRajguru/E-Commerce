package com.classwork.csj.service;

import com.classwork.csj.entity.Product;
import com.classwork.csj.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product create(Product product){
       return productRepository.save(product);
    }

    public List<Product> fetchAll(){
        return productRepository.findAll();
    }
}
