package com.smartosc.training.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 2:03 PM
 * @created_by Huupd
 * @updated by Thanhttt
 */
@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String content;
    private Integer rate;
    private UserDTO user;
    private HotelDTO hotel;
}
