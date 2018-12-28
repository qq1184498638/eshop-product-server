package com.springcloud.eshop.product.server.controller;

import com.springcloud.eshop.common.server.support.utils.ServerResponse;
import com.springcloud.eshop.product.server.entity.ProductSpecification;
import com.springcloud.eshop.product.server.service.ProductSpecificationService;
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
@Api(description = "商品规格相关的接口")
@RequestMapping("/product-specification")
public class ProductSpecificationController extends XbootBaseController<ProductSpecification, String> {
    @Autowired
    private ProductSpecificationService productSpecificationService;


    @Override
    public XbootBaseService<ProductSpecification, String> getService() {
        return productSpecificationService;
    }

    @GetMapping("/findByProductId")
    @ApiOperation("根据商品id查询到对应的商品规格")
    public ServerResponse findByProductId(@RequestParam("productId") String productId) {
        return ServerResponse.createBySuccess(productSpecificationService.findByProductId(productId));
    }
}
