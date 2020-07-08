package com.smartosc.training.dto;

<<<<<<< HEAD
import com.smartosc.training.validations.TitleConstraint;
import java.util.List;
import lombok.AllArgsConstructor;
=======
>>>>>>> master
import lombok.Getter;
import lombok.Setter;
<<<<<<< HEAD
=======

import java.util.List;

>>>>>>> master
/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 06/07/2020 - 2:02 PM
 */
@Getter
@Setter
public class CentralDTO {
<<<<<<< HEAD

  private Long id;
  @TitleConstraint private String title;
  private String imgUrl;
  private List<CityDTO> cityDTOS;
=======
    private Long id;
    private String title;
    private String imgUrl;
    private List<CityDTO> cities;
>>>>>>> master
}
