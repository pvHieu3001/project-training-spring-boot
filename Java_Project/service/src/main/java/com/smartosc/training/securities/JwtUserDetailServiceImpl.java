package com.smartosc.training.securities;

import com.smartosc.training.dto.response.RoleResponse;
import com.smartosc.training.dto.response.UserRespone;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.services.RoleService;
import com.smartosc.training.services.UserService;
import com.smartosc.training.services.impl.RoleServicesImpl;
import com.smartosc.training.services.impl.UserServicesImpl;
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

    private RoleService roleService = new RoleServicesImpl();

    private UserService userService = new UserServicesImpl();


    @Override
    public UserDetails loadUserByUsername(String userName) {
        UserRespone users = userService.findUserByUserName(userName);
        if (users == null || users.getStatus() == 0) {
            throw new NotFoundException("User " + userName + " was not found in the database");
        }
        List<GrantedAuthority> grantList = new ArrayList<>();
        List<RoleResponse> roleNames = this.roleService.findByUsersUserName(userName);
        if (roleNames != null) {
            for (RoleResponse role : roleNames) {
                grantList.add(new SimpleGrantedAuthority(role.getName()));
            }
        }

        return new User(users.getUsername(), users.getPassword(), grantList);
    }
}
