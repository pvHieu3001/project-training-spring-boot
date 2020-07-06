package com.smartosc.training.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 3:37 PM
 * @created_by Huupd
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

}