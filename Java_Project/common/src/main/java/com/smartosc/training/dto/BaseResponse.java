/*
 * Copyright (C) 2020 Viettel Digital Services. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.smartosc.training.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author VuHQ
 * @Since 6/2/2020
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BaseResponse {
    private String errorCode;
    private String errorDesc;
    @JsonIgnore
    private String errorData;
}
