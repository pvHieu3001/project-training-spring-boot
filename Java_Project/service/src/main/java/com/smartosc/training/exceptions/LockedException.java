package com.smartosc.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 06/07/2020 - 11:40 AM
 * @created_by Huupd
 */

@ResponseStatus(HttpStatus.LOCKED)
public class LockedException extends RuntimeException {
    public LockedException(String message) {
        super(message);
    }
}