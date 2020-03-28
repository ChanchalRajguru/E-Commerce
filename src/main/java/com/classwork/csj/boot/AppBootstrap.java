package com.classwork.csj.boot;

import com.classwork.csj.entity.Product;
import com.classwork.csj.repository.ProductRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initialize();
    }

    private void initialize() {
        long count = productRepository.count();
        if (count <20 ){
            for(int i=0; i<55; i++){
                Product product = new Product();
                String name = generateName();
                product.setName(name);
                product.setAmount(Double.valueOf((i+1) * 100));
                product.setDescription(name);
                product.setQuantity(i+11);
                product.setMinimumQuantity(i+7);

                productRepository.save(product);
            }

        }

    }

    private String generateName(){
        return RandomStringUtils.randomAlphabetic(4);
    }



}
