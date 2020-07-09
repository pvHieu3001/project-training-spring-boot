package com.smartosc.training.services.impl;

import com.smartosc.training.dto.TypeRoomDTO;

import com.smartosc.training.entities.TypeRoom;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.repositories.TypeRoomRepository;
import com.smartosc.training.repositories.specifications.TypeRoomSpecification;
import com.smartosc.training.services.TypeRoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;
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
public class TypeRoomServiceImpl implements TypeRoomService {
    @Autowired
    private TypeRoomRepository typeRoomRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TypeRoomDTO> findTypeRoomById(Long id, Pageable pageable) {
        return typeRoomRepository.findAll(TypeRoomSpecification
                .spec()
                .typeRoomHasId(id)
                .build(), pageable)
                .stream().map(s -> modelMapper.map(s,TypeRoomDTO.class))
                .collect(Collectors.toList()
                );
    }

    @Override
    public TypeRoomDTO updateTypeRoom(TypeRoomDTO typeRoomDTO)  {
        Optional<TypeRoom> typeRoomOptional = Optional.ofNullable(
                typeRoomRepository.findById(typeRoomDTO.getId())
                .orElseThrow(() -> {
                    return new NotFoundException("error.msg.type_room.data.notfound");
                }));
        TypeRoom typeRoom = typeRoomOptional.get();
        typeRoom.setImgUrl(typeRoomDTO.getImgUrl());
        typeRoom.setName(typeRoomDTO.getName());
        typeRoom.setTotalPrice(typeRoomDTO.getTotalPrice());
        typeRoom = typeRoomRepository.save(typeRoom);
        return modelMapper.map(typeRoom, TypeRoomDTO.class);
    }

    @Override
    public TypeRoomDTO createTypeRoom(TypeRoomDTO typeRoomDTO) {
        TypeRoom typeRoom = modelMapper.map(typeRoomDTO, TypeRoom.class);
        typeRoom = typeRoomRepository.save(typeRoom);
        return modelMapper.map(typeRoom, TypeRoomDTO.class);
    }

    @Override
    public void deleteTypeRoom(Long[] id) {

    }
}
