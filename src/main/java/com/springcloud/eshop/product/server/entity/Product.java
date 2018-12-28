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
@Table(name = "t_product")
@TableName("t_product")
public class Product extends XbootBaseEntity{

    private static final long serialVersionUID = 4131500575201533655L;
    @ApiModelProperty("商品的名称")
    @Column(nullable = false)
    private String name;

    @ApiModelProperty("商品的描述")
    private String description;

    @ApiModelProperty("商品关联的类别的id")
    @Column(nullable = false)
    private String categoryId;

    @ApiModelProperty("商品关联的品牌的id")
    @Column(nullable = false)
    private String brandId;
}
