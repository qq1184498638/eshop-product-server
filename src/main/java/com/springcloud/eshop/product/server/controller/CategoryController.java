package com.springcloud.eshop.product.server.controller;

import com.springcloud.eshop.product.server.entity.Category;
import com.springcloud.eshop.product.server.service.CategoryService;
import com.springcloud.eshop.product.server.support.base.XbootBaseController;
import com.springcloud.eshop.product.server.support.base.XbootBaseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "商品类别相关的接口")
@RequestMapping("/category")
public class CategoryController extends XbootBaseController<Category, String>{
    @Autowired
    private CategoryService categoryService;


    @Override
    public XbootBaseService<Category, String> getService() {
        return categoryService;
    }


}
