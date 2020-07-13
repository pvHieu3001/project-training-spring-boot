package com.smartosc.training.services.impl;

import com.smartosc.training.dto.CentralDTO;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.mappers.CentralConvert;
import com.smartosc.training.repositories.CentralRepository;
import com.smartosc.training.repositories.specifications.CentralSpecification;
import com.smartosc.training.services.CentralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

  @Autowired private CentralRepository centralRepository;

  @Override
  public CentralDTO createCentral(CentralDTO centralDTO) {
    if (!centralRepository.findByTitle(centralDTO.getTitle()).isPresent()) {
      return CentralConvert.convertToDTO(
          centralRepository.save(CentralConvert.convertToEntity(centralDTO)));
    } else {
      throw new DuplicateKeyException("Duplicate.central.title");
    }
  }
  // get central by title and id
  @Override
  public List<CentralDTO> getAllCentral(String keyword, Long id) {
    CentralSpecification centralSpecification = CentralSpecification.spec();
    Optional.ofNullable(keyword).ifPresent(centralSpecification::byTitle);
    Optional.ofNullable(id).ifPresent(centralSpecification::byId);
    return CentralConvert.convertListDto(centralRepository.findAll(centralSpecification.build()));
  }

  @Override
  public CentralDTO updateCentral(Long id, CentralDTO centralDTO) {
    if (centralRepository.findById(id).isPresent()) {
      throw new NotFoundException("NotFound.central.id");
    }
    centralDTO.setId(id);
    centralRepository.save(CentralConvert.convertToEntity(centralDTO));
    return centralDTO;
  }
}
