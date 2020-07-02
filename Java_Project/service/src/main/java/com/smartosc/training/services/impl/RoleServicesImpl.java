package com.smartosc.training.services.impl;

import com.smartosc.training.dto.request.RoleRequest;
import com.smartosc.training.dto.response.RoleResponse;
import com.smartosc.training.entities.Role;
import com.smartosc.training.repositories.RoleRepository;
import com.smartosc.training.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 2:01 PM
 * @created_by Huupd
 */
@Service
public class RoleServicesImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    private ModelMapper modelMapper;

    @Override
    public RoleResponse findByName(String username) {
        Role role = roleRepository.findByName(username);
        return modelMapper.map(role,RoleResponse.class);
    }

    @Override
    public List<RoleResponse> findByUsersUserName(String userName) {
        List<RoleResponse> roleResponses = new ArrayList<>();
        List<Role> roleList = roleRepository.findByUsersUserName(userName);
        for (Role role : roleList ){
            RoleResponse roleResponse = modelMapper.map(role,RoleResponse.class);
            roleResponses.add(roleResponse);
        }
        return roleResponses;
    }
}
