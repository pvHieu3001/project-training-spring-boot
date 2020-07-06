package com.smartosc.training.services;

import com.smartosc.training.dto.CityDTO;
import com.smartosc.training.exceptions.NullPointerException;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 2:18 PM
 */
public interface CityService {
    List<CityDTO> getAllCities() throws NullPointerException;
}
