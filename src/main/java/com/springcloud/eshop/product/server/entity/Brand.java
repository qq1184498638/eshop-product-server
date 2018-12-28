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
@Table(name = "t_brand")
@TableName("t_brand")
public class Brand extends XbootBaseEntity{


    private static final long serialVersionUID = -5185889866129956785L;
    @ApiModelProperty("品牌的名称")
    @Column(nullable = false)
    private String name;

    @ApiModelProperty("品牌的描述")
    private String description;
}
