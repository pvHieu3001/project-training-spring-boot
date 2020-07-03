package com.smartosc.training.controllers;

import com.smartosc.training.dto.request.JwtRequest;
import com.smartosc.training.dto.response.APIResponse;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.securities.JWTUtils;
import com.smartosc.training.securities.JwtUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 3:29 PM
 * @created_by Huupd
 */
@RestController
@RequestMapping
public class ApiSecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtTokenUtil;

    @Autowired
    private JwtUserDetailServiceImpl userDetailsService;

    @PostMapping(value = "/api/authenticate")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody @Valid JwtRequest authenticationRequest) throws NotFoundException {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        if (!userDetails.isAccountNonLocked()) {
            return new ResponseEntity(new APIResponse(HttpStatus.OK.value(), "this account has been locked"), HttpStatus.BAD_REQUEST);
        }
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}