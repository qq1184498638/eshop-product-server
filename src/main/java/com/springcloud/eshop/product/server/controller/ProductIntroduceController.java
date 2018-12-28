package com.springcloud.eshop.product.server.controller;

import com.springcloud.eshop.product.server.entity.ProductIntroduce;
import com.springcloud.eshop.product.server.service.ProductIntroduceService;
import com.springcloud.eshop.product.server.support.base.XbootBaseController;
import com.springcloud.eshop.product.server.support.base.XbootBaseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "商品介绍相关的接口")
@RequestMapping("/product-intro")
public class ProductIntroduceController extends XbootBaseController<ProductIntroduce, String> {
    @Autowired
    private ProductIntroduceService productIntroduceService;

    @Override
    public XbootBaseService<ProductIntroduce, String> getService() {
        return productIntroduceService;
    }


}
