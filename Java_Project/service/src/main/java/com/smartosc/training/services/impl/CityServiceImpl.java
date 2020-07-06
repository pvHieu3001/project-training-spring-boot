package com.smartosc.training.services.impl;

import com.smartosc.training.dto.*;
import com.smartosc.training.entities.*;
import com.smartosc.training.exceptions.NullPointerException;
import com.smartosc.training.repositories.CityRepository;
import com.smartosc.training.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 2:19 PM
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<CityDTO> getAllCities() throws NullPointerException {
        List<CityDTO> cityDTOList = new ArrayList<>();
        List<City> cityList = cityRepository.findAll();

        for (City city : cityList) {
            Central central = city.getCentral();
            if (central == null) {
                throw new NullPointerException("M bij ngu rooif");
            }
            cityDTOList.add(this.convertFromCityToCityDTO(city));
        }
        return cityDTOList;
    }

    private CityDTO convertFromCityToCityDTO(City city) {
        CityDTO cityDTO = new CityDTO();

        Central central = city.getCentral();
        CentralDTO centralDTO = new CentralDTO();
        centralDTO.setId(central.getId());
        centralDTO.setImgUrl(central.getImgUrl());
        centralDTO.setTitle(central.getTitle());


        List<Hotel> hotelList = city.getHotels();
        if (hotelList.isEmpty()) {
            List<HotelDTO> hotelDTOList = new ArrayList<>();
            cityDTO.setHotels(hotelDTOList);
        }
        List<HotelDTO> hotelDTOList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            HotelDTO hotelDTO = new HotelDTO();

            hotelDTO.setId(hotel.getId());
            hotelDTO.setName(hotel.getName());
            hotelDTO.setImgUrl(hotel.getImgUrl());
            hotelDTO.setDescription(hotel.getDescription());
            hotelDTO.setTotalRate(hotel.getTotalRate());
            List<Comment> commentList = hotel.getComments();
            List<CommentDTO> commentDTOList = new ArrayList<>();

            for (Comment comment : commentList) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(comment.getId());
                commentDTO.setContent(comment.getContent());
                commentDTO.setRate(comment.getRate());
                User user = comment.getUser();
                UserDTO userDTO = new UserDTO();
                userDTO.setEmail(user.getEmail());
                userDTO.setUsername(user.getUsername());
                commentDTO.setUser(userDTO);
                commentDTOList.add(commentDTO);
            }
            hotelDTO.setComments(commentDTOList);
            hotelDTOList.add(hotelDTO);
        }

        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setUrlImg(city.getUrlImg());
        cityDTO.setCentral(centralDTO);
        cityDTO.setHotels(hotelDTOList);
        return cityDTO;
    }
}
