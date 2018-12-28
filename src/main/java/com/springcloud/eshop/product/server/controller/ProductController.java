package com.springcloud.eshop.product.server.controller;

import com.springcloud.eshop.common.server.support.utils.ServerResponse;
import com.springcloud.eshop.product.server.entity.Product;
import com.springcloud.eshop.product.server.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description ="商品相关的接口")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping("/add")
    @ApiOperation("添加品牌的接口")
    public ServerResponse addProduct(@ApiParam("品牌的参数") @ModelAttribute Product product) {
        productService.addProduct(product);
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    @DeleteMapping("/del")
    @ApiOperation("删除品牌的接口")
    public ServerResponse delProduct(@ApiParam("品牌的id") @RequestParam("id") String id) {
        productService.deleteProduct(id);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @PutMapping("/update")
    @ApiOperation("更新品牌的接口")
    public ServerResponse updateProduct(@ModelAttribute Product product) {
        productService.updateProduct(product);
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    @GetMapping("/findById")
    @ApiOperation("根据品牌id查询的接口")
    public ServerResponse findByProductId(@ApiParam("品牌的id") String id) {
        Product product = productService.findByProductId(id);
        if (product != null) {
            return ServerResponse.createBySuccess(product);
        }
        return ServerResponse.createByErrorMessage("为查询到该品牌信息");
    }

    @GetMapping("/findAll")
    @ApiOperation("查询所有的品牌的接口")
    public ServerResponse findAll() {
        List<Product> lists = productService.findAllProducts();
        if (CollectionUtils.isEmpty(lists)) {
            return ServerResponse.createByErrorMessage("目前商品列表为空");
        }
        return ServerResponse.createBySuccess(lists);
    }
}
