package com.smartosc.training.events;

import com.smartosc.training.domain.order.OrderDB;
import com.smartosc.training.domain.shipping.Shipping;
import com.smartosc.training.services.ShippingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderKafKaListener {
    private final ShippingService shippingService;
    @Autowired
    public OrderKafKaListener(ShippingService shippingService) {
        super();
        this.shippingService = shippingService;
    }

    @KafkaListener(topics = "order")
    public void order(OrderDB order, Acknowledgment acknowledgment) {
        log.info("Received order " + order.getId());
        Shipping shipping = new Shipping();
        shipping.setCode("testcode");
        shippingService.createShipment(shipping);
        acknowledgment.acknowledge();
    }
}
