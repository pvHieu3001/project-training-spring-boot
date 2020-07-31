package com.smartosc.training.service.impl;

import com.smartosc.training.dto.RoleDTO;
import com.smartosc.training.entities.Role;
import com.smartosc.training.repository.RoleRepository;
import com.smartosc.training.service.RoleService;
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

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDTO findByName(String username) {
        Role role = roleRepository.findByName(username);
        return modelMapper.map(role,RoleDTO.class);
    }

    @Override
    public List<RoleDTO> findByUsersUserName(String userName) {
        List<RoleDTO> roleResponses = new ArrayList<>();
        List<Role> roleList = roleRepository.findAllByAccountsUsername(userName);
        for (Role role : roleList ){
            RoleDTO roleResponse = modelMapper.map(role,RoleDTO.class);
            roleResponses.add(roleResponse);
        }
        return roleResponses;
    }
}
