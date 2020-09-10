package com.smartosc.training.controllers;

import java.security.Principal;

import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping(value = "/users")
public class UserController {
  
  @Autowired
  private UserService userService;
  
  @GetMapping("/authenticate")
  @ResponseBody
  public ResponseEntity<Principal> user(Principal user) {
   return ResponseEntity.<Principal>ok(user);
  }
  
  @GetMapping
  @ResponseBody
  public ResponseEntity<UserDTO> getUserByUsername(Principal principal) throws NotFoundException {
    UserDTO user = userService.findUserByUserName(principal.getName());
    return ResponseEntity.ok(user);
  }

  @GetMapping("/demo")
  public String demo(){
    return "demo";
  }
}
