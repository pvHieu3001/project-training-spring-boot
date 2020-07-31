package com.smartosc.training.security;

import com.smartosc.training.dto.RoleDTO;
import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.service.RoleService;
import com.smartosc.training.service.UserService;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 1:53 PM
 * @created_by Huupd
 */
@Component
public class JwtUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String userName) {
        UserDTO users = userService.findUserByUserName(userName);
        if (users == null ) {
            throw new NotFoundException("Authenticate.locked.account");
        }
        if ( users.getStatus() == 0){
            throw new LockedException("User " + userName + " was looked");

        }
        List<GrantedAuthority> grantList = new ArrayList<>();
        List<RoleDTO> roleNames = this.roleService.findByUsersUserName(userName);
        if (roleNames != null) {
            for (RoleDTO role : roleNames) {
                grantList.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return new User(users.getUsername(), users.getPassword(), grantList);
    }
}
