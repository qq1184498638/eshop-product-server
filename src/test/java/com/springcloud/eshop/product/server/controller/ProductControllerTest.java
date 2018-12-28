package com.springcloud.eshop.product.server.controller;

import com.springcloud.eshop.product.server.ProductServerApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class ProductControllerTest extends ProductServerApplicationTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void addProduct() throws Exception {
        String result = mockMvc.perform(
                post("/product/add")
                        .param("name","apple iphone 8")
                        .param("description", "苹果 iphone 8 手机")
                        .param("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                        .param("categoryId", "89705135231471616")
                        .param("brandId", "89702294634893312")
        ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info("返回的结果: " + result);
    }

    @Test
    public void delProduct() {
    }

    @Test
    public void updateProduct() {
    }

    @Test
    public void findByProductId() throws Exception {
        String result = mockMvc.perform(
                get("/product/findById")
                        .param("id", "89717875815747584")
        ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info("返回的商品信息: " + result);
    }

    @Test
    public void findAll() {
    }
}