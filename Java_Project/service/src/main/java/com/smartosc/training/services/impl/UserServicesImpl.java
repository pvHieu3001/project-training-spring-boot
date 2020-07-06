package com.smartosc.training.services.impl;

import com.smartosc.training.dto.request.UserRequest;
import com.smartosc.training.dto.response.UserRespone;
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
    public List<UserRespone> getAllUser() {
        List<User> users = userRepository.findAll(); // chứa list user lấy từ entity
        List<UserRespone> userRespones = new ArrayList<>(); // khai báo một list rỗng để chứa
        for (User user : users) {
            UserRespone userRespone = modelMapper.map(user, UserRespone.class);
            userRespones.add(userRespone); // add data vào list
        }
        return userRespones;
    }

    @Override
    public UserRequest createUser(UserRequest userRequest) throws DuplicateKeyException{
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
    public UserRespone findUserByUserName(String name) throws NotFoundException {
        Optional<User> userEntity = userRepository.findByUsername11(name);
        if (userEntity.isPresent()) {
            return modelMapper.map(userEntity.get(), UserRespone.class);
        } else {
            throw new NotFoundException("UnAuthorized");
        }
    }

    @Override
    public UserRespone deleteUserById(Long id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            optionalUser.get().setStatus(0);
            User user = userRepository.save(optionalUser.get());
            UserRespone userRespone = modelMapper.map(user, UserRespone.class);
            return userRespone;
        } else {
            throw new NotFoundException("Khong tim thay id " + id);
        }
    }

    @Override
    public UserRequest updateUser(Long id, UserRequest userRequest) throws NotFoundException {
        if (!userRepository.findById(id).isPresent()) {
            throw new NotFoundException("Tai khoan khong ton tai");
        }
        User user;
        user = modelMapper.map(userRequest, User.class);
        user.setId(id);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return modelMapper.map(userRepository.save(user), UserRequest.class);
    }
}
