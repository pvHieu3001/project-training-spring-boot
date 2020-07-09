package com.smartosc.training;

import com.smartosc.training.dto.CommentDTO;
import com.smartosc.training.dto.RoleDTO;
import com.smartosc.training.dto.StatusOTDTO;
import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.entities.Comment;
import com.smartosc.training.entities.Role;
import com.smartosc.training.entities.StatusOT;
import com.smartosc.training.entities.User;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.repositories.RoleRepository;
import com.smartosc.training.repositories.UserRepository;
import com.smartosc.training.repositories.specifications.UserSpecifications;
import com.smartosc.training.services.UserService;
import com.smartosc.training.services.impl.UserServicesImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 07/07/2020 - 3:15 PM
 * @created_by Huupd
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService = new UserServicesImpl();

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserSpecifications userSpecifications;

    private List<UserDTO> userDTOList;
    private List<User> userList;
    private List<RoleDTO> roleDTOList;
    private List<Role> roleList;
    private List<CommentDTO> commentDTOS;
    private List<Comment> comments;
    private StatusOTDTO statusOTDTO;
    private StatusOT statusOT;
    private UserDTO userDTO;
    private UserDTO userDTO2;
    private User user;
    private Role role;

    @Before
    public void init() {
        userDTOList = new ArrayList<>();
        roleDTOList = new ArrayList<>();
        commentDTOS = new ArrayList<>();
        statusOTDTO = new StatusOTDTO();
        userList = new ArrayList<>();
        role = new Role(1L,"ROLE_ADMIN");
        roleList = new ArrayList<>();
        roleList.add(role);
        comments = new ArrayList<>();
        statusOT = new StatusOT();
        user = new User(1L, "admin", "123", "admin@gmail.com", 1, roleList, comments, statusOT);
        userList.add(user);
        userDTO = new UserDTO(1L, "admin", "123", "admin@gmail.com", 1, roleDTOList, commentDTOS, statusOTDTO);
        userDTO2 = new UserDTO(2L, "admin2", "123456", "admin222@gmail.com", 1, roleDTOList, commentDTOS, statusOTDTO);
        userDTOList.add(userDTO);
        userDTOList.add(userDTO2);
        userSpecifications = userSpecifications.spec();
    }

    @Test
    public void testGetAllUserSuccess() {
        when(userRepository.findAll()).thenReturn(userList);
        List<UserDTO> userDTOS = userService.getAllUser();
        Assert.assertEquals(1, userDTOS.size());
    }

    @Test(expected = NotFoundException.class)
    public void testGetAllUserFail() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);
        List<UserDTO> userDTOS = userService.getAllUser();
        Assert.assertEquals(NotFoundException.class, userDTOS);
    }

    @Test
    public void testGetAllUserStatusTrueSuccess() {
        when(userRepository.findAllByStatus1()).thenReturn(userList);
        List<UserDTO> userDTOS = userService.getAllUserStatusTrue();
        Assert.assertEquals(1, userDTOS.size());
    }

    @Test(expected = NotFoundException.class)
    public void testGetAllUserStatusFail() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAllByStatus1()).thenReturn(users);
        List<UserDTO> userDTOS = userService.getAllUserStatusTrue();
        Assert.assertEquals(NotFoundException.class, userDTOS);
    }

    @Test
    public void testGetAllUserWithSpecSuccess() {
        when(userRepository.findAll(userSpecifications.all())).thenReturn(userList);
        List<UserDTO> userDTOS = userService.getAllUserWithSpec();
        Assert.assertEquals(1, userDTOS.size());
    }

    @Test(expected = NotFoundException.class)
    public void testGetAllUserStatusSpecFail() {
        List<User> users = new ArrayList<>();
        userSpecifications = userSpecifications.spec();
        when(userRepository.findAll(userSpecifications.all())).thenReturn(users);
        List<UserDTO> userDTOS = userService.getAllUserWithSpec();
        Assert.assertEquals(NotFoundException.class, userDTOS);
    }

    @Test
    public void testGetByIdSpecSuccess() {
        when(userRepository.findAll(userSpecifications.hasId(1L))).thenReturn(userList);
        List<UserDTO> userDTOS = userService.getUserById(1L);
        Assert.assertEquals(1, userList.size());
    }

    @Test
    public void testCreateUserSuccess(){
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        when(modelMapper.map(any(),any())).thenReturn(user).thenReturn(userDTO);
        when(bCryptPasswordEncoder.encode(any())).thenReturn(userDTO.getPassword());
        when(userRepository.save(any())).thenReturn(user);
        UserDTO userDTO5 = userService.createUser(userDTO);
        Assert.assertEquals(user.getPassword(),userDTO5.getPassword());
    }

    @Test(expected = DuplicateKeyException.class)
    public void testCreateUserFail(){
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        UserDTO userDTO5 = userService.createUser(userDTO);
        Assert.assertEquals(DuplicateKeyException.class,userDTO5);
    }

    @Test
    public void testFindUserByUserNameSuccess(){
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        UserDTO dto = userService.findUserByUserName("admin");
        Assert.assertEquals(user.getUsername(),dto.getUsername());

    }

    @Test(expected = NotFoundException.class)
    public void testFindUserByUserNameFail(){
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        UserDTO dto = userService.findUserByUserName("admin");
        Assert.assertEquals(NotFoundException.class,dto);
    }

    @Test
    public void testDeleteByIdSuccess(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(any(),any())).thenReturn(userDTO).thenReturn(user);
        UserDTO dto = userService.deleteUserById(1L);
        Assert.assertEquals(userDTO.getStatus(),dto.getStatus());
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteByIdFail(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        UserDTO dto = userService.deleteUserById(anyLong());
        Assert.assertEquals(NotFoundException.class,dto);
    }

    @Test
    public void testUpdateUserSuccess(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(modelMapper.map(any(),any())).thenReturn(user).thenReturn(userDTO);
        when(bCryptPasswordEncoder.encode(any())).thenReturn(userDTO.getPassword());
        when(userRepository.save(any())).thenReturn(user);
        userService.updateUser(1L,userDTO);
        Assert.assertEquals(userDTO.getPassword(),user.getPassword());
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateUserFail(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        userService.updateUser(1L,userDTO);
        Assert.assertEquals(NotFoundException.class,user.getPassword());
    }
}
