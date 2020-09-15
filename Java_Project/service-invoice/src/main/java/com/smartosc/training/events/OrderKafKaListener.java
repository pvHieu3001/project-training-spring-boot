package com.smartosc.training.events;


import com.smartosc.training.dto.InvoiceDto;
import com.smartosc.training.services.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class OrderKafKaListener {
    private final Logger log = LoggerFactory.getLogger(OrderKafKaListener.class);

    private InvoiceService invoiceService;
    @Autowired
    public OrderKafKaListener(InvoiceService invoiceService) {
        super();
        this.invoiceService = invoiceService;
    }

    @KafkaListener(topics = "order")
    public void order(OrderDB order, Acknowledgment acknowledgment) {
        log.info("Received order " + order.getId());
        InvoiceDto invoice = new InvoiceDto();
        invoice.setCode("testcode");
        invoiceService.save(invoice);
        acknowledgment.acknowledge();
    }
}
