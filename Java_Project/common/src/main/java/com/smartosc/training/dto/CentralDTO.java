package com.smartosc.training.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 06/07/2020 - 10:27 AM
 * @created_by Namtt
 * @since 06/07/2020
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CentralDTO {
  private Long id;
  private String title;
  private String imgUrl;
  private List<CityDTO> cityDTOS;

}
