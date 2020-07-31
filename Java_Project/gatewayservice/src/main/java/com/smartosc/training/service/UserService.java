package com.smartosc.training.service;


import com.smartosc.training.dto.UserDTO;
import javassist.NotFoundException;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 10:33 AM
 * @created_by Namtt
 * @since 02/07/2020
 */

public interface UserService {
    UserDTO findUserByUserName(String name) throws NotFoundException;

    UserDTO getUserById(Long id) throws NotFoundException;




}
