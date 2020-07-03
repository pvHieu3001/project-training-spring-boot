package com.smartosc.training.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Locale;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 3:39 PM
 * @created_by Huupd
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class APIResponse<T> {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

    public APIResponse(int status, String message) {
        Locale locale;
        this.status = Integer.toString(status);
        this.message = ;
    }

    public APIResponse(int status, String message, T data) {
        this.status = Integer.toString(status);
        this.message = message;
        this.data = data;
    }
}