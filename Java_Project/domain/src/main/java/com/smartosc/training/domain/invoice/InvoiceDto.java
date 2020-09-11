package com.smartosc.training.domain.invoice;

import com.smartosc.training.base.IdDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto extends IdDto{
    private String code;
}
