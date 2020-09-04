package com.smartosc.training.repositories;

import com.smartosc.training.entities.OrderDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDB,Long> {
}
