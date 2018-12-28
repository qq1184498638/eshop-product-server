package com.springcloud.eshop.product.server.service.impl;

import com.springcloud.eshop.common.server.support.utils.ServerResponse;
import com.springcloud.eshop.product.server.config.rabbitmq.RabbitConstants;
import com.springcloud.eshop.product.server.entity.Brand;
import com.springcloud.eshop.product.server.repository.BrandRepository;
import com.springcloud.eshop.product.server.service.BrandService;
import com.springcloud.eshop.product.server.service.impl.mq.sender.RabbitSender;
import com.springcloud.eshop.product.server.support.base.XbootBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private RabbitSender rabbitSender;

    @Override
    public XbootBaseRepository<Brand, String> getRepository() {
        return brandRepository;
    }


    @Override
    public Brand save(Brand entity) {
        Brand brand = brandRepository.save(entity);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"add\", \"data_type\": \"brand\", \"id\": \"" +  entity.getId() + "\"}")
        );
        return brand;
    }

    @Override
    public Brand update(Brand entity) {
        Brand brand = brandRepository.saveAndFlush(entity);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"update\", \"data_type\": \"brand\", \"id\": \""+ entity.getId() + "\"}")
        );
        return brand;
    }

    @Override
    public void delete(String id) {
        brandRepository.deleteById(id);
        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DATA_SYNC_QUEUE,
                RabbitConstants.MQ_ROUTING_KEY_DATA_SYNC,
                ServerResponse.createBySuccess("{\"event_type\": \"del\", \"data_type\": \"brand\", \"id\": \"" + id + "\"}")
        );
    }


    @Override
    public List<Brand> findByIds(String[] ids) {
        return brandRepository.findAllById(Arrays.asList(ids));
    }
}
