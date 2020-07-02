package com.smartosc.training.dto.request;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 01/07/2020 - 11:09 AM
 * @created_by Hieupv
 * @since 01/07/2020
 */
public class UserRequest {
    private Long id;
    private String username;
    private String password;
    private String email;
    private int status;
    private List<RoleRequest> roles;
    private List<CommentRequest> comments;
    private StatusOTRequest statusOT;
}
