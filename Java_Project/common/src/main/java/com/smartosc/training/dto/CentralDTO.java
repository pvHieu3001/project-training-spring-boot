package com.smartosc.training.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 06/07/2020 - 2:02 PM
 */
@Getter
@Setter
public class CentralDTO {
    private Long id;
    private String title;
    private String imgUrl;
    private List<CityDTO> cities;
}
