package com.smartosc.training.services.impl;

import com.smartosc.training.dto.CommentDTO;
import com.smartosc.training.dto.RoleDTO;
import com.smartosc.training.dto.StatusOTDTO;
import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.entities.Role;
import com.smartosc.training.entities.StatusOT;
import com.smartosc.training.entities.User;
import com.smartosc.training.repositories.UserRepository;
import com.smartosc.training.repositories.specifications.UserSpecifications;
import com.smartosc.training.services.UserService;
import com.smartosc.training.utils.AuthenEnum;
import com.smartosc.training.utils.RoleEnum;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        List<UserDTO> list = new ArrayList<>(); // khai báo một list rỗng để chứa
        for (User user : users) {
            UserDTO userRespone = modelMapper.map(user, UserDTO.class);
            list.add(userRespone); // add data vào list
        }
        log.info("Get all user success");
        return list;
    }

    @Override
    public List<UserDTO> getAllUserWithSpec() {
        UserSpecifications userSpecifications = UserSpecifications.spec();
        List<User> userEntitys = userRepository.findAll(userSpecifications.build());
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User userEntity : userEntitys) {
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
            userDTO.setRoles(roleDTOS);
            List<CommentDTO> commentDTOS = new ArrayList<>();
            userDTO.setComments(commentDTOS);
            StatusOTDTO statusOTDTOS = new StatusOTDTO();
            userDTO.setStatusOT(statusOTDTOS);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) throws DuplicateKeyException {
        User user;
        Role role = new Role();
        List<Role> roles = new ArrayList<>();

        if (userRepository.findByUsername(userDTO.getUsername()) != null) { // check da ton tai hay chua, neu ton tai in ra messeages
            throw new DuplicateKeyException(userDTO.getUsername() + "da ton tai");
        }
        // chua ton tai thi thuc hien tiep o duoi
        user = modelMapper.map(userDTO, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setStatus(AuthenEnum.valueOfStatus(AuthenEnum.ACTIVATED));

        role.setRoleId(RoleEnum.valueOfStatus(RoleEnum.ROLE_USER));
        roles.add(role);
        userRepository.save(user);
        log.info("Create user success");
        return userDTO;
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
            userDTO.setRoles(roleDTOS);
            List<CommentDTO> commentDTOS = new ArrayList<>();
            userDTO.setComments(commentDTOS);
            StatusOTDTO statusOTDTOS = new StatusOTDTO();
            userDTO.setStatusOT(statusOTDTOS);
            return userDTO;
        } else {
            throw new NotFoundException("User " + name + "Not Found");
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
