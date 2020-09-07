package com.smartosc.training.services.impl;

import com.smartosc.training.dto.CommentDTO;
import com.smartosc.training.dto.RoleDTO;
import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.entities.Role;
import com.smartosc.training.entities.User;
import com.smartosc.training.repositories.UserRepository;
import com.smartosc.training.services.UserService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 2:01 PM
 * @created_by Huupd
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO getUserById(Long id) throws NotFoundException {
        List<UserDTO> list = new ArrayList<>();
        Optional<User> userEntity = userRepository.findById(id);
        if (!userEntity.isPresent()) {
            log.info("get id fail,id not found");
            throw new NotFoundException("Id not found");
        }
        log.info("Get  user by id  success");
        return modelMapper.map(userEntity.get(), UserDTO.class);
    }

    @Override
    public UserDTO findUserByUserName(String name) throws NotFoundException {
        User userEntity = userRepository.findByUsername(name);
        if (userEntity != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userEntity.getId());
            userDTO.setUsername(userEntity.getUsername());
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setPassword(userEntity.getPassword());
            userDTO.setStatus(userEntity.getStatus());
            List<Role> roleList = userEntity.getRoles();
            List<RoleDTO> roleDTOS = new ArrayList<>();
            for (Role role : roleList) {

                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setId(role.getRoleId());
                roleDTO.setName(role.getName());
                roleDTOS.add(roleDTO);
            }
            userDTO.setRoleDTOS(roleDTOS);
            List<CommentDTO> commentDTOS = new ArrayList<>();
            userDTO.setCommentDTOS(commentDTOS);
            log.info("Find User by name success!");
            return userDTO;

        } else {
            throw new NotFoundException("User " + name + "Not Found");
        }
    }
}
