package com.smartosc.training.dto;

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
