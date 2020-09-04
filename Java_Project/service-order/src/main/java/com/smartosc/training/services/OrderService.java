package com.smartosc.training.services;

import com.smartosc.training.entities.OrderDB;
import com.smartosc.training.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private OrderService(OrderRepository orderRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        super();
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public OrderDB order(OrderDB order) {
        OrderDB result = orderRepository.save(order);
        fireOrderCreatedEvent(order);
        return result;
    }

    private void fireOrderCreatedEvent(OrderDB order) {
        kafkaTemplate.send("order", order.getId() + "created", order);
    }
}
