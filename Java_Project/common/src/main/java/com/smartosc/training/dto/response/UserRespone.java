package com.smartosc.training.dto.response;

import com.smartosc.training.dto.request.CommentRequest;
import com.smartosc.training.dto.request.RoleRequest;
import com.smartosc.training.dto.request.StatusOTRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 01/07/2020 - 11:10 AM
 * @created_by Hieupv
 * @since 01/07/2020
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRespone {
    private Long id;
    private String username;
    private String password;
    private String email;
    private int status;
    private List<RoleResponse> roles;
    private List<ComentResponse> comments;
    private StatusORResponse statusOT;
}
