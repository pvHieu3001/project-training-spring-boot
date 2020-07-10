package com.smartosc.training.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 10/07/2020 - 3:53 PM
 * @created_by Hieupv
 * @since 10/07/2020
 */
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiLog {
    @Id
    @GeneratedValue
    private Long id;
    private Date calledTime;
    @Column(columnDefinition = "TEXT")
    private String data;
    @Column(columnDefinition = "TEXT")
    private String errorMessage;
    private int retryNum;
}
