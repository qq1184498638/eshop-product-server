package com.springcloud.eshop.product.server.service.impl;

import com.springcloud.eshop.common.server.support.utils.ServerResponse;
import com.springcloud.eshop.product.server.config.rabbitmq.RabbitConstants;
import com.springcloud.eshop.product.server.entity.Category;
import com.springcloud.eshop.product.server.repository.CategoryRepository;
import com.springcloud.eshop.product.server.service.CategoryService;
import com.springcloud.eshop.product.server.service.impl.mq.sender.RabbitSender;
import com.springcloud.eshop.product.server.support.base.XbootBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RabbitSender rabbitSender;

    @Override
    public XbootBaseRepository<Category, String> getRepository() {
        return categoryRepository;
    }

    @Override
    public Category save(Category entity) {
        Category category = categoryRepository.save(entity);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"add\", \"data_type\": \"category\", \"id\": \"" + entity.getId() + "\" }")
        );
        return category;
    }

    @Override
    public Category update(Category entity) {
        Category category = categoryRepository.saveAndFlush(entity);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"update\", \"data_type\": \"category\", \"id\": \"" + entity.getId() + "\" }")
        );
        return category;
    }

    @Override
    public void delete(String id) {
        categoryRepository.deleteById(id);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"del\", \"data_type\": \"category\", \"id\": \"" + id + "\" }")
        );
    }
}
