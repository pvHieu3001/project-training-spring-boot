package com.smartosc.training.domain.order;

import com.smartosc.training.base.IdDto;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends IdDto {
    private String code;

    public OrderDto(Long i, String hha) {
        super(i);
        this.code = hha;
    }
}
