package com.smartosc.training.controllers;

import com.smartosc.training.dto.APIResponse;
import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

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
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllUser(Locale locale) {
        List<UserDTO> userDTOS = userService.getAllUser();

        APIResponse<List<UserDTO>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(messageSource.getMessage("message.getAll.user.success", null, locale));
        apiResponse.setData(userDTOS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping({"/spec"})
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllUserWithSpec(Locale locale) {
        List<UserDTO> userDTOS = userService.getAllUserWithSpec();

        APIResponse<List<UserDTO>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(messageSource.getMessage("message.getAll.user.success", null, locale));
        apiResponse.setData(userDTOS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllUserStatusTrue(Locale locale) {
        List<UserDTO> userDTOS = userService.getAllUserStatusTrue();

        APIResponse<List<UserDTO>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(messageSource.getMessage("message.getAll.user.success", null, locale));
        apiResponse.setData(userDTOS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/spec/{id}")
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllUserWithSpecId(@PathVariable(value = "id") Long id, Locale locale) {
        List<UserDTO> userDTOS = userService.getUserById(id);

        APIResponse<List<UserDTO>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(messageSource.getMessage("Message.status.ok", null, locale));
        apiResponse.setData(userDTOS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<APIResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO, Locale locale) {
        UserDTO request = userService.createUser(userDTO);

        APIResponse<UserDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(messageSource.getMessage("message.create.user.success", null, locale));
        apiResponse.setData(request);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<UserDTO>> updateUser(@RequestBody @Valid UserDTO userDTO, @PathVariable(value = "id") Long id, Locale locale) {
        UserDTO userDTO1 = userService.updateUser(id, userDTO);
        APIResponse<UserDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(messageSource.getMessage("message.create.update.success", null, locale));
        apiResponse.setData(userDTO1);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<UserDTO>> deleteUserById(@PathVariable(value = "id") Long id, Locale locale) {
        UserDTO userDTO = userService.deleteUserById(id);
        APIResponse<UserDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(messageSource.getMessage("message.create.delete.success", null, locale));
        apiResponse.setData(userDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
