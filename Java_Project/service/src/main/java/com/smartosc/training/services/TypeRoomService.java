package com.smartosc.training.services;

import com.smartosc.training.dto.TypeRoomDTO;

import com.smartosc.training.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 02/07/2020 - 1:59 PM
 * @created_by Hieupv
 * @since 02/07/2020
 */
public interface TypeRoomService {
    public Page<TypeRoomDTO> findTypeRoomById(Long id, Pageable pageable);
    public TypeRoomDTO updateTypeRoom(TypeRoomDTO typeRoomRequest) throws NotFoundException;
    public TypeRoomDTO createTypeRoom(TypeRoomDTO typeRoomRequest);
    public List<TypeRoomDTO> findTypeRoomByName(String name);
    public TypeRoomDTO search(Long id);
}
