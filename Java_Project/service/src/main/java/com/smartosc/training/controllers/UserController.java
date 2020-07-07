package com.smartosc.training.controllers;

import com.smartosc.training.dto.APIResponse;
import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 10:48 AM
 * @created_by Namtt
 * @since 02/07/2020
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllUser() {
        List<UserDTO> userRespones = userService.getAllUser();

        APIResponse<List<UserDTO>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.toString());
        apiResponse.setMessage("get all thanh cong em oi");
        apiResponse.setData(userRespones);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/status")
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllUserStatusTrue() {
        List<UserDTO> userRespones = userService.getAllUserStatusTrue();

        APIResponse<List<UserDTO>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.toString());
        apiResponse.setMessage("Messsage.status.ok");
        apiResponse.setData(userRespones);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/spec")
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllUserWithSpec() {
        List<UserDTO> userRespones = userService.getAllUserWithSpec();

        APIResponse<List<UserDTO>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.toString());
        apiResponse.setMessage("get all thanh cong em oi");
        apiResponse.setData(userRespones);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/spec/{id}")
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllUserWithSpecId(@PathVariable(value = "id") Long id) {
        List<UserDTO> userRespones = userService.getUserById(id);

        APIResponse<List<UserDTO>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.toString());
        apiResponse.setMessage("NotFound.user.id");
        apiResponse.setData(userRespones);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<APIResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO request = userService.createUser(userDTO);

        APIResponse<UserDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.toString());
        apiResponse.setMessage("create user success");
        apiResponse.setData(request);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<UserDTO>> updateUser(@RequestBody @Valid UserDTO userDTO, @PathVariable(value = "id") Long id) throws NotFoundException {
        UserDTO userDTO1 = userService.updateUser(id, userDTO);
        APIResponse<UserDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.toString());
        apiResponse.setMessage("Update user success");
        apiResponse.setData(userDTO1);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<UserDTO>> deleteUserById(@PathVariable(value = "id") Long id) throws NotFoundException {
        UserDTO userDTO = userService.deleteUserById(id);
        APIResponse<UserDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.toString());
        apiResponse.setMessage("delete success");
        apiResponse.setData(userDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
