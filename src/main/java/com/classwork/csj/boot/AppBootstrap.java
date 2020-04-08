package com.classwork.csj.boot;

import com.classwork.csj.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProductUtil productUtil;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initialize();
    }

    /**
     * Populate database with sample products on start up
     * Another approach could have been to use the data.sql file in the resource folder of the project
     */
    private void initialize() {
        productUtil.generateSampleProducts(55);
    }
}
