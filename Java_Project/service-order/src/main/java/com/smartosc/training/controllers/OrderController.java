package com.smartosc.training.controllers;

import com.smartosc.training.entities.OrderDB;
import com.smartosc.training.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public OrderDB order(@RequestBody OrderDB order){
        return orderService.order(order);
    }

    @GetMapping("/find-all")
    public String getAll(){
        return "may khong thoat dc dau con trai. tu bi con ti niu!";
    }
}
