package com.smartosc.training.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 06/07/2020 - 10:21 AM
 * @created_by Huupd
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class APIResponse<T> {
    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

    public APIResponse(int status, String message) {
        this.status = Integer.toString(status);
        this.message = message;
    }

    public APIResponse(int status, String message, T data) {
        this.status = Integer.toString(status);
        this.message = message;
        this.data = data;
    }
}