package com.smartosc.training.services.impl;

import com.smartosc.training.dto.TypeRoomDTO;

import com.smartosc.training.entities.TypeRoom;
import com.smartosc.training.repositories.TypeRoomRepository;
import com.smartosc.training.repositories.specifications.TypeRoomSpecification;
import com.smartosc.training.services.TypeRoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public List<TypeRoomDTO> findTypeRoomById(Long id) {
        TypeRoomSpecification typeRoomSpecification = new TypeRoomSpecification();
        List<TypeRoomDTO> result = new ArrayList<>();
        Optional.ofNullable(id).ifPresent(s -> typeRoomSpecification.typeRoomHasId(id));
        for (TypeRoom typeRoom : typeRoomRepository.findAll(typeRoomSpecification.build())){
            result.add(modelMapper.map(typeRoom, TypeRoomDTO.class));
        }
        return result;
    }

    @Override
    public TypeRoomDTO updateTypeRoom(TypeRoomDTO typeRoomDTO) {
        return null;
    }

    @Override
    public TypeRoomDTO createTypeRoom(TypeRoomDTO typeRoomDTO) {
        return null;
    }

    @Override
    public void deleteTypeRoom(Long[] id) {

    }
}
