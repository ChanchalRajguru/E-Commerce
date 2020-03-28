package com.classwork.csj.controller;

import com.classwork.csj.entity.Cart;
import com.classwork.csj.entity.Product;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CartControllerTest extends AbstractTest {
    private final static Logger logger = LoggerFactory.getLogger(CartControllerTest.class);

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void addToCart() throws Exception {
        int quantity = 5;
        String uri = "/cart/add/" + quantity;
        Product product = new Product();

        product.setId(Long.valueOf(1));
        product.setName("Scissors");
        product.setAmount(1500.00);
        product.setQuantity(25);
        product.setMinimumQuantity(1);
        product.setDescription("Great device for cutting paper");
        product.setCreate_date(LocalDateTime.now());

        String inputJson = super.mapToJson(product);
        logger.info("INPUT JSON: " + inputJson);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .header("user-id", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        logger.info("Item added to cart: " + content);
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }

    @Test
    public void getCart() throws Exception {
        String uri = "/cart/list";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .header("user-id", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Cart cart = super.mapFromJson(content, Cart.class);
        logger.info("Cart: " + content);
    }
}
