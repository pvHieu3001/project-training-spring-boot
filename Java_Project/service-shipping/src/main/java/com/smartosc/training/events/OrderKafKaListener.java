package com.smartosc.training.events;

import com.smartosc.training.entities.OrderDB;
import com.smartosc.training.entities.Shipping;
import com.smartosc.training.services.ShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class OrderKafKaListener {
    private final Logger log = LoggerFactory.getLogger(OrderKafKaListener.class);

    private ShippingService shippingService;
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
