package com.smartosc.training.services.impl;

import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.entities.Role;
import com.smartosc.training.entities.User;
import com.smartosc.training.repositories.UserRepository;
import com.smartosc.training.services.UserService;
import com.smartosc.training.utils.AuthenEnum;
import com.smartosc.training.utils.RoleEnum;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 2:01 PM
 * @created_by Huupd
 */
@Service
public class UserServicesImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override

    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll(); // chứa list user lấy từ entity
        List<UserDTO> userRespones = new ArrayList<>(); // khai báo một list rỗng để chứa
        for (User user : users) {
            UserDTO userRespone = modelMapper.map(user, UserDTO.class);
            userRespones.add(userRespone); // add data vào list
        }
        return userRespones;
    }

    @Override
    public UserDTO createUser(UserDTO userRequest) throws DuplicateKeyException {
        User user;
        Role role = new Role();
        List<Role> roles = new ArrayList<>();

        if (userRepository.findByUsername(userRequest.getUsername()) != null) { // check da ton tai hay chua, neu ton tai in ra messeages
            throw new DuplicateKeyException(userRequest.getUsername() + "da ton tai");
        }
        // chua ton tai thi thuc hien tiep o duoi
        user = modelMapper.map(userRequest, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setStatus(AuthenEnum.valueOfStatus(AuthenEnum.ACTIVATED));

        role.setRoleId(RoleEnum.valueOfStatus(RoleEnum.ROLE_USER));
        roles.add(role);
        return userRequest;
    }

    @Override
    public UserDTO findUserByUserName(String name) throws NotFoundException {

        Optional<User> userEntity = userRepository.findByUsername11(name);
        if (userEntity.isPresent()) {
            return modelMapper.map(userEntity.get(), UserDTO.class);
        } else {
            throw new NotFoundException("UnAuthorized");
        }
    }

    @Override
    public UserDTO deleteUserById(Long id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            optionalUser.get().setStatus(0);
            User user = userRepository.save(optionalUser.get());
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return userDTO;
        } else {
            throw new NotFoundException("Khong tim thay id " + id);
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) throws NotFoundException {
        if (!userRepository.findById(id).isPresent()) {
            throw new NotFoundException("Tai khoan khong ton tai");
        }
        User user;
        user = modelMapper.map(userDTO, User.class);
        user.setId(id);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }
}
