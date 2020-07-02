package com.smartosc.training.services;

import com.smartosc.training.dto.response.UserRespone;
import com.smartosc.training.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 10:33 AM
 * @created_by Namtt
 * @since 02/07/2020
 */

public interface UserService {
    UserRespone findUserByUserName(String name);

}
