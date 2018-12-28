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
@Table(name = "t_product_specification")
@TableName("t_product_specification")
public class ProductSpecification extends XbootBaseEntity{
    private static final long serialVersionUID = 5329841424578183888L;

    @ApiModelProperty("商品规格的名称")
    @Column(nullable = false)
    private String name;

    @ApiModelProperty("商品规格值")
    private String value;

    @ApiModelProperty("商品规格对应的商品id")
    private String productId;
}
