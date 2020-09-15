package com.smartosc.training.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdDto implements Serializable {
    private Long id;
}

