package repositories.specification;

import com.smartosc.training.ServiceApplication;
import com.smartosc.training.entities.TypeRoom;
import com.smartosc.training.repositories.specifications.TypeRoomSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 10/07/2020 - 10:38 AM
 * @created_by Hieupv
 * @since 10/07/2020
 */

@ExtendWith(SpringExtension.class)
public class TypeRoomSpecificationTest {

    @Mock
    private CriteriaBuilder cb;

    @Nested
    class SpecificationReturnNull {
        @Test
        @DisplayName("typeRoomHasId when input null return not null")
        void testGreaterThanOrEqualToFromDateReturnNullWhenInputNull() {
            assertNotNull(TypeRoomSpecification.spec().typeRoomHasId(null).build(), "should return not null");
        }
    }

    @Nested
    class SpecificationReturnData {
        @Test
        @DisplayName("typeRoomHasId when input null return not null")
        void testGreaterThanOrEqualToFromDateReturnNullWhenInputNull() {
            assertNotNull(TypeRoomSpecification.spec().typeRoomHasId(1L).build(), "should return not null");
        }
    }
}
