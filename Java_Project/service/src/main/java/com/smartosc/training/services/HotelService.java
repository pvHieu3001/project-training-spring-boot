package com.smartosc.training.services;

import com.smartosc.training.dto.HotelDTO;


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

    HotelDTO updateHotel(HotelDTO hotelDTO);

    void deleteHotel(Long id);

    List<HotelDTO> geHotelsByName(String key);
}
