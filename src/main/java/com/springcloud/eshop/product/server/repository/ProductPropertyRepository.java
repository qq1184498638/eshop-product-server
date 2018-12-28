package com.springcloud.eshop.product.server.repository;

import com.springcloud.eshop.product.server.entity.ProductProperty;
import com.springcloud.eshop.product.server.support.base.XbootBaseRepository;

public interface ProductPropertyRepository extends XbootBaseRepository<ProductProperty, String> {
    ProductProperty findByProductId(String productId);
}
