
import com.smartosc.training.dto.CentralDTO;
import com.smartosc.training.dto.CityDTO;
import com.smartosc.training.dto.HotelDTO;
import com.smartosc.training.entities.*;
import com.smartosc.training.exceptions.DuplicateException;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.repositories.CityRepository;
import com.smartosc.training.services.CityService;
import com.smartosc.training.services.impl.CityServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 07/07/2020 - 2:49 PM
 */
@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @InjectMocks
    private CityService cityService = new CityServiceImpl();

    @Mock
    private CityRepository cityRepository;

    private List<CityDTO> cityDTOList;
    private List<City> cityList;
    private CityDTO cityDTO;
    private Optional<City> city;
    private City city1,update;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cityList = new ArrayList<>();
        List<Comment> commentList = new ArrayList<>();
        Comment comment = new Comment(2L, "Comment1", 123, new User(), new Hotel());
        commentList.add(comment);

        List<TypeRoom> typeRoomList = new ArrayList<>();
        TypeRoom typeRoom = new TypeRoom(2L, "Comment1", "", new BigDecimal("34"), new Hotel(), new ArrayList<>());
        typeRoomList.add(typeRoom);
        List<Hotel> hotelList = new ArrayList<>();
        Hotel hotel = new Hotel(1L, "Hotel 1", "description 1", "hotel1.jpg",
                new BigDecimal("4.4"), new City(), typeRoomList, commentList);
        hotelList.add(hotel);

        Central central = new Central(1L, "Hotel 1","Hotel 1", cityList);

        city = Optional.of(new City(1L, "City 1", "city1.jpg", hotelList, central));
        City city2 = new City(2L, "City 2", "city2.jpg", hotelList, central);
        City city3 = new City(3L, "City 3", "city3.jpg", hotelList, central);
        City city4 = new City(4L, "City 4", "city3.jpg", hotelList, central);
        City city5 = new City(5L, "City 5", "city3.jpg", hotelList, central);

        city1 = new City(1L, "City 1", "city1.jpg", hotelList, central);
        update = new City(1L, "Ahihi", "city1.jpg", hotelList, central);

        cityList.add(city2);
        cityList.add(city3);
        cityList.add(city4);
        cityList.add(city5);
        cityList.add(update);

        List<HotelDTO> hotelDTOList = new ArrayList<>();
        HotelDTO hotelDTO = new HotelDTO(1L, "Hotel 1", "description 1", "hotel1.jpg",
                new BigDecimal("4.4"), new CityDTO(), new ArrayList<>(), new ArrayList<>());
        hotelDTOList.add(hotelDTO);

        cityDTOList = new ArrayList<>();
        cityDTO = new CityDTO(1L, "City 1", "city2.jpg", hotelDTOList, new CentralDTO());
        cityDTOList.add(cityDTO);
    }

    @Test
    public void getAllCities() {
        when(cityRepository.findAll()).thenReturn(cityList);
        cityDTOList = cityService.getAllCities();
        assertEquals(cityList.size(), cityDTOList.size());
    }

    @Test
    public void getCityWithHotels(){
        when(cityRepository.findById(1L)).thenReturn(city);

        cityDTO = cityService.getCityWithHotels(1L);
        assertEquals(cityDTO.getName(), city.get().getName());

    }

    @Test
    public void getCityWithHotelsFailed(){
        when(cityRepository.findById(cityDTO.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,()->{
            cityService.getCityWithHotels(cityDTO.getId());
        });
    }

    @Test
    public void createNew() {
        when(cityRepository.save(any())).thenReturn(city1);

        CityDTO cityDTO1 = cityService.createNew(cityDTO);
        assertEquals(cityDTO1.getName(), city1.getName());
    }

    @Test
    public void createNewFailedByDuplicate() {
        when(cityRepository.findByName(cityDTO.getName())).thenReturn(Optional.of(city1));
        Assertions.assertThrows(DuplicateException.class,()->{
            cityService.createNew(cityDTO);
        });
    }

    @Test
    public void updateInformation() {
        when(cityRepository.findById(city1.getId())).thenReturn(Optional.of(update));

        CityDTO cityDTO1 = cityService.updateInformation(cityDTO);

        assertThat(cityDTO1).isNotNull();
        verify(cityRepository).save(any(City.class));
    }

    @Test
    public void updateInformationFailed() {
        when(cityRepository.findById(cityDTO.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,()->{
            cityService.updateInformation(cityDTO);
        });
    }

    @Test
    public void getCitiesWithPagination() {
        int pageNumber = 0;
        int pageSize = 1;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<City> cityPage = new PageImpl<>(Collections.singletonList(city1));

        when(cityRepository.findAll(pageable)).thenReturn(cityPage);
        Page<CityDTO> cities = cityService.getCitiesWithPagination(pageable);
        assertEquals(cities.getNumberOfElements(), 1);
    }
}
