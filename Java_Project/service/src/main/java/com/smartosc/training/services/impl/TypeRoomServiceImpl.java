package com.smartosc.training.services.impl;

import com.smartosc.training.dto.response.TypeRoomRespone;
import com.smartosc.training.services.TypeRoomService;
import org.springframework.data.domain.Page;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 02/07/2020 - 1:59 PM
 * @created_by Hieupv
 * @since 02/07/2020
 */
public class TypeRoomServiceImpl implements TypeRoomService {
    @Override
    public Page<TypeRoomRespone> findTypeRoomById(Long id, int page, int limit) {
        
    }
}
