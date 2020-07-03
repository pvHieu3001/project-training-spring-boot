package com.smartosc.training.dto.response;

import com.smartosc.training.dto.request.TypeRoomRequest;
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
public class TypeRoomRespone {
    private Long id;
    private String name;
    private String imgUrl;
    private BigDecimal totalPrice;
}
