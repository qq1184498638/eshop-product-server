package com.springcloud.eshop.product.server.controller;

import com.springcloud.eshop.common.server.support.utils.ServerResponse;
import com.springcloud.eshop.product.server.config.rabbitmq.RabbitConstants;
import com.springcloud.eshop.product.server.service.impl.mq.sender.RabbitSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuhao.wang
 */
@RestController
@Api(value = "MQ测试接口", tags = "RabbitmqController")
public class RabbitmqController {

    @Autowired
    private RabbitSender rabbitSender;

    @ApiOperation(value = "发送MQ消息接口", notes = "用户名", response = Object.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名称", required = false, dataType = "String", paramType = "query")})
    @PostMapping(value = "sendMsg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object sendMsg(String name) {
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_SEND_AWARD, RabbitConstants.MQ_ROUTING_KEY_SEND_COUPON,
                ServerResponse.createBySuccess("{\"event_type\": \"add\", \"data_type\": \"brand\", \"id\": \"" + 1 + "\"}"));
        return "发送成";
    }

}