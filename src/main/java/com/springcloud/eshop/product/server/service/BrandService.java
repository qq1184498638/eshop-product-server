package com.springcloud.eshop.product.server.service;

import com.springcloud.eshop.product.server.entity.Brand;
import com.springcloud.eshop.product.server.support.base.XbootBaseService;

import java.util.List;

public interface BrandService extends XbootBaseService<Brand, String> {
    List<Brand> findByIds(String[] ids);
}
