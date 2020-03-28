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

    private void initialize() {
        productUtil.generateSampleProducts(55);
    }
}
