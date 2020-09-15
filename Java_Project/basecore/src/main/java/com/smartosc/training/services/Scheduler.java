package com.smartosc.training.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private OrderServiceImpl orderService;

    public Scheduler(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedDelay = 10000)
    public void checkTheScedule() {
        orderService.findAll();
    }

}
