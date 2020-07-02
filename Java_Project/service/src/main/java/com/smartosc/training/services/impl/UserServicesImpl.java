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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class UserServicesImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserRequest createUser(UserRequest userRequest) {
        User user;
        Role role = new Role();
        List<Role> roles = new ArrayList<>();

        if (userRepository.findByUserName(userRequest.getUsername()) != null) { // check da ton tai hay chua, neu ton tai in ra messeages
            throw new DuplicateKeyException(userRequest.getUsername() + "da ton tai");
        }
        // chua ton tai thi thuc hien tiep o duoi
        user = modelMapper.map(userRequest, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setStatus(AuthenEnum.valueOfStatus(AuthenEnum.ACTIVATED));

        role.setRoleId(RoleEnum.valueOfStatus(RoleEnum.ROLE_USER));
        roles.add(role);
        return null;
    }

    @Override
    public UserRespone findUserByName(String name) throws NotFoundException {
        try {
            Optional<User> account = userRepository.findByUserName(name);
            return modelMapper.map(account, UserRespone.class);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("Not found User with Username: " + name, e);
        }
    }

//    @Override
//    public UserRespone findUserByName(String name) throws NotFoundException {
//        Optional<User> user = userRepository.findByUserName(name);
//        UserRespone userRespone;
//        if (user.isPresent()){
////            userRespone =
//        }
//    }

    @Override
    public Boolean deleteUserById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public UserRequest updateUser(UserRequest userRequest) throws NotFoundException {
        return null;
    }
}
