package com.smartosc.training.repositories;

import com.smartosc.training.domain.shipping.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping,Long> {
}
