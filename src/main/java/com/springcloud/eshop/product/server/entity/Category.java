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
@Table(name = "t_category")
@TableName("t_category")
public class Category extends XbootBaseEntity{

    private static final long serialVersionUID = 1641439540940889452L;

    @ApiModelProperty("类别名称")
    @Column(nullable = false)
    private String name;

    @ApiModelProperty("类别的描述")
    private String description;
}
