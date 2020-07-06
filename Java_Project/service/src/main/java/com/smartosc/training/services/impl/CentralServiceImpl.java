package com.smartosc.training.services.impl;

import com.smartosc.training.dto.CentralDTO;
import com.smartosc.training.mappers.CentralConvert;
import com.smartosc.training.repositories.CentralRepository;
import com.smartosc.training.repositories.specifications.CentralSpecification;
import com.smartosc.training.services.CentralService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 03/07/2020 - 10:06 AM
 * @created_by Namtt
 * @since 03/07/2020
 */
@Service
public class CentralServiceImpl implements CentralService {

  @Autowired
  private CentralRepository centralRepository;

  @Override
  public List<CentralDTO> getAllCentral(String keyword) {
    CentralSpecification centralSpecification = CentralSpecification.spec();
    Optional.ofNullable(keyword).ifPresent(centralSpecification::byTitle);
    return CentralConvert.convertListDto(centralRepository.findAll(centralSpecification.build()));
  }

}