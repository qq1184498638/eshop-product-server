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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class CategoryControllerTest extends ProductServerApplicationTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void save() throws Exception {
        String result = mockMvc.perform(
                post("/category/save")
                        .param("name", "手机")
                        .param("description", "手机")
                        .param("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
        ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info("返回的结果: " + result);
    }
}