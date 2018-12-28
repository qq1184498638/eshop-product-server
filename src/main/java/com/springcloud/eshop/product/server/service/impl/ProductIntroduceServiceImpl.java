package com.springcloud.eshop.product.server.service.impl;

import com.springcloud.eshop.common.server.support.utils.ServerResponse;
import com.springcloud.eshop.product.server.config.rabbitmq.RabbitConstants;
import com.springcloud.eshop.product.server.entity.ProductIntroduce;
import com.springcloud.eshop.product.server.repository.ProductIntroduceRepository;
import com.springcloud.eshop.product.server.service.ProductIntroduceService;
import com.springcloud.eshop.product.server.service.impl.mq.sender.RabbitSender;
import com.springcloud.eshop.product.server.support.base.XbootBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductIntroduceServiceImpl implements ProductIntroduceService {
    @Autowired
    private ProductIntroduceRepository productIntroduceRepository;

    @Autowired
    private RabbitSender rabbitSender;


    @Override
    public XbootBaseRepository<ProductIntroduce, String> getRepository() {
        return productIntroduceRepository;
    }

    @Override
    public ProductIntroduce save(ProductIntroduce entity) {
        ProductIntroduce productIntroduce = productIntroduceRepository.save(entity);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"add\", \"data_type\": \"productIntroduce\", \"id\": \"" + entity.getId() + "\", \"product_id\": \"" + entity.getProductId() +"\" }")
        );
        return productIntroduce;
    }

    @Override
    public ProductIntroduce update(ProductIntroduce entity) {
        ProductIntroduce productIntroduce = productIntroduceRepository.saveAndFlush(entity);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"update\", \"data_type\": \"productIntroduce\", \"id\": \"" + entity.getId() + "\", \"product_id\": \"" + entity.getProductId() +"\" }")
        );
        return productIntroduce;
    }

    @Override
    public void delete(String id) {
        ProductIntroduce productIntroduce = get(id);
        productIntroduceRepository.deleteById(id);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"del\", \"data_type\": \"productIntroduce\", \"id\": \"" + id + "\", \"product_id\": \"" + productIntroduce.getProductId() + "\" }")
        );
    }
}
