package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.UserModel;
import geekfactory.homefinance.dao.model.UserRoles;
import geekfactory.homefinance.dao.repository.UserRepository;
import geekfactory.homefinance.service.config.ServiceConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class UserServiceTest {
    private UserRepository userRepositoryMock = mock(UserRepository.class);

    @InjectMocks
    @Autowired
    private UserService userService;

    @Test
    public void testAccountService() {

        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(createUserModel()));

        assertNotNull(userRepositoryMock);
        Assertions.assertEquals(createUserModel(), userRepositoryMock.findById(1L).get());

        verify(userRepositoryMock, times(1)).findById(anyLong());
        verify(userRepositoryMock, never()).findAll();
        verify(userRepositoryMock, never()).save(createUserModel());
        verify(userRepositoryMock, never()).remove(createUserModel());
        verify(userRepositoryMock, never()).update(eq(createUserModel()));
    }

    private UserModel createUserModel() {
        UserModel userModel = new UserModel();
        userModel.setUser("test");
        userModel.setPassword("test");
        userModel.setUserRole(UserRoles.ROLE_USER);

        return userModel;
    }
}
