package com.smartosc.training.dto.request;

import com.smartosc.training.entities.DetailTypeRoom;
import com.smartosc.training.entities.TypeRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 02/07/2020 - 10:43 AM
 * @created_by Hieupv
 * @since 02/07/2020
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetailTypeRoomRequest {
    private Long id;
    private String description;
    private Integer maxPerson;
    private Integer defaultPerson;
    private BigDecimal price;
    private BigDecimal additionalPrice;
    private Integer roomAvailable;

    public DetailTypeRoom toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        DetailTypeRoom detailTypeRoom = new DetailTypeRoom();
        modelMapper.map(this, detailTypeRoom);
        return  detailTypeRoom;
    }

    public DetailTypeRoomRequest toDTO(DetailTypeRoom detailTypeRoom){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(detailTypeRoom, DetailTypeRoomRequest.class);
    }
}
