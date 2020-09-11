package com.smartosc.training.services.impls;

import com.smartosc.training.domain.shipping.Shipping;
import com.smartosc.training.repositories.ShippingRepository;
import com.smartosc.training.services.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceImpl implements ShippingService {
    private final ShippingRepository shippingRepository;

    @Autowired
    public ShippingServiceImpl(ShippingRepository shippingRepository){
        this.shippingRepository = shippingRepository;
    }
    @Override
    public void createShipment(Shipping shipping) {
        shippingRepository.save(shipping);
    }
}
