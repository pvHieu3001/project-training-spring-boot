import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartosc.training.controllers.UserController;
import com.smartosc.training.dto.CommentDTO;
import com.smartosc.training.dto.RoleDTO;
import com.smartosc.training.dto.StatusOTDTO;
import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.entities.User;
import com.smartosc.training.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

/**
 * Fresher-Training
 *
 * @author Tung lam
 * @created_at 07/07/2020 - 17:46
 * @created_by Tung lam
 * @since 07/07/2020
 */
@ExtendWith(SpringExtension.class)
public class UserControllerTest {
    private MockMvc mockMvc;

    private String url;
    private String url1;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private ObjectMapper objectMapper;

    private List<UserDTO> userDTOList;

    private UserDTO userDTO;

    private RoleDTO roleDTO;

    private List<RoleDTO> roleDTOList;

    private List<CommentDTO> commentDTOList;

    private StatusOTDTO statusOTDTO;

    private List<User> userList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        url = "/api/user";
        url1 = "/api/user/{id}";

        roleDTOList = new ArrayList<>();
        roleDTO = new RoleDTO(1L, "Admin");
        roleDTOList.add(roleDTO);

        userDTOList = new ArrayList<>();
        userDTO = new UserDTO(1L, "lamchuot", "12345", "lamchuot0@gmail.com", 1, roleDTOList, commentDTOList, statusOTDTO);
        userDTOList.add(userDTO);


        commentDTOList = new ArrayList<>();

        statusOTDTO = new StatusOTDTO();
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }


    // test get all user
    @Test
    public void TestGetAllUser() throws Exception {
        lenient().when(userService.getAllUser()).thenReturn(userDTOList);
        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()", is(userDTOList.size())));
    }

    // test create user
    @Test
    public void TestCreateUser() throws Exception {
        lenient().when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);
        this.mockMvc.perform(post(url, userDTO).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log());
    }

    // test update user
    @Test
    public void TestUpdateUser() throws Exception {
        lenient().when(userService.updateUser(anyLong(), any(UserDTO.class))).thenReturn(userDTO);
        this.mockMvc.perform(put(url1, userDTO.getId(), userDTO)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log());
    }

    // test delete userById
    @Test
    public void TestDeleteUserById() throws Exception {
        lenient().when(userService.deleteUserById(any())).thenReturn(userDTO);
        this.mockMvc.perform(delete(url1, userDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log());
    }

    // test get userById
    @Test
    public void TestGetUserById() throws Exception {
        lenient().when(userService.getUserById(any())).thenReturn(userDTOList);
        this.mockMvc.perform(get(url, userDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log());
    }

    // test getUserByUsername
    @Test
    public void TestFindUserByUserName() throws Exception {
        lenient().when(userService.findUserByUserName(any())).thenReturn(userDTO);
        this.mockMvc.perform(get(url, userDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log());
    }

    // test getAllUserWithSpec
    @Test
    public void GetAllUserWithSpec() throws Exception {
        lenient().when(userService.getAllUserWithSpec()).thenReturn(userDTOList);
        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void GetAllUserStatusTrue() throws Exception {
        lenient().when(userService.getAllUserStatusTrue()).thenReturn(userDTOList);
        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log());
    }
}
