package com.smartosc.training.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 01/07/2020 - 11:09 AM
 * @created_by Hieupv
 * @since 01/07/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private int status;
    private List<RoleDTO> roles;
    private List<CommentDTO> comments;
    private StatusOTDTO statusOT;
}
