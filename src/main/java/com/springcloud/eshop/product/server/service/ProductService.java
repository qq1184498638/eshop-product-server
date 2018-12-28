package com.springcloud.eshop.product.server.service;

import com.springcloud.eshop.product.server.entity.Product;
import com.springcloud.eshop.product.server.support.base.XbootBaseService;

import java.util.List;

public interface ProductService extends XbootBaseService<Product, String> {
    List<Product> findAllProducts();
    void addProduct( Product product);

    void updateProduct(Product product);

    void deleteProduct(String id);

    Product findByProductId(String id);
}
