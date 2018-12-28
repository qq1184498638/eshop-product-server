package com.springcloud.eshop.product.server.controller;

import com.springcloud.eshop.product.server.ProductServerApplicationTests;
import com.springcloud.eshop.product.server.entity.Brand;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class BrandControllerTest extends ProductServerApplicationTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void addBrand() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = mockMvc.perform(
                post("/brand/add")
                        .param("name", "apples iphone")
                        .param("description", "苹果手机")
                        .param("createTime",  sdf.format(new Date()))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info("返回的结果: " + result);
    }

    @Test
    public void delBrand() {
    }

    @Test
    public void updateBrand() {
    }

    @Test
    public void findByProductId() {
    }

    @Test
    public void findAll() {
    }
}