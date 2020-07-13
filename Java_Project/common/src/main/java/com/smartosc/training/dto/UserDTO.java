package com.smartosc.training.dto;

import com.smartosc.training.validations.anotation.EmailContraint;
import com.smartosc.training.validations.anotation.PasswordContraint;
import com.smartosc.training.validations.anotation.UserNameConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author Tung lam
 * @created_at 06/07/2020 - 10:41
 * @created_by Tung lam
 * @since 06/07/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @UserNameConstraint
    private String username;

    @PasswordContraint
    private String password;

    @EmailContraint
    private String email;

    private int status;
    private List<RoleDTO> roleDTOS;
    private List<CommentDTO> commentDTOS;
    private StatusOTDTO statusOTDTO;
}
