package repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.smartosc.training.ServiceApplication;
import com.smartosc.training.entities.Central;
import com.smartosc.training.entities.City;
import com.smartosc.training.repositories.CentralRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 10/07/2020 - 2:50 PM
 * @created_by Namtt
 * @since 10/07/2020
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {ServiceApplication.class})
public class CentralRepositoryTest {
  @Autowired private CentralRepository centralRepository;

  private Central central;
  private List<City> cities = new ArrayList<>();

  @BeforeEach
  void init() {
    central = new Central(1L, "South", "south", cities);
  }

  @DisplayName("find Central by Title")
  @Test
  public void whenFindByTitle() {
    assertThat(centralRepository.findByTitle("south").get().getTitle()).isEqualTo(central.getTitle());
  }
}
