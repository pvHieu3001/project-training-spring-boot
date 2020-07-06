package com.smartosc.training.services;

import com.smartosc.training.dto.TypeRoomDTO;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 06/07/2020 - 11:22 AM
 */
public interface TypeRoomService {
    public List<TypeRoomDTO> findTypeRoomById(Long id);
}
