package com.smartosc.training.dto;


import com.smartosc.training.validations.TitleConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class CentralDTO {


  private Long id;
  @TitleConstraint private String title;
  private String imgUrl;
  private List<CityDTO> cityDTOS;
}
