package com.smartosc.training.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Slf4j
public class MyThread implements Runnable{
    @Autowired
    private OrderServiceImpl orderService;
    @Override
    public void run() {
        orderService.printOrder();
        log.info("Called from thread");
    }
}
