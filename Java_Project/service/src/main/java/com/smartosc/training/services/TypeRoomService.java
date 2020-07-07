package com.smartosc.training.services;

import com.smartosc.training.dto.TypeRoomDTO;

import javassist.NotFoundException;


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
    List<TypeRoomDTO> findTypeRoomById(Long id);

    TypeRoomDTO updateTypeRoom(TypeRoomDTO typeRoomRequest) throws NotFoundException;

    TypeRoomDTO createTypeRoom(TypeRoomDTO typeRoomRequest);

    void deleteTypeRoom(Long[] id);

}
