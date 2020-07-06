package com.smartosc.training.controllers;

import com.smartosc.training.dto.request.UserRequest;
import com.smartosc.training.dto.response.APIResponse;
import com.smartosc.training.dto.response.UserRespone;
import com.smartosc.training.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public ResponseEntity<APIResponse<List<UserRespone>>> getAllUser() {
        List<UserRespone> userRespones = userService.getAllUser();

        APIResponse<List<UserRespone>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.toString());
        apiResponse.setMessage("get all thanh cong em oi");
        apiResponse.setData(userRespones);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<APIResponse<UserRequest>> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserRequest request = userService.createUser(userRequest);

        APIResponse<UserRequest> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.toString());
        apiResponse.setMessage("create user success");
        apiResponse.setData(request);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<UserRequest>> updateUser(@RequestBody @Valid UserRequest userRequest, @PathVariable(value = "id") Long id) throws NotFoundException {
        UserRequest userRequest1 = userService.updateUser(id, userRequest);
        APIResponse<UserRequest> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.toString());
        apiResponse.setMessage("Update user success");
        apiResponse.setData(userRequest1);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<UserRespone>> deleteUserById(@PathVariable(value = "id") Long id) throws NotFoundException {
        UserRespone userRespone = userService.deleteUserById(id);
        APIResponse<UserRespone> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.toString());
        apiResponse.setMessage("delete success");
        apiResponse.setData(userRespone);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
