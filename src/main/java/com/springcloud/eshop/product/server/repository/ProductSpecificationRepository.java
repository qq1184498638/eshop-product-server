package com.springcloud.eshop.product.server.repository;

import com.springcloud.eshop.product.server.entity.ProductSpecification;
import com.springcloud.eshop.product.server.support.base.XbootBaseRepository;

public interface ProductSpecificationRepository extends XbootBaseRepository<ProductSpecification, String> {
    ProductSpecification findByProductId(String productId);
}
