package com.smartosc.training.controllers;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@FeignClient("service-order")
interface OrderClient {
    @GetMapping("/find-all")
    @CrossOrigin
    String findAll();
}

@RestController
public class OrderController {
    private final OrderClient orderClient;

    public OrderController(OrderClient orderClient){
        this.orderClient = orderClient;
    }

    @GetMapping("/find-order")
    @CrossOrigin
    public String findOrder() {
        return orderClient.findAll();
    }
}
