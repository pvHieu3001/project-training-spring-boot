package com.smartosc.training.controllers;

import com.smartosc.training.domain.order.OrderDB;
import com.smartosc.training.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/find-order")
    @PreAuthorize("hasRole('read_profile')")
    public String getAll(){
        return "may khong thoat dc dau con trai. tu bi con ti niu!";
    }

    @GetMapping("/edit-order")
    @PreAuthorize("hasRole('update_profile')")
    public String editorder(){
        return "edit  thanh cong!";
    }

    @GetMapping("/delete-order")
    @PreAuthorize("hasRole('delete_profile')")
    public String deleteorder(){
        return "delete  thanh cong!";
    }
}
