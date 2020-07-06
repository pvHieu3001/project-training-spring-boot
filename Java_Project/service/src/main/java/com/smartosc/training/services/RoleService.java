package com.smartosc.training.services;

import com.smartosc.training.dto.response.RoleResponse;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 1:54 PM
 * @created_by Huupd
 */
public interface RoleService {
    public RoleResponse findByName(String username);
    List<RoleResponse> findByUsersUserName(String userName);
}
