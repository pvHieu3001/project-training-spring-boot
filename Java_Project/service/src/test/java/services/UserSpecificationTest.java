package services;

import com.smartosc.training.entities.User;
import com.smartosc.training.repositories.specifications.UserSpecification;
import com.smartosc.training.repositories.specifications.UserSpecifications;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 09/07/2020 - 5:08 PM
 * @created_by Huupd
 */
//@SpringBootTest
//@RunWith(PowerMockRunner.class)
//@PowerMockIgnore({"javax.management.*", "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.dom.*"})
//@PrepareForTest({UserSpecifications.class})
public class UserSpecificationTest {
//    @Mock
//    private Root<User> root;
//
//    @Mock
//    private CriteriaQuery<?> query;
//
//    @Mock
//    private CriteriaBuilder cb;
//
//    @Mock
//    private Predicate predicate;
//
//    @Mock
//    private Path path;
//
//    @Mock
//    private Expression expression;
//
//
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void all_susscess() {
//        UserSpecifications userSpecifications = UserSpecifications.spec();
//        when(root.get("code")).thenReturn(path);
//        when(cb.equal(expression,any())).thenReturn(predicate);
//        Specification<User> actual = userSpecifications.all();
//        predicate = actual.toPredicate(root, query, cb);
//
//        assertTrue(actual != null);
//    }

//
//    @Test
//    public void isSwitch_susscess() {
//        when(root.get("isSwitch")).thenReturn(path);
//        when(cb.equal(expression, IS_SWITCH_PARAM)).thenReturn(predicate);
//
//        Specification<ServiceEntity> actual = ServiceSpecification.isSwitch(IS_SWITCH_PARAM);
//        predicate = actual.toPredicate(root, query, cb);
//
//        assertTrue(actual != null);
//    }
//
//    @Test
//    public void hasStatusActive_susscess() {
//        when(root.get("status")).thenReturn(path);
//        when(cb.equal(expression, HAS_STATUS)).thenReturn(predicate);
//
//        Specification<ServiceEntity> actual = ServiceSpecification.hasStatus(HAS_STATUS);
//        predicate = actual.toPredicate(root, query, cb);
//
//        assertTrue(actual != null);
//    }
}