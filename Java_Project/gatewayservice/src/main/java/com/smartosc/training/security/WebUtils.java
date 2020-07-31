package com.smartosc.training.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 1:46 PM
 * @created_by Huupd
 */
public class WebUtils {
    private WebUtils() {}

    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();

        sb.append("UserName:").append(user.getUsername());

        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }
}
