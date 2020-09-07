package com.smartosc.training.services.impl;

import com.smartosc.training.dto.RoleDTO;
import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.services.UserService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String password = user.getPassword();
        List<RoleDTO> roles = user.getRoleDTOS();
        List<GrantedAuthority> userGrants = new ArrayList<>();
        for(RoleDTO role : roles){
            GrantedAuthority userGrant = new SimpleGrantedAuthority(role.getName());
            userGrants.add(userGrant);
        }
        logger.info("user roles : {}",  roles);
        return new org.springframework.security.core.userdetails.User(username, password, userGrants);
    }
}
