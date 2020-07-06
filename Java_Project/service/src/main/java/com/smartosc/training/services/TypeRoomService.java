package com.smartosc.training.services;

import com.smartosc.training.dto.TypeRoomDTO;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 06/07/2020 - 11:01 AM
 * @created_by Huupd
 */
public interface TypeRoomService {
    List<TypeRoomDTO> findTypeRoomById(Long id);
}
