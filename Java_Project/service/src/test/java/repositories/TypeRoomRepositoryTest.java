package repositories;

import com.smartosc.training.ServiceApplication;
import com.smartosc.training.entities.TypeRoom;
import com.smartosc.training.repositories.TypeRoomRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 10/07/2020 - 10:42 AM
 * @created_by Hieupv
 * @since 10/07/2020
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes={ServiceApplication.class})
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TypeRoomRepositoryTest {
    @Autowired
    TypeRoomRepository typeRoomRepository;

    @Test()
    public void testFindById() {
        TypeRoom typeRoom = typeRoomRepository.findById(1L).orElse(null);
        then(typeRoom).isNotNull();
        then(typeRoom.getId()).isEqualTo(1L);
    }
}
