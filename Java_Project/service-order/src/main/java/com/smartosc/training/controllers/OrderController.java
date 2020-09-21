package com.smartosc.training.controllers;

import com.smartosc.training.dto.OrderDto;
import com.smartosc.training.entities.OrderDB;
import com.smartosc.training.services.BaseService;
import com.smartosc.training.services.impls.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController extends BaseController<OrderDB, OrderDto, Long>{
    private OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService){
        this.orderService = orderService;
    }

    @Override
    public BaseService<OrderDB, OrderDto, Long> getService() {
        return orderService;
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
