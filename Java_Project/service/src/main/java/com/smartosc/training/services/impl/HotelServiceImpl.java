package com.smartosc.training.services.impl;

import com.smartosc.training.dto.*;
import com.smartosc.training.entities.*;
import com.smartosc.training.repositories.HotelRepository;
import com.smartosc.training.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Override
    public List<HotelDTO> getAllHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        List<HotelDTO> hotelResponseList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            hotelResponseList.add(this.convertFromHotelToHotelDTO(hotel));
        }
        return hotelResponseList;
    }

    private HotelDTO convertFromHotelToHotelDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();

        City city = hotel.getCity();
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setUrlImg(city.getUrlImg());
        cityDTO.setName(city.getName());

        Central central = city.getCentral();
        CentralDTO centralDTO = new CentralDTO();
        centralDTO.setTitle(central.getTitle());
        centralDTO.setId(central.getId());
        cityDTO.setCentral(centralDTO);

        List<Comment> commentList = hotel.getComments();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setRate(comment.getRate());
            commentDTO.setContent(comment.getContent());

            User user = comment.getUser();
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());

            commentDTO.setUser(userDTO);

            commentDTOList.add(commentDTO);
        }

        hotelDTO.setId(hotelDTO.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setComments(commentDTOList);
        hotelDTO.setImgUrl(hotel.getImgUrl());
        hotelDTO.setDescription(hotel.getDescription());
        hotelDTO.setCity(cityDTO);
        hotelDTO.setTotalRate(hotel.getTotalRate());

        return hotelDTO;
    }
}
