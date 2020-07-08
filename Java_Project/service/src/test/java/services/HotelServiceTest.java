package services;

import com.smartosc.training.dto.CityDTO;
import com.smartosc.training.dto.HotelDTO;
import com.smartosc.training.dto.TypeRoomDTO;
import com.smartosc.training.entities.*;
import com.smartosc.training.exceptions.DuplicateException;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.repositories.HotelRepository;
import com.smartosc.training.services.HotelService;
import com.smartosc.training.services.impl.HotelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
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
 * @created_at 07/07/2020 - 10:23 AM
 */
@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    @InjectMocks
    private HotelService hotelService = new HotelServiceImpl();

    @Mock
    private HotelRepository hotelRepository;

    private List<Hotel> hotelList;
    private Hotel hotel;
    private Optional<Hotel> optionalHotel;
    private List<HotelDTO> hotelDTOList;
    private HotelDTO hotelDTO, testInput;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        Central central = new Central(1L, "Hotel 1","Hotel 1", new ArrayList<>());
        City city5 = new City(5L, "City 5", "city3.jpg", hotelList, central);

        hotelList = new ArrayList<>();
        List<Comment> commentList = new ArrayList<>();
        Comment comment = new Comment(1L, "Comment1", 123, new User(), new Hotel());
        Comment comment1 = new Comment(2L, "Comment2", 123, new User(), new Hotel());
        commentList.add(comment);
        commentList.add(comment1);



        List<TypeRoom> typeRoomList = new ArrayList<>();
        TypeRoom typeRoom = new TypeRoom(2L, "Comment1", "", new BigDecimal("34"), new Hotel(), new ArrayList<>());
        typeRoomList.add(typeRoom);
        hotel = new Hotel(1L, "Hotel 1", "description 1", "hotel1.jpg",
                new BigDecimal("4.4"), city5, typeRoomList, commentList);
        Hotel hotel1 = new Hotel(2L, "Hotel 1", "description 1", "hotel1.jpg",
                new BigDecimal("4.4"), city5, typeRoomList, commentList);
        Hotel hotel2 = new Hotel(3L, "Hotel 1", "description 1", "hotel1.jpg",
                new BigDecimal("4.4"), city5, typeRoomList, commentList);
        optionalHotel = Optional.of(hotel);

        List<TypeRoomDTO> typeRoomDTOList = new ArrayList<>();
        TypeRoomDTO typeRoomDTO = new TypeRoomDTO(2L, "Comment1", "fh", new BigDecimal("34"));
        typeRoomDTOList.add(typeRoomDTO);
        testInput = new HotelDTO(3L, "Hotel 1", "description 1", "hotel1.jpg",
                new BigDecimal("4.4"), new CityDTO(), new ArrayList<>(), typeRoomDTOList);

        hotelList.add(hotel);
        hotelList.add(hotel1);
        hotelList.add(hotel2);
    }

    @Test
    public void getAllHotels() {
        when(hotelRepository.findAll()).thenReturn(hotelList);
        hotelDTOList = hotelService.getAllHotels();
        assertEquals(hotelList.size(), hotelDTOList.size());
    }

    @Test
    public void getHotelByID() {
        when(hotelRepository.findById(hotel.getId())).thenReturn(optionalHotel);

        hotelDTO = hotelService.getHotelByID(1L);
        assertEquals(hotelDTO.getName(), optionalHotel.get().getName());
    }

    @Test
    public void getHotelByIDFailed() {
        when(hotelRepository.findById(testInput.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,()->{
            hotelService.getHotelByID(testInput.getId());
        });
    }

    @Test
    public void createNew() {
        when(hotelRepository.save(any())).thenReturn(hotel);

        HotelDTO hotelDTO1 = hotelService.createNew(testInput);
        assertEquals(hotelDTO1.getName(), hotel.getName());
    }

    @Test
    public void createNewFailedByDuplicate() {
        when(hotelRepository.findByName(testInput.getName())).thenReturn(optionalHotel);
        Assertions.assertThrows(DuplicateException.class,()->{
            hotelService.createNew(testInput);
        });
    }

    @Test
    public void updateHotel() {
        when(hotelRepository.findById(testInput.getId())).thenReturn(Optional.of(hotel));

        HotelDTO hotelDTO1 = hotelService.updateHotel(testInput);

        assertThat(hotelDTO1).isNotNull();
        verify(hotelRepository).save(any(Hotel.class));
    }

    @Test
    public void updateInformationFailed() {
        when(hotelRepository.findById(testInput.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,()->{
            hotelService.updateHotel(testInput);
        });
    }
}
