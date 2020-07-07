package com.smartosc.training.services;

import com.smartosc.training.dto.CityDTO;
import com.smartosc.training.exceptions.NullPointerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 2:18 PM
 */
public interface CityService {
    List<CityDTO> getAllCities() throws NullPointerException;

    CityDTO getCityWithHotels(Long id);

    Page<CityDTO> getCitiesWithPagination(Pageable pageable);

    CityDTO createNew(CityDTO cityDTO);

    CityDTO updateInformation(CityDTO cityDTO);
}
