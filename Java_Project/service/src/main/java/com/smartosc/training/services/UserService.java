package com.smartosc.training.services;

import com.smartosc.training.dto.request.UserRequest;
import com.smartosc.training.dto.response.UserRespone;
import javassist.NotFoundException;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 10:33 AM
 * @created_by Namtt
 * @since 02/07/2020
 */

public interface UserService {
    UserRequest createUser(UserRequest userRequest);

    Boolean deleteUserById(Long id) throws NotFoundException;

    UserRequest updateUser(Long id, UserRequest userRequest) throws NotFoundException;

    UserRespone findUserByUserName(String name) throws NotFoundException;

}
