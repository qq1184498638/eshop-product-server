package com.springcloud.eshop.product.server.support.base;

import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springcloud.eshop.common.server.support.constant.CommonConstant;
import com.springcloud.eshop.common.server.support.utils.SnowFlakeUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class XbootBaseEntity implements Serializable{
    private static final long serialVersionUID = -7498185441760629026L;
    @Id
    @TableId
    @ApiModelProperty("唯一标识")
    private String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());

    @ApiModelProperty("创建者")
    @CreatedBy
    private String createBy;

    @ApiModelProperty("创建时间")
    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") // 出参
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 入参
    private Date createTime;

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @LastModifiedBy
    @ApiModelProperty("修改者")
    private String updateBy;

    @ApiModelProperty("删除标准, 默认是0 正常状态")
    private Integer delFlag = CommonConstant.STATUS_NORMAL;

}
