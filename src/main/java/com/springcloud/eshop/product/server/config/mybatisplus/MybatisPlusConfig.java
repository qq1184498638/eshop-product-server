package com.springcloud.eshop.product.server.config.mybatisplus;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.springcloud.eshop.product.server.*.mapper")
public class MybatisPlusConfig {
    /**
     * 分页插件, 自动识别数据库类型
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
