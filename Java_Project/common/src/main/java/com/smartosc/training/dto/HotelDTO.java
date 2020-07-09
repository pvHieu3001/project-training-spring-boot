package com.smartosc.training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    private Long id;
    private String name;
    private String description;
    private String imgUrl;
    private BigDecimal totalRate;
    private CityDTO city;
    private List<CommentDTO> comments;
    private List<TypeRoomDTO> typeRooms;
}
