package services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import com.smartosc.training.dto.CentralDTO;
import com.smartosc.training.dto.CityDTO;
import com.smartosc.training.entities.Central;
import com.smartosc.training.entities.City;
import com.smartosc.training.exceptions.NotFoundException;
import com.smartosc.training.repositories.CentralRepository;
import com.smartosc.training.repositories.specifications.CentralSpecification;
import com.smartosc.training.services.impl.CentralServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 08/07/2020 - 3:08 PM
 * @created_by Namtt
 * @since 08/07/2020
 */
@ExtendWith(MockitoExtension.class)
public class CentralServiceTest {

  @Mock private CentralRepository centralRepository;
  @InjectMocks private CentralServiceImpl centralService;
  @Mock private ModelMapper modelMapper;
  @Mock private CentralSpecification centralSpecification = CentralSpecification.spec();

  private List<Central> centrals = new ArrayList<>();
  private List<CentralDTO> centralDTOS = new ArrayList<>();
  private Central central;
  private CentralDTO centralDTO;
  private List<City> cities;
  private List<CityDTO> cityDTOS;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.cities = new ArrayList<>();
    this.cityDTOS = new ArrayList<>();
    this.central = new Central(1L, "namcx", "abc.jpg", this.cities);
    this.centrals.add(central);
    this.centralDTO = new CentralDTO(1L, "namcx", "abc.jpg", this.cityDTOS);
    this.centralDTOS.add(centralDTO);
  }

  @Test
  public void getAllCentral(){
//    lenient().doAnswer(invocationOnMock -> {
//
//    }).when(centralSpecification).byId(anyLong());

  }

  @Test
  public void createCentral() {
    lenient().when(modelMapper.map(central, CentralDTO.class)).thenReturn(centralDTO);
    lenient().when(modelMapper.map(centralDTO, Central.class)).thenReturn(central);
    lenient().when(centralRepository.findByTitle(anyString())).thenReturn(Optional.empty());
    lenient().when(centralRepository.save(any())).thenReturn(central);

    CentralDTO dto = centralService.createCentral(this.centralDTO);
    Assertions.assertEquals(dto.getTitle(), centralDTO.getTitle());
  }

  @Test
  public void createCentralFail() {
    lenient().when(centralRepository.findByTitle(anyString())).thenReturn(Optional.of(central));
    Assertions.assertThrows(
        DuplicateKeyException.class,
        () -> {
          centralService.createCentral(this.centralDTO);
        });
  }

  @Test
  public void updateCentral() {
    lenient().when(modelMapper.map(central, CentralDTO.class)).thenReturn(centralDTO);
    lenient().when(modelMapper.map(centralDTO, Central.class)).thenReturn(central);
    lenient().when(centralRepository.findById(anyLong())).thenReturn(Optional.empty());
    lenient().when(centralRepository.save(any())).thenReturn(central);

    CentralDTO dto = centralService.updateCentral(centralDTO.getId(), centralDTO);
    Assertions.assertEquals(dto.getTitle(), centralDTO.getTitle());
  }

  @Test
  public void updateCentralFail() {
    lenient().when(centralRepository.findById(anyLong())).thenReturn(Optional.of(central));
    Assertions.assertThrows(
        NotFoundException.class,
        () -> {
          centralService.updateCentral(anyLong(), this.centralDTO);
        });
  }
}
