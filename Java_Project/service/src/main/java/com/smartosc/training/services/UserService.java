package com.smartosc.training.services;


import com.smartosc.training.dto.UserDTO;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 10:33 AM
 * @created_by Namtt
 * @since 02/07/2020
 */

public interface UserService {
    UserDTO findUserByUserName(String name);

}
