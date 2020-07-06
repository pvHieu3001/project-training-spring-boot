package com.smartosc.training.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Fresher-Training
 *
 * @author Tung lam
 * @created_at 02/07/2020 - 15:54
 * @created_by Tung lam
 * @since 02/07/2020
 */
public enum AuthenEnum {
    DEACTIVATED(1), ACTIVATED(2);

    private static final Map<AuthenEnum, Integer> BY_VALUE = new HashMap<>();

    static {
        for (AuthenEnum status : values()) {
            BY_VALUE.put(status, status.value);
        }
    }

    private final Integer value;

    AuthenEnum(Integer value) {
        this.value = value;
    }

    public static Integer valueOfStatus(AuthenEnum status) {
        return BY_VALUE.get(status);
    }
}
