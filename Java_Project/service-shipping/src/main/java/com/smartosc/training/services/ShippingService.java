package com.smartosc.training.services;


import com.smartosc.training.domains.Shipping;
import com.smartosc.training.dto.ShippingDto;

public interface ShippingService extends BaseService<Shipping, ShippingDto, Long>{
    void createShipment(Shipping shipping);
}
