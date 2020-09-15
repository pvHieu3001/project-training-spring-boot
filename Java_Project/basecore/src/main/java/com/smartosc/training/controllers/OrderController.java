package com.smartosc.training.controllers;


import com.smartosc.training.domains.OrderDB;
import com.smartosc.training.repositories.OrderRepository;
import com.smartosc.training.services.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderService;


    @GetMapping("/runTask")
    public String executeAsync() {

        orderService.executeAsynchronously();

        return "OK";
    }

    @GetMapping("/employees")
    public List< OrderDB> employees() throws Exception {

        return orderService.fetchOrders().get();
    }

    @PostMapping(value = "/employee")
    public void add(@RequestBody OrderDB orderDB) {

        orderRepository.save(orderDB);
    }
}
