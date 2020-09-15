package com.smartosc.training.services.impls;

import com.smartosc.training.repositories.BaseRepository;
import com.smartosc.training.converter.ObjectMapperUtils;
import com.smartosc.training.domains.Invoice;
import com.smartosc.training.dto.InvoiceDto;
import com.smartosc.training.repositories.InvoiceRepository;
import com.smartosc.training.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public BaseRepository<Invoice, Long> getDao() {
        return invoiceRepository;
    }

    @Override
    public Invoice createDto(InvoiceDto dto) {
        return ObjectMapperUtils.map(dto, Invoice.class);
    }

    @Override
    public void convertDtoToEntity(InvoiceDto dto, Invoice entity) {
        ObjectMapperUtils.map(dto, entity);
    }

    @Override
    public Invoice save(InvoiceDto dto) {
        return invoiceRepository.save(this.createDto(dto));
    }
}
