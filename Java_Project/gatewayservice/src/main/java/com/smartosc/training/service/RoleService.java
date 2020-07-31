package com.smartosc.training.service;

import com.smartosc.training.dto.RoleDTO;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 1:54 PM
 * @created_by Huupd
 */
public interface RoleService {
    public RoleDTO findByName(String username);
    List<RoleDTO> findByUsersUserName(String userName);
}
