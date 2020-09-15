package com.smartosc.training.services;

import com.smartosc.training.converter.ObjectMapperUtils;
import com.smartosc.training.domains.OrderDB;
import com.smartosc.training.dto.OrderDto;
import com.smartosc.training.repositories.BaseRepository;
import com.smartosc.training.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderServiceImpl implements OrderService {
    private ApplicationContext applicationContext;
    private OrderRepository orderRepository;
    private TaskExecutor taskExecutor;
    @Autowired
    public OrderServiceImpl(TaskExecutor taskExecutor, OrderRepository orderRepository, ApplicationContext applicationContext) {
        this.taskExecutor = taskExecutor;
        this.orderRepository = orderRepository;
        this.applicationContext = applicationContext;
    }


    @Override
    public BaseRepository<OrderDB, Long> getDao() {
        return orderRepository;
    }

    @Override
    public OrderDB createDto(OrderDto dto) {
        return ObjectMapperUtils.map(dto, OrderDB.class);
    }

    @Override
    public void convertDtoToEntity(OrderDto dto, OrderDB entity) {
        ObjectMapperUtils.map(dto, entity);
    }

    @Async
    @Transactional
    public void printOrder(){
        orderRepository.findAll().stream().forEach(e->{
            System.out.println(e.toString());
        });
    }

    @Async("specificTaskExecutor")
    @Transactional
    public CompletableFuture<List<OrderDB>> fetchOrders(){
        return CompletableFuture.completedFuture(orderRepository.findAll());
    }

    public void executeAsynchronously() {

        MyThread myThread = applicationContext.getBean(MyThread.class);
        taskExecutor.execute(myThread);
    }

}
