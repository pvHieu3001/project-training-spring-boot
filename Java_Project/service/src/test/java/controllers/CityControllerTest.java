package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartosc.training.controllers.CityController;
import com.smartosc.training.dto.CentralDTO;
import com.smartosc.training.dto.CityDTO;
import com.smartosc.training.dto.HotelDTO;
import com.smartosc.training.exceptions.CustomExceptionHandler;
import com.smartosc.training.services.CityService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 07/07/2020 - 2:49 PM
 */
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = CityController.class)
@ActiveProfiles("test")
public class CityControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private CityController cityController;

    @Mock
    private CityService cityService;

    @Mock
    private MessageSource messageSource;

    private List<CityDTO> cityDTOList;
    private CityDTO cityDTO1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        cityDTOList = new ArrayList<>();
        List<HotelDTO> hotelDTOList = new ArrayList<>();
        CentralDTO centralDTO = new CentralDTO();

        cityDTO1 = new CityDTO(1L, "City 1", "city1.jpg", hotelDTOList, centralDTO);
        CityDTO cityDTO2 = new CityDTO(2L, "City 2", "city2.jpg", hotelDTOList, centralDTO);
        CityDTO cityDTO3 = new CityDTO(3L, "City 3", "city3.jpg", hotelDTOList, centralDTO);
        CityDTO cityDTO4 = new CityDTO(4L, "City 4", "city3.jpg", hotelDTOList, centralDTO);
        CityDTO cityDTO5 = new CityDTO(5L, "City 5", "city3.jpg", hotelDTOList, centralDTO);

        cityDTOList.add(cityDTO1);
        cityDTOList.add(cityDTO2);
        cityDTOList.add(cityDTO3);
        cityDTOList.add(cityDTO4);
        cityDTOList.add(cityDTO5);
        mockMvc = MockMvcBuilders.standaloneSetup(cityController).
                setControllerAdvice(CustomExceptionHandler.class).
                build();
    }

    @Test
    public void getAllCities() throws Exception {

        when(cityService.getAllCities()).thenReturn(cityDTOList);
        when(messageSource.getMessage(any(), any(), any())).thenReturn("message.getAll.success");
        this.mockMvc.perform(get("/api/cities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()", is(cityDTOList.size())));
    }

    @Test
    public void getCityWithHotels() throws Exception {
        final Long id = 1L;
        when(cityService.getCityWithHotels(anyLong())).thenReturn(cityDTOList.get(0));
        when(messageSource.getMessage(any(), any(), any())).thenReturn("message.getById.success");
        this.mockMvc.perform(get("/api/cities/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is(cityDTOList.get(0).getName())));
    }

    @Test
    public void createNew() throws Exception {
        when(cityService.createNew(any(CityDTO.class))).thenReturn(cityDTO1);
        when(messageSource.getMessage(any(), any(), any())).thenReturn("message.create.success");
        this.mockMvc.perform(post("/api/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(cityDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is(cityDTO1.getName())));
    }

    @Test
    public void updateInformation() throws Exception {
        when(cityService.updateInformation(any(CityDTO.class))).thenReturn(cityDTO1);
        when(messageSource.getMessage(any(), any(), any())).thenReturn("message.update.success");
        this.mockMvc.perform(put("/api/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(cityDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is(cityDTO1.getName())));
    }

    @Test
    public void getWithPage() throws Exception {
        int pageNumber = 0;
        int pageSize = 1;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CityDTO> cityDTOPage = new PageImpl<>(Collections.singletonList(cityDTO1));
        when(cityService.getCitiesWithPagination(pageable)).thenReturn(cityDTOPage);
        when(messageSource.getMessage(any(), any(), any())).thenReturn("message.getAll.success");
        mockMvc.perform(get("/api/cities/menu")
                .param("page", "0")
                .param("size", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(cityDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content[0].name", is(cityDTO1.getName())));
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
