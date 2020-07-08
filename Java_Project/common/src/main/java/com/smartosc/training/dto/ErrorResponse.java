package com.smartosc.training.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 3:13 PM
 * @created_by Huupd
 */
@Data
@Getter
@Setter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private Map<String, String> messages;
    private String error;
}