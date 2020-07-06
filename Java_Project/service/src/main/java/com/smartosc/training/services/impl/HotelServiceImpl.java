package com.smartosc.training.services.impl;

import com.smartosc.training.dto.response.HotelResponse;
import com.smartosc.training.entities.Hotel;
import com.smartosc.training.repositories.HotelRepository;
import com.smartosc.training.services.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 2:17 PM
 */
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<HotelResponse> getAllHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        List<HotelResponse> hotelResponseList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            hotelResponseList.add(modelMapper.map(hotel, HotelResponse.class));
        }
        return hotelResponseList;
    }
}
