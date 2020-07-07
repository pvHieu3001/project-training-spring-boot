package com.smartosc.training.services;


import java.util.List;

import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.exceptions.NotFoundException;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 10:33 AM
 * @created_by Namtt
 * @since 02/07/2020
 */

public interface UserService {
    List<UserDTO> getAllUser();

    List<UserDTO> getAllUserStatusTrue();

    List<UserDTO> getAllUserWithSpec();

    UserDTO createUser(UserDTO userRequest);

    UserDTO deleteUserById(Long id) throws NotFoundException;

    UserDTO updateUser(Long id, UserDTO userRequest) throws NotFoundException;

    UserDTO findUserByUserName(String name) throws NotFoundException;

    List<UserDTO> getUserById(Long id) throws NotFoundException;




}
