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
public enum  RoleEnum {
    ROLE_USER(2L),ROLE_ADMIN(1L);

    private static final Map<RoleEnum, Long> BY_VALUE = new HashMap<>();

    static {
        for (RoleEnum status : values()) {
            BY_VALUE.put(status, status.value);
        }
    }

    private final Long value;

    private RoleEnum(Long value) {
        this.value = value;
    }

    public static Long valueOfStatus(RoleEnum status) {
        return BY_VALUE.get(status);
    }
}
