package com.springcloud.eshop.product.server.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.springcloud.eshop.product.server.support.base.XbootBaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_product_property")
@TableName("t_product_property")
public class ProductProperty extends XbootBaseEntity{

    private static final long serialVersionUID = 2251132463482764002L;

    @ApiModelProperty("商品属性名称")
    @Column(nullable = false)
    private String name;

    @ApiModelProperty("商品属性值")
    private String value;

    @ApiModelProperty("商品属性对应的商品id")
    private String productId;
}
