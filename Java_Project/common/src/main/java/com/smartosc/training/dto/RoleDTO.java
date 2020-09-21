package com.smartosc.training.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 1:58 PM
 * @created_by Huupd
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO extends IdDto{
    private String name;
    List<UserDTO> userDTOS;



}
