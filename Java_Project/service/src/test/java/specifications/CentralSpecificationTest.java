package specifications;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import com.smartosc.training.entities.Central;
import com.smartosc.training.repositories.specifications.CentralSpecification;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 13/07/2020 - 10:51 AM
 * @created_by Namtt
 * @since 13/07/2020
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CentralSpecificationTest {

  @Mock private Root<Central> root;
  @Mock private CriteriaQuery<?> query;
  @Mock private CriteriaBuilder builder;
  @Mock private Path path;
  @Mock private Expression expression;
  @Mock private Predicate predicate;

  private static final String HAS_TITLE = "South";
  private static final Long HAS_ID = 1L;

  private List<Specification<Central>> specifications;
  private CentralSpecification centralSpecification;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    specifications = new ArrayList<>();
    centralSpecification = CentralSpecification.spec();
  }

  @Test
  public void hasTitle_Success() {
    lenient().when(root.get(anyString())).thenReturn(path);
    lenient().when(builder.equal(expression, HAS_TITLE)).thenReturn(predicate);
    centralSpecification.hasTitle(HAS_TITLE);
  }
}
