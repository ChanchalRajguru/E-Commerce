package com.classwork.csj.service;

import com.classwork.csj.entity.Product;
import com.classwork.csj.repository.ProductRepository;
import com.classwork.csj.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Long getCount(String productNameLike){
        Long count = productRepository.fetchProductCountByName(productNameLike);
        count = count == null? 0: count;
        return count;
    }

    public Long getCountWhereNameLike(String nameLike){
        return productRepository.countProductsByNameLike(nameLike);
    }
    public Long getCount(){
       return productRepository.count();
    }

    public boolean deleteAll() {
        productRepository.deleteAll();
        return true;
    }

    public void generateSampleProducts(int number) {
        productUtil.generateSampleProducts(number);
    }

    public Product find(Long id) {
        return productRepository.getOne(id);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findByIdOrName(Long id, String name) {
        return productRepository.findByIdOrName(id, name);
    }

    public List<Product> findByNameLike(String name) {
        return productRepository.findByNameIgnoreCaseLike(name);
    }
}
