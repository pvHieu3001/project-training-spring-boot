package com.smartosc.training.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 3:39 PM
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