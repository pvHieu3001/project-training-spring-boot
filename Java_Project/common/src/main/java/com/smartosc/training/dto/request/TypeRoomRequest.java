package com.smartosc.training.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 02/07/2020 - 10:42 AM
 * @created_by Hieupv
 * @since 02/07/2020
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TypeRoomRequest {
    private Long id;
    private String name;
    private String imgUrl;
    private BigDecimal totalPrice;
}
