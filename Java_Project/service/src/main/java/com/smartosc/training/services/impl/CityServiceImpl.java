package com.smartosc.training.services.impl;

import com.smartosc.training.dto.*;
import com.smartosc.training.entities.*;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.exceptions.NullPointerException;
import com.smartosc.training.repositories.CityRepository;
import com.smartosc.training.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                throw new NullPointerException("Có dữ liệu đâu mà lấy đồ ngốc");
            }
            cityDTOList.add(this.convertFromCityToCityDTO(city));
        }
        return cityDTOList;
    }

    @Override
    public CityDTO getCityWithHotels(Long id) throws NotFoundException {
        Optional<City> city = cityRepository.findById(id);

        if (city.isPresent()) {
            return this.convertFromCityToCityDTO(city.get());
        }
        throw new NotFoundException("Thách mi tìm được đấy!");
    }

    //TODO
    @Override
    public CityDTO save(CityDTO cityDTO) {
        Optional<City> input = cityRepository.findById(cityDTO.getId());
        if (input.isPresent()) {
            //update
            City city = input.get();

            CentralDTO centralDTO = cityDTO.getCentral();
            Central central = new Central();
            central.setId(centralDTO.getId());
            central.setImgUrl(centralDTO.getImgUrl());
            central.setTitle(centralDTO.getTitle());
            city.setCentral(central);
            city.setName(cityDTO.getName());
            city.setUrlImg(cityDTO.getUrlImg());
        }
        //create

        return null;
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
