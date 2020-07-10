package services;

import com.smartosc.training.dto.TypeRoomDTO;
import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.entities.TypeRoom;
import com.smartosc.training.repositories.TypeRoomRepository;
import com.smartosc.training.repositories.specifications.TypeRoomSpecification;
import com.smartosc.training.services.TypeRoomService;
import com.smartosc.training.services.impl.TypeRoomServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.smartosc.training.exceptions.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 09/07/2020 - 3:41 PM
 * @created_by Hieupv
 * @since 09/07/2020
 */
@RunWith(MockitoJUnitRunner.class)
public class TypeRoomServiceTest {
    @InjectMocks
    private TypeRoomService typeRoomService = new TypeRoomServiceImpl();

    @Mock
    private TypeRoomRepository typeRoomRepository;
    @Mock
    private ModelMapper modelMapper;

    private List<TypeRoomDTO> typeRoomDTOList;
    private List<TypeRoom> typeRoomList;
    private Page<TypeRoom> typeRoomPage;
    private TypeRoomDTO typeRoomDTO;
    private TypeRoom typeRoom;

    @Before
    public void init() {
        typeRoomDTO = new TypeRoomDTO(1L, "hà aln", "a", new BigDecimal(2));
        typeRoom = new TypeRoom(1L, "hà aln", "a", new BigDecimal(2), null, null);

        typeRoomDTOList = new ArrayList<>();
        typeRoomDTOList.add(typeRoomDTO);
        typeRoomList = new ArrayList<>();
        typeRoomList.add(typeRoom);
    }


    @Test
    @DisplayName("searchTypeRoom when input true data then return data")
    public void findTypeRoomById() {
        Pageable pageable=PageRequest.of(0, 1);
        typeRoomPage = new PageImpl(typeRoomList, pageable, typeRoomList.size());
        Long id = new Long(1);
        when(modelMapper.map(any(), any())).thenReturn(typeRoomDTO);
        when(typeRoomRepository.findAll(Mockito.any(Specification.class), Mockito.eq(pageable))).thenReturn(typeRoomPage);

        Page<TypeRoomDTO> userDTOS = typeRoomService.findTypeRoomById(id, pageable);
        assertEquals(typeRoomList.size(), userDTOS.getContent().size(), "should return 2 element");
    }

    @Test
    @DisplayName("Update Success")
    public void updateTypeRoom() throws NotFoundException {
        when(typeRoomRepository.findById(any())).thenReturn(Optional.ofNullable(typeRoom));
        when(typeRoomRepository.save(Mockito.any(TypeRoom.class))).thenReturn(typeRoom);
        when(modelMapper.map(any(), any())).thenReturn(typeRoomDTO);
        TypeRoomDTO userDTO = typeRoomService.updateTypeRoom(typeRoomDTO);
        assertEquals(userDTO.getId(), typeRoom.getId(), "should return similar id");
    }

    @Test
    @DisplayName("Update when TypeRoom not found")
    public void updateTypeRoomNotFound() {
        when(typeRoomRepository.findById(any())).thenReturn(Optional.empty());
        Exception exception = Assertions.assertThrows(NotFoundException.class, () ->
                typeRoomService.updateTypeRoom(typeRoomDTO));

        String expectedMessage = "error.msg.type_room.data.notfound";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    @DisplayName("Create Success")
    public void createTypeRoom() throws NotFoundException {
        when(modelMapper.map(any(), any())).thenReturn(typeRoom).thenReturn(typeRoomDTO);
        when(typeRoomRepository.save(Mockito.any(TypeRoom.class))).thenReturn(typeRoom);
        TypeRoomDTO result = typeRoomService.createTypeRoom(typeRoomDTO);
        assertEquals(result.getName(), typeRoom.getName(), "should return similar name");
    }
}
