package com.springcloud.eshop.product.server.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.springcloud.eshop.product.server.support.base.XbootBaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_product_introduce")
@TableName("t_product_introduce")
public class ProductIntroduce extends XbootBaseEntity{

    private static final long serialVersionUID = 3399985126997873506L;

    @ApiModelProperty("商品介绍的名称")
    private String name;

    @ApiModelProperty("商品介绍内容")
    private String value;

    @ApiModelProperty("商品介绍对应的商品id")
    private String productId;
}
