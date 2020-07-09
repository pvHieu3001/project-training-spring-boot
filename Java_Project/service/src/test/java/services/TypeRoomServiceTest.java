package services;

import com.smartosc.training.dto.TypeRoomDTO;
import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.entities.TypeRoom;
import com.smartosc.training.repositories.TypeRoomRepository;
import com.smartosc.training.repositories.specifications.TypeRoomSpecification;
import com.smartosc.training.services.TypeRoomService;
import com.smartosc.training.services.impl.TypeRoomServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        typeRoomDTO = new TypeRoomDTO(1L, "", "", new BigDecimal(2));
        typeRoom = new TypeRoom(1L, "", "", new BigDecimal(2), null, null);

        typeRoomDTOList = new ArrayList<>();
        typeRoomDTOList.add(typeRoomDTO);
        typeRoomList = new ArrayList<>();
        typeRoomList.add(typeRoom);
    }


    @Test
    public void findTypeRoomById() {
        Pageable pageable=PageRequest.of(0, 1);
        typeRoomPage = new PageImpl(typeRoomList, pageable, typeRoomList.size());
        Long id = new Long(1);

        when(typeRoomRepository.findAll(TypeRoomSpecification
                .spec()
                .typeRoomHasId(id)
                .build(), pageable)).thenReturn(typeRoomPage);
        when(modelMapper.map(any(), any())).thenReturn(typeRoomDTO);
        Page<TypeRoomDTO> userDTOS = typeRoomService.findTypeRoomById(id, pageable);
        Assert.assertEquals(1, userDTOS.getSize());
    }
}
