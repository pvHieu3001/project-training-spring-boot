package com.smartosc.training.services;

import com.smartosc.training.dto.TypeRoomDTO;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;

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
    public List<TypeRoomDTO> findTypeRoomById(Long id);
    public TypeRoomDTO updateTypeRoom(TypeRoomDTO typeRoomRequest) throws NotFoundException;
    public TypeRoomDTO createTypeRoom(TypeRoomDTO typeRoomRequest);
    public void deleteTypeRoom(Long[] id);
}
