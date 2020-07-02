package com.smartosc.training.securities;

import com.smartosc.training.services.impl.RoleServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 1:53 PM
 * @created_by Huupd
 */
public class JwtUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private RoleServicesImpl roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
