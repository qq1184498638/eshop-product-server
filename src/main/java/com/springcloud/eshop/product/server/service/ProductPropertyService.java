package com.springcloud.eshop.product.server.service;

import com.springcloud.eshop.product.server.entity.ProductProperty;
import com.springcloud.eshop.product.server.support.base.XbootBaseService;

public interface ProductPropertyService extends XbootBaseService<ProductProperty, String> {
    ProductProperty findByProductId(String productId);
}
