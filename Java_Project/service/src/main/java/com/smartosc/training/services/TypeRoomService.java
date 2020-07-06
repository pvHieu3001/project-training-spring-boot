package com.smartosc.training.services;

import com.smartosc.training.dto.TypeRoomDTO;
import org.springframework.data.domain.Page;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 02/07/2020 - 1:59 PM
 * @created_by Hieupv
 * @since 02/07/2020
 */
public interface TypeRoomService {
    public Page<TypeRoomDTO> findTypeRoomById(Long id, int page, int limit);
}
