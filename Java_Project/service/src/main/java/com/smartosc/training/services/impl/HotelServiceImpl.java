package com.smartosc.training.services.impl;

import com.smartosc.training.dto.*;
import com.smartosc.training.entities.*;
import com.smartosc.training.exceptions.DuplicateException;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.repositories.CityRepository;
import com.smartosc.training.repositories.HotelRepository;
import com.smartosc.training.repositories.specifications.HotelSpecification;
import com.smartosc.training.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private CityRepository cityRepository;

    @Override
    public List<HotelDTO> getAllHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        List<HotelDTO> hotelResponseList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            hotelResponseList.add(this.convertFromHotelToHotelDTO(hotel));
        }
        return hotelResponseList;
    }

    @Override
    public HotelDTO getHotelByID(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);

        if (hotel.isPresent()) {
            return this.convertFromHotelToHotelDTO(hotel.get());
        } else {
            throw new NotFoundException("Thách mi tìm được đấy!");
        }
    }

    @Override
    public HotelDTO createNew(HotelDTO hotelDTO) {

        Optional<Hotel> input = hotelRepository.findByName(hotelDTO.getName());
        if (input.isPresent()) {
            throw new DuplicateException("Lặp lại rồi nha chế. Lấy tên khác đi");
        }
        Hotel hotel = new Hotel();

        this.convertFromDtoToEntity(hotel, hotelDTO);

        return hotelDTO;
    }

    @Override
    public HotelDTO updateHotel(HotelDTO hotelDTO) {
        Optional<Hotel> hotelID = hotelRepository.findById(hotelDTO.getId());
        if (hotelID.isPresent()) {
            Hotel hotel = hotelID.get();
            this.convertFromDtoToEntity(hotel, hotelDTO);
            return hotelDTO;
        } else {
            throw new NotFoundException("Có éo đâu mà đòi update");
        }
    }

    @Override
    public void deleteHotel(Long id) {
        if (hotelRepository.findById(id).isPresent()) {
            hotelRepository.deleteById(id);
        } else {
            throw new NotFoundException("Có éo đâu mà đòi delete");
        }
    }

    @Override
    public List<HotelDTO> geHotelsByName(String key) {
        List<Hotel> list = hotelRepository.findAll(HotelSpecification.geHotelsByNameSpec(key));

        List<HotelDTO> hotelResponseList = new ArrayList<>();
        for (Hotel hotel : list) {
            hotelResponseList.add(this.convertFromHotelToHotelDTO(hotel));
        }
        if (hotelResponseList.size() > 0) {
            return hotelResponseList;
        } else {
            throw new NotFoundException("Không tìm thấy gì đâu");
        }
    }

    @Override
    public List<HotelDTO> geListHotelFollowedByCity(String key) {
        Optional<City> city = cityRepository.findByName(key);

        List<Hotel> hotelList = hotelRepository.findAll(HotelSpecification.getHotelsByCity(city.get()));

        List<HotelDTO> hotelResponseList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            hotelResponseList.add(this.convertFromHotelToHotelDTO(hotel));
        }
        return hotelResponseList;
    }

    private Hotel convertFromDtoToEntity(Hotel hotel, HotelDTO hotelDTO) {
        CityDTO cityDTO = hotelDTO.getCity();
        City city = new City();
        city.setId(cityDTO.getId());
        hotel.setCity(city);

        hotel.setDescription(hotelDTO.getDescription());
        hotel.setImgUrl(hotelDTO.getImgUrl());
        hotel.setName(hotelDTO.getName());
        hotel.setTotalRate(hotelDTO.getTotalRate());

        List<TypeRoomDTO> typeRoomDTOList = hotelDTO.getTypeRooms();
        List<TypeRoom> typeRoomList = new ArrayList<>();
        if (typeRoomDTOList != null) {
            for (TypeRoomDTO typeRoomDTO : typeRoomDTOList) {
                TypeRoom typeRoom = new TypeRoom();

                typeRoom.setId(typeRoomDTO.getId());
                typeRoom.setImgUrl(typeRoomDTO.getImgUrl());
                typeRoom.setName(typeRoomDTO.getName());
                typeRoom.setTotalPrice(typeRoomDTO.getTotalPrice());
                typeRoomList.add(typeRoom);
            }
        }
        hotel.setTypeRooms(typeRoomList);

        hotelRepository.save(hotel);

        return hotel;
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

        List<TypeRoom> typeRoomList = hotel.getTypeRooms();
        List<TypeRoomDTO> typeRoomDTOList = new ArrayList<>();

        for (TypeRoom typeRoom : typeRoomList) {
            TypeRoomDTO typeRoomDTO = new TypeRoomDTO();
            typeRoomDTO.setId(typeRoom.getId());
            typeRoomDTO.setName(typeRoom.getName());
            typeRoomDTO.setImgUrl(typeRoom.getImgUrl());
            typeRoomDTO.setTotalPrice(typeRoom.getTotalPrice());

            typeRoomDTOList.add(typeRoomDTO);
        }

        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setComments(commentDTOList);
        hotelDTO.setImgUrl(hotel.getImgUrl());
        hotelDTO.setDescription(hotel.getDescription());
        hotelDTO.setCity(cityDTO);
        hotelDTO.setTotalRate(hotel.getTotalRate());
        hotelDTO.setTypeRooms(typeRoomDTOList);

        return hotelDTO;
    }
}
