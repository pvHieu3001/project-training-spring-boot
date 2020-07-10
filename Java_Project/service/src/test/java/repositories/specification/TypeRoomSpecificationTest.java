package repositories.specification;

import com.smartosc.training.repositories.specifications.TypeRoomSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 10/07/2020 - 10:38 AM
 * @created_by Hieupv
 * @since 10/07/2020
 */
public class TypeRoomSpecificationTest {
    @Nested
    class SpecificationReturnNull {
        @Test
        @DisplayName("typeRoomHasId when input null return not null")
        void testGreaterThanOrEqualToFromDateReturnNullWhenInputNull() {
            assertNotNull(TypeRoomSpecification.spec().typeRoomHasId(null), "should return not null");
        }
    }

    @Nested
    class SpecificationReturnData {
        @Test
        @DisplayName("typeRoomHasId when input null return not null")
        void testGreaterThanOrEqualToFromDateReturnNullWhenInputNull() {
            assertNotNull(TypeRoomSpecification.spec().typeRoomHasId(1L), "should return not null");
        }
    }
}
