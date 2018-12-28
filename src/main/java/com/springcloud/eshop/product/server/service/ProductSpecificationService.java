package com.springcloud.eshop.product.server.service;

import com.springcloud.eshop.product.server.entity.ProductSpecification;
import com.springcloud.eshop.product.server.support.base.XbootBaseService;

public interface ProductSpecificationService extends XbootBaseService<ProductSpecification, String> {
    ProductSpecification findByProductId(String productId);
}
