package com.smartosc.training.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderDB extends BaseEntity<Long> implements Serializable {
    @Column
    private String code;
}
