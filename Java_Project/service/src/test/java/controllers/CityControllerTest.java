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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        cityDTOList = new ArrayList<>();
        List<HotelDTO> hotelDTOList = new ArrayList<>();
        CentralDTO centralDTO = new CentralDTO();

        CityDTO cityDTO1 = new CityDTO(1L, "City 1", "city1.jpg", hotelDTOList, centralDTO);
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
        when(messageSource.getMessage("message.getAll.success", null, Locale.US)).thenReturn("message.getAll.success");
        this.mockMvc.perform(get("/api/cities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()", is(cityDTOList.size())));
    }

    @Test
    public void getCityWithHotels() throws Exception {
        final Long id = 1L;
        when(cityService.getCityWithHotels(id)).thenReturn(cityDTOList.get(0));
        when(messageSource.getMessage(any(), any(), any())).thenReturn("message.getById.success");
        this.mockMvc.perform(get("/api/cities/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is(cityDTOList.get(0).getName())));
    }

    @Test
    public void getWithPage() throws Exception {
        when(messageSource.getMessage(any(), any(), any())).thenReturn("message.getAll.success");
        mockMvc.perform(get("/api/cities/page")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk());
    }
}
