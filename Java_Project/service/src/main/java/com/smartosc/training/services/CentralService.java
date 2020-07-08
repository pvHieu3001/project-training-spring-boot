package com.smartosc.training.services;

import com.smartosc.training.dto.CentralDTO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 03/07/2020 - 10:06 AM
 * @created_by Namtt
 * @since 03/07/2020
 */

public interface CentralService {

  List<CentralDTO> getAllCentral(String keyword,Long id);

  CentralDTO createCentral(CentralDTO centralDTO);

  CentralDTO updateCentral(Long id,CentralDTO centralDTO);

}
