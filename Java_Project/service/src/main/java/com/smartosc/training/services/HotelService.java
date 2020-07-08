package com.smartosc.training.services;

import com.smartosc.training.dto.HotelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 2:17 PM
 */
public interface HotelService {
    List<HotelDTO> getAllHotels();

    HotelDTO getHotelByID(Long id);

    HotelDTO createNew(HotelDTO hotelDTO);
}
