package com.springcloud.eshop.product.server.config.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yuhao.wang
 */
@Component
public class SystemConfig {

    @Value("${mq.retry.count:3}")
    private int mqRetryCount;
    
    public int getMqRetryCount() {
        return mqRetryCount;
    }

    public void setMqRetryCount(int mqRetryCount) {
        this.mqRetryCount = mqRetryCount;
    }
}
