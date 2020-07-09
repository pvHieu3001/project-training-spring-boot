package specifications;

import com.smartosc.training.entities.Hotel;
import com.smartosc.training.entities.Hotel_;
import com.smartosc.training.repositories.specifications.HotelSpecification;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 09/07/2020 - 3:11 PM
 */
public class HotelSpecificationTest {
    //specification
    private CriteriaBuilder criteriaBuilderMock;

    private CriteriaQuery criteriaQueryMock;

    private Root<Hotel> hotelRootMock;
    private static final String SEARCH_TERM = "Hotel 1";
    private static final String SEARCH_TERM_LIKE_PATTERN = "hotel 1%";

    @Before
    public void setUp() {
        criteriaBuilderMock = mock(CriteriaBuilder.class);
        criteriaQueryMock = mock(CriteriaQuery.class);
        hotelRootMock = mock(Root.class);
    }

    @Test
    public void geHotelsByName() {
        Path namePathMock = mock(Path.class);
        when(hotelRootMock.get(Hotel_.name)).thenReturn(namePathMock);

        Expression nameExpressionMock = mock(Expression.class);
        when(criteriaBuilderMock.lower(namePathMock)).thenReturn(nameExpressionMock);

        Predicate namePredicateMock = mock(Predicate.class);
        when(criteriaBuilderMock.like(nameExpressionMock, SEARCH_TERM_LIKE_PATTERN)).thenReturn(namePredicateMock);

        Specification<Hotel> actual = HotelSpecification.geHotelsByNameSpec(SEARCH_TERM);
        Predicate actualPredicate = actual.toPredicate(hotelRootMock, criteriaQueryMock,criteriaBuilderMock);

        verify(hotelRootMock, times(1)).get(Hotel_.name);
        verifyNoMoreInteractions(hotelRootMock);

        verify(criteriaBuilderMock, times(1)).lower(namePathMock);
        verify(criteriaBuilderMock, times(1)).like(nameExpressionMock, SEARCH_TERM_LIKE_PATTERN);
        verifyNoMoreInteractions(criteriaBuilderMock);

        verifyZeroInteractions(criteriaQueryMock, namePathMock, namePredicateMock);

        assertEquals(namePredicateMock, actualPredicate);
    }
}
