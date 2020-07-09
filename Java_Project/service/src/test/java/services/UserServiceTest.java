import com.smartosc.training.repositories.RoleRepository;
import com.smartosc.training.repositories.UserRepository;
import com.smartosc.training.services.UserService;
import com.smartosc.training.services.impl.UserServicesImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 07/07/2020 - 3:15 PM
 * @created_by Huupd
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService = new UserServicesImpl();

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Before
    public void init() {
    }

    @Test
    public void GetAllUserSuccess() {



    }

}
