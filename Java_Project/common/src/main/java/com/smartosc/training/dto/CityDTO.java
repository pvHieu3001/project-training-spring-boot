package com.smartosc.training.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 2:23 PM
 */
@Getter
@Setter
public class CityDTO {
    private Long id;
    private String name;
    private String urlImg;
    private List<HotelDTO> hotels;
    private CentralDTO central;
}
