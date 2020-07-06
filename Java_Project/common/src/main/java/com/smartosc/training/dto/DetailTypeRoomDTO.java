package com.smartosc.training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 02/07/2020 - 10:43 AM
 * @created_by Hieupv
 * @since 02/07/2020
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetailTypeRoomDTO {
    private Long id;
    private String description;
    private Integer maxPerson;
    private Integer defaultPerson;
    private BigDecimal price;
    private BigDecimal additionalPrice;
    private Integer roomAvailable;
}
