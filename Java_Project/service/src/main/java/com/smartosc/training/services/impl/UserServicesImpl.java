package com.smartosc.training.services.impl;

import com.smartosc.training.dto.response.UserRespone;
import com.smartosc.training.entities.User;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.repositories.RoleRepository;
import com.smartosc.training.repositories.UserRepository;
import com.smartosc.training.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 2:01 PM
 * @created_by Huupd
 */
@Service
@Transactional
public class UserServicesImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserRespone findUserByUserName(String name) {
        Optional<User> userEntity = userRepository.findByUsername(name);
        if (userEntity.isPresent()) {
            return modelMapper.map(userEntity.get(), UserRespone.class);
        } else {
            throw new NotFoundException("UnAuthorized");
        }
    }
}
