package com.smartosc.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 06/07/2020 - 3:39 PM
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullPointerException extends RuntimeException {
    public NullPointerException(String message) {
        super(message);
    }

}
