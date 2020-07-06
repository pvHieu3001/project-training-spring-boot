package com.smartosc.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 3:18 PM
 * @created_by Huupd
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}