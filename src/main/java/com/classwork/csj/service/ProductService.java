package com.classwork.csj.service;

import com.classwork.csj.entity.Product;
import com.classwork.csj.repository.ProductRepository;
import com.classwork.csj.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductUtil productUtil;

    public Product create(Product product){
       return productRepository.save(product);
    }

    public List<Product> fetchAll(){
        return productRepository.fetchAll();
    }

    public Integer getCount(String productName){
        Integer count = productRepository.fetchProductCountByName(productName);
        count = count == null? 0: count;
        return count;
    }

    public boolean deleteAll() {
        productRepository.deleteAll();
        return true;
    }

    public void generateSampleProducts(int number) {
        productUtil.generateSampleProducts(number);
    }
}
