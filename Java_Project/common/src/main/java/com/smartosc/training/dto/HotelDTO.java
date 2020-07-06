package com.smartosc.training.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 2:30 PM
 */
@Setter
@Getter
public class HotelDTO {
    private Long id;
    private String name;
    private String description;
    private String imgUrl;
    private BigDecimal totalRate;
    private CityDTO city;
    private List<CommentDTO> comments;
}
