package com.smartosc.training.dto;


import lombok.*;

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
public class RoleDTO {
    private Long id;
    private String name;
    List<UserDTO> userDTOS;

    public RoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
