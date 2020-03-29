package com.classwork.csj.util;

import com.classwork.csj.entity.Product;
import com.classwork.csj.repository.ProductRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductUtil {


    @Autowired
    private ProductRepository productRepository;

    public void generateSampleProducts(int number) {
        long count = productRepository.count();
        if (count < 20) {
            for (int i = 0; i < number; i++) {
                Product product = new Product();
                String name = generateName();
                product.setName(name);
                product.setAmount(Double.valueOf((i + 1) * 100));
                product.setDescription(name);
                product.setQuantity(i + 11);
                product.setMinimumQuantity(i + 5);

                productRepository.save(product);
            }
        }
    }

    private String generateName(){
        return RandomStringUtils.randomAlphabetic(4);
    }
}
