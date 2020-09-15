package com.smartosc.training.services.impls;

import com.smartosc.training.dto.OrderDto;
import com.smartosc.training.entities.OrderDB;
import com.smartosc.training.repositories.BaseRepository;
import com.smartosc.training.repositories.OrderRepository;
import com.smartosc.training.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public BaseRepository<OrderDB, Long> getDao() {
        return null;
    }

    @Override
    public OrderDB createDto(OrderDto dto) {
        return null;
    }

    @Override
    public void convertDtoToEntity(OrderDto dto, OrderDB entity) {

    }

    @Autowired
    private OrderServiceImpl(OrderRepository orderRepository, KafkaTemplate<String, Object> kafkaTemplate) {
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
