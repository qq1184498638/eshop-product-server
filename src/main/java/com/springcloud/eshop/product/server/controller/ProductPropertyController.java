package com.springcloud.eshop.product.server.controller;

import com.springcloud.eshop.common.server.support.utils.ServerResponse;
import com.springcloud.eshop.product.server.entity.ProductProperty;
import com.springcloud.eshop.product.server.service.ProductPropertyService;
import com.springcloud.eshop.product.server.support.base.XbootBaseController;
import com.springcloud.eshop.product.server.support.base.XbootBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "商品属性相关的接口")
@RequestMapping("/product-property")
public class ProductPropertyController extends XbootBaseController<ProductProperty, String>{
    @Autowired
    private ProductPropertyService productPropertyService;

    @Override
    public XbootBaseService<ProductProperty, String> getService() {
        return productPropertyService;
    }

    @GetMapping("/findByProductId")
    @ApiOperation("根据商品id 查询对应的商品属性")
    public ServerResponse findByProductId(@RequestParam("productId") String productId) {
        ProductProperty productProperty = productPropertyService.findByProductId(productId);
        return ServerResponse.createBySuccess(productProperty);
    }

}
