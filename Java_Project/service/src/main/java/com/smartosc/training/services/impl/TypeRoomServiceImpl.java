package com.smartosc.training.services.impl;

import com.smartosc.training.dto.TypeRoomDTO;
import com.smartosc.training.repositories.TypeRoomRepository;
import com.smartosc.training.services.TypeRoomService;
import com.smartosc.training.specifications.TypeRoomSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
    private TypeRoomSpecification typeRoomSpecification;

    @Override
    public Page<TypeRoomDTO> findTypeRoomById(Long id, int page, int limit) {
        Optional.ofNullable(id).ifPresent(s -> typeRoomSpecification.byId(id));
        return null;
    }
}
