package com.smartosc.training.services.impl;

import com.smartosc.training.dto.DetailTypeRoomDTO;
import com.smartosc.training.dto.TypeRoomDTO;
import com.smartosc.training.entities.DetailTypeRoom;
import com.smartosc.training.entities.TypeRoom;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.repositories.DetailTypeRoomRepository;
import com.smartosc.training.repositories.TypeRoomRepository;
import com.smartosc.training.repositories.specifications.DetailTypeRoomSpecification;
import com.smartosc.training.repositories.specifications.TypeRoomSpecification;
import com.smartosc.training.services.DetailTypeRoomService;
import com.smartosc.training.services.TypeRoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 02/07/2020 - 1:59 PM
 * @created_by Hieupv
 * @since 02/07/2020
 */
@Service
public class DetailTypeRoomServiceImpl implements DetailTypeRoomService {
    @Autowired
    private DetailTypeRoomRepository detailTypeRoomRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MessageSource messageSource;

    @Override
    public List<DetailTypeRoomDTO> findDetailTypeRoomById(Long id) {
        return detailTypeRoomRepository.findAll(DetailTypeRoomSpecification
                .spec()
                .detailTypeRoomHasId(id)
                .build())
                .stream().map(s -> modelMapper.map(s, DetailTypeRoomDTO.class))
                .collect(Collectors.toList()
                );
    }

    @Override
    public DetailTypeRoomDTO updateDetailTypeRoom(DetailTypeRoomDTO detailTypeRoomDTO)  {
        Optional<DetailTypeRoom> detailTypeRoomOptional = Optional.ofNullable(
                detailTypeRoomRepository.findById(detailTypeRoomDTO.getId())
                .orElseThrow(() -> {
                    return new NotFoundException("aaa");
                }));
        DetailTypeRoom detailTypeRoom = detailTypeRoomOptional.get();
        detailTypeRoom.setAdditionalPrice(detailTypeRoomDTO.getAdditionalPrice());
        detailTypeRoom.setDefaultPerson(detailTypeRoomDTO.getDefaultPerson());
        detailTypeRoom.setMaxPerson(detailTypeRoomDTO.getMaxPerson());
        detailTypeRoom.setPrice(detailTypeRoomDTO.getPrice());
        detailTypeRoom.setRoomAvailable(detailTypeRoomDTO.getRoomAvailable());
        detailTypeRoom = detailTypeRoomRepository.save(detailTypeRoom);
        return modelMapper.map(detailTypeRoom, DetailTypeRoomDTO.class);
    }

    @Override
    public DetailTypeRoomDTO createDetailTypeRoom(DetailTypeRoomDTO detailTypeRoomDTO) {
        DetailTypeRoom typeRoom = modelMapper.map(detailTypeRoomDTO, DetailTypeRoom.class);
        typeRoom = detailTypeRoomRepository.save(typeRoom);
        return modelMapper.map(typeRoom, DetailTypeRoomDTO.class);
    }

    @Override
    public void deleteDetailTypeRoom(Long[] id) {

    }
}
