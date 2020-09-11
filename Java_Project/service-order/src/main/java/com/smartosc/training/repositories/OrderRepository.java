package com.smartosc.training.repositories;

import com.smartosc.training.domain.order.OrderDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDB,Long> {
}
