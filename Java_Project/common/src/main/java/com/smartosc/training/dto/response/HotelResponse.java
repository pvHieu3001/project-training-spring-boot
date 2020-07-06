package com.smartosc.training.dto.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 2:30 PM
 */
public class HotelResponse {
    private Long id;
    private String name;
    private String description;
    private String imgUrl;
    private BigDecimal totalRate;
    private CityResponse city;
    private List<TypeRoomRespone> typeRooms;
    private List<ComentResponse> comments;
}
