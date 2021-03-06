package com.smartosc.training.services.impl;

import com.smartosc.training.dto.CommentDTO;
import com.smartosc.training.dto.RoleDTO;
import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.entities.Role;
import com.smartosc.training.entities.User;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.repositories.UserRepository;
import com.smartosc.training.repositories.specifications.UserSpecifications;
import com.smartosc.training.services.UserService;
import com.smartosc.training.utils.AuthenEnum;
import com.smartosc.training.utils.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.domain.Specification;
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

    @Autowired
    private UserSpecifications userSpecifications;

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> list = new ArrayList<>(); // khai báo một list rỗng để chứa
        if (users.size() > 0) {
            for (User userEntity : users) {
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

                list.add(userDTO);
            }
            log.info("Get all user success");
            return list;
        }
        log.info("Get all user fail,not found user");
        throw new NotFoundException("Not found user");
    }

    @Override
    public List<UserDTO> getAllUserStatusTrue() {
        List<User> users = userRepository.findAllByStatus1();
        List<UserDTO> list = new ArrayList<>(); // khai báo một list rỗng để chứa
        if (users.size() > 0) {
            for (User userEntity : users) {
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

                list.add(userDTO);
            }
            log.info("Get all user success");
            return list;
        }
        throw new NotFoundException("Not found user with status = 1");
    }

    @Override
    public List<UserDTO> getAllUserWithSpec() {
        List<UserDTO> list = new ArrayList<>(); // khai báo một list rỗng để chứa
        List<User> userEntitys = userRepository.findAll(userSpecifications.spec().all());
        if (userEntitys.size() > 0) {
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
                userDTO.setRoleDTOS(roleDTOS);
                List<CommentDTO> commentDTOS = new ArrayList<>();
                userDTO.setCommentDTOS(commentDTOS);

                list.add(userDTO);
            }
            log.info("Get all user success");
            return list;
        }
        throw new NotFoundException("Not found user with spec()");
    }

    @Override
    public List<UserDTO> getUserById(Long id) {
        List<UserDTO> list = new ArrayList<>();
        List<User> userEntitys = userRepository.findAll(Specification.where(userSpecifications.spec().hasId(id)));
        if (userEntitys.size() == 0) {
            log.info("get id fail,id not found");
            throw new NotFoundException("Id not found");
        }
        for (User user : userEntitys) {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            list.add(userDTO);
        }
        log.info("Get  user by id  success");
        return list;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) throws DuplicateKeyException {
        User user;
        Role role = new Role();
        List<Role> roles = new ArrayList<>();

        if (userRepository.findByUsername(userDTO.getUsername())
                != null) { // check da ton tai hay chua, neu ton tai in ra messeages
            log.info("user exist");
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
    public UserDTO findUserByUserName(String name) {
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

    @Override
    public UserDTO deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            optionalUser.get().setStatus(0);
            User user = userRepository.save(optionalUser.get());
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return userDTO;
        } else {
            log.info("Not found user!");
            throw new NotFoundException("Khong tim thay id " + id);
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {

            throw new NotFoundException("Tai khoan khong ton tai");
        }
        User user;
        user = modelMapper.map(userDTO, User.class);
        user.setId(id);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }
}
