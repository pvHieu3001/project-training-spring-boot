package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartosc.training.controllers.HotelController;
import com.smartosc.training.dto.*;
import com.smartosc.training.entities.Hotel;
import com.smartosc.training.exceptions.CustomExceptionHandler;
import com.smartosc.training.services.HotelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 07/07/2020 - 10:23 AM
 */
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = HotelController.class)
@ActiveProfiles("test")
public class HotelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private HotelController hotelController;

    @Mock
    private HotelService hotelService;

    @Mock
    private MessageSource messageSource;

    private List<HotelDTO> hotelDTOList;
    private HotelDTO hotelDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<CommentDTO> commentDTOList = new ArrayList<>();
        List<TypeRoomDTO> typeRoomDTOList = new ArrayList<>();
        hotelDTOList = new ArrayList<>();
        hotelDTO = new HotelDTO(1L, "Hotel 1", "description 1", "hotel1.jpg", new BigDecimal("4.4"), new CityDTO(), commentDTOList, typeRoomDTOList);
        HotelDTO hotelDTO1 = new HotelDTO(2L, "Hotel 2", "description 1", "hotel1.jpg", new BigDecimal("4.4"), new CityDTO(), commentDTOList, typeRoomDTOList);
        HotelDTO hotelDTO2 = new HotelDTO(3L, "Hotel 3", "description 1", "hotel1.jpg", new BigDecimal("4.4"), new CityDTO(), commentDTOList, typeRoomDTOList);
        HotelDTO hotelDTO3 = new HotelDTO(4L, "Hotel 4", "description 1", "hotel1.jpg", new BigDecimal("4.4"), new CityDTO(), commentDTOList, typeRoomDTOList);
        HotelDTO hotelDTO4 = new HotelDTO(5L, "Hotel 5", "description 1", "hotel1.jpg", new BigDecimal("4.4"), new CityDTO(), commentDTOList, typeRoomDTOList);

        hotelDTOList.add(hotelDTO);
        hotelDTOList.add(hotelDTO1);
        hotelDTOList.add(hotelDTO2);
        hotelDTOList.add(hotelDTO3);
        hotelDTOList.add(hotelDTO4);

        mockMvc = MockMvcBuilders.standaloneSetup(hotelController).
                setControllerAdvice(CustomExceptionHandler.class).
                build();
    }

    @Test
    public void getAllHotels() throws Exception {
        when(hotelService.getAllHotels()).thenReturn(hotelDTOList);
        when(messageSource.getMessage(any(), any(), any())).thenReturn("message.getAll.success");
        this.mockMvc.perform(get("/api/hotels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()", is(hotelDTOList.size())));
    }

    @Test
    public void getCityWithHotels() throws Exception {
        final Long id = 1L;
        when(hotelService.getHotelByID(anyLong())).thenReturn(hotelDTOList.get(0));
        when(messageSource.getMessage(any(), any(), any())).thenReturn("message.getById.success");
        this.mockMvc.perform(get("/api/hotels/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is(hotelDTOList.get(0).getName())));
    }

    @Test
    public void createNew() throws Exception {
        when(hotelService.createNew(any(HotelDTO.class))).thenReturn(hotelDTO);
        when(messageSource.getMessage(any(), any(), any())).thenReturn("message.create.success");
        this.mockMvc.perform(post("/api/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(hotelDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is(hotelDTO.getName())));
    }

    @Test
    public void updateInformation() throws Exception {
        when(hotelService.updateHotel(any(HotelDTO.class))).thenReturn(hotelDTO);
        when(messageSource.getMessage(any(), any(), any())).thenReturn("message.update.success");
        this.mockMvc.perform(put("/api/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(hotelDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is(hotelDTO.getName())));
    }

    @Test
    public void deleteHotelById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                delete("/api/hotels/{id}", anyLong())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void geHotelsByName() {
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
