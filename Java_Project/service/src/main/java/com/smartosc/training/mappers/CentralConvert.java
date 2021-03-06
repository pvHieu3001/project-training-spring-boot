package com.smartosc.training.mappers;

import com.smartosc.training.dto.CentralDTO;
import com.smartosc.training.entities.Central;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 10:49 AM
 * @created_by Namtt
 * @since 02/07/2020
 */
public class CentralConvert {

  @Autowired private static ModelMapper MODEL_MAPPER = new ModelMapper();

  public static CentralDTO convertToDTO(Central central) {
    return MODEL_MAPPER.map(central, CentralDTO.class);
  }

  public static List<CentralDTO> convertListDto(List<Central> centrals) {
    List<CentralDTO> centralDTOS = new ArrayList<>();
    for (Central central : centrals) {
      centralDTOS.add(convertToDTO(central));
    }
    return centralDTOS;
  }

  public static Central convertToEntity(CentralDTO centralDTO) {
    return MODEL_MAPPER.map(centralDTO, Central.class);
  }
}
