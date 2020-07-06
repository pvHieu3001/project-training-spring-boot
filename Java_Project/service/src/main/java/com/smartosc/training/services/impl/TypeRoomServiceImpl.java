package com.smartosc.training.services.impl;

import com.smartosc.training.dto.response.TypeRoomRespone;
import com.smartosc.training.entities.TypeRoom;
import com.smartosc.training.repositories.TypeRoomRepository;
import com.smartosc.training.services.TypeRoomService;
import com.smartosc.training.repositories.specifications.TypeRoomSpecification;
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
    public List<TypeRoomRespone> findTypeRoomById(Long id) {
        TypeRoomSpecification typeRoomSpecification = TypeRoomSpecification.spec();
        List<TypeRoomRespone> result = new ArrayList<>();
//        Optional.ofNullable(id).ifPresent(s -> typeRoomSpecification.byId(id));
        for (TypeRoom typeRoom : typeRoomRepository.findAll(typeRoomSpecification.build())){
            result.add(modelMapper.map(typeRoom, TypeRoomRespone.class));
        }
        return result;
    }
}
