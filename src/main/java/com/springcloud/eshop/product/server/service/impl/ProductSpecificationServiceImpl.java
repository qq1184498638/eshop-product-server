package com.springcloud.eshop.product.server.service.impl;

import com.springcloud.eshop.common.server.support.utils.ServerResponse;
import com.springcloud.eshop.product.server.config.rabbitmq.RabbitConstants;
import com.springcloud.eshop.product.server.entity.ProductSpecification;
import com.springcloud.eshop.product.server.repository.ProductSpecificationRepository;
import com.springcloud.eshop.product.server.service.ProductSpecificationService;
import com.springcloud.eshop.product.server.service.impl.mq.sender.RabbitSender;
import com.springcloud.eshop.product.server.support.base.XbootBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductSpecificationServiceImpl implements ProductSpecificationService {
    @Autowired
    private ProductSpecificationRepository productSpecificationRepository;
    @Autowired
    private RabbitSender rabbitSender;

    @Override
    public XbootBaseRepository<ProductSpecification, String> getRepository() {
        return productSpecificationRepository;
    }


    @Override
    public ProductSpecification save(ProductSpecification entity) {
        ProductSpecification productSpecification = productSpecificationRepository.save(entity);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"add\", \"data_type\": \"productSpecification\", \"id\": \"" + entity.getId() + "\", \"product_id\": \"" + entity.getProductId() +  "\"}")
        );
        return productSpecification;
    }

    @Override
    public ProductSpecification update(ProductSpecification entity) {
        ProductSpecification productSpecification = productSpecificationRepository.saveAndFlush(entity);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"update\", \"data_type\": \"productSpecification\", \"id\": \"" + entity.getId() + "\", \"product_id\": \"" + entity.getProductId() +  "\"}")
        );
        return productSpecification;
    }

    @Override
    public void delete(String id) {
        ProductSpecification productSpecification = get(id);
        productSpecificationRepository.deleteById(id);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"del\", \"data_type\": \"productSpecification\", \"id\": \"" + id + "\", \"product_id\": \"" + productSpecification.getProductId() +  "\"}")
        );

    }

    @Override
    public ProductSpecification findByProductId(String productId) {
        return productSpecificationRepository.findByProductId(productId);
    }
}
