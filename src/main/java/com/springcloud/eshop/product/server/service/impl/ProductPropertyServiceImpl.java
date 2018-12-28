package com.springcloud.eshop.product.server.service.impl;

import com.springcloud.eshop.common.server.support.utils.ServerResponse;
import com.springcloud.eshop.product.server.config.rabbitmq.RabbitConstants;
import com.springcloud.eshop.product.server.entity.ProductProperty;
import com.springcloud.eshop.product.server.repository.ProductPropertyRepository;
import com.springcloud.eshop.product.server.service.ProductPropertyService;
import com.springcloud.eshop.product.server.service.impl.mq.sender.RabbitSender;
import com.springcloud.eshop.product.server.support.base.XbootBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductPropertyServiceImpl implements ProductPropertyService {
    @Autowired
    private ProductPropertyRepository productPropertyRepository;

    @Autowired
    private RabbitSender rabbitSender;


    @Override
    public XbootBaseRepository<ProductProperty, String> getRepository() {
        return productPropertyRepository;
    }

    @Override
    public ProductProperty save(ProductProperty entity) {
        ProductProperty productProperty = productPropertyRepository.save(entity);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"add\", \"data_type\": \"productProperty\", \"id\": \"" + entity.getId() + "\", \"product_id\": \"" + entity.getProductId() +  "\"}")
        );
        return productProperty;
    }

    @Override
    public ProductProperty update(ProductProperty entity) {
        ProductProperty productProperty = productPropertyRepository.saveAndFlush(entity);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"update\", \"data_type\": \"productProperty\", \"id\": \"" + entity.getId() + "\", \"product_id\": \"" + entity.getProductId() +  "\"}")
        );
        return productProperty;
    }

    @Override
    public void delete(String s) {
        ProductProperty productProperty = get(s);
        productPropertyRepository.deleteById(s);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"del\", \"data_type\": \"brand\", \"id\": \"" + s + "\", \"product_id\": \"" + productProperty.getProductId() + "\"}")
        );
    }

    @Override
    public ProductProperty findByProductId(String productId) {
        return productPropertyRepository.findByProductId(productId);
    }
}
