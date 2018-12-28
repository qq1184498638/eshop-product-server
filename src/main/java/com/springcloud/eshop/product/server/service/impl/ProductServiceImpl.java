package com.springcloud.eshop.product.server.service.impl;

import com.springcloud.eshop.common.server.support.utils.ServerResponse;
import com.springcloud.eshop.product.server.config.rabbitmq.RabbitConstants;
import com.springcloud.eshop.product.server.entity.Product;
import com.springcloud.eshop.product.server.repository.ProductRepository;
import com.springcloud.eshop.product.server.repository.mapper.ProductMapper;
import com.springcloud.eshop.product.server.service.ProductService;
import com.springcloud.eshop.product.server.service.impl.mq.sender.RabbitSender;
import com.springcloud.eshop.product.server.support.base.XbootBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.management.counter.Units;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RabbitSender rabbitSender;

    @Override
    public XbootBaseRepository<Product, String> getRepository() {
        return productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productMapper.findAllProducts();
    }

    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"add\", \"data_type\": \"product\", \"id\": \"" + product.getId() + "\"}")
        );
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"update\", \"data_type\": \"product\", \"id\": \"" + product.getId() + "\"}")
        );
    }

    @Override
    public void deleteProduct(String id) {
        productMapper.deleteProduct(id);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"del\", \"data_type\": \"product\", \"id\": \"" + id + "\"}")
        );
    }

    @Override
    public Product findByProductId(String id) {
        return productMapper.findByProductId(id);
    }


}
