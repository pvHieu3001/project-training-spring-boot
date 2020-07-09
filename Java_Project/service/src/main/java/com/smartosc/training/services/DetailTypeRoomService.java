package com.smartosc.training.services;

import com.smartosc.training.dto.DetailTypeRoomDTO;
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
public interface DetailTypeRoomService {
    public List<DetailTypeRoomDTO> findDetailTypeRoomById(Long id);
    public DetailTypeRoomDTO updateDetailTypeRoom(DetailTypeRoomDTO detailTypeRoomDTO) throws NotFoundException;
    public DetailTypeRoomDTO createDetailTypeRoom(DetailTypeRoomDTO detailTypeRoomDTO);
    public void deleteDetailTypeRoom(Long[] id);
}
