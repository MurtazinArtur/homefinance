package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.config.DaoConfiguration;
import geekfactory.homefinance.dao.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:delete_tables_ddl.sql")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_ddl.sql")
public class UserRepositoryTest {
    private UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void TestContext() {
        assertNotNull(userRepository);
    }

    @Test
    @Transactional
    @DisplayName("running save and findById test")
    void testSaveAndFind() {
        assertEquals(createUserModel(), userRepository.findById(1L).get());
    }

    @Test
    @Transactional
    @DisplayName("running findAll test")
    void testFindAll() {
        List<UserModel> expectedList = createCollectionModels();
        List<UserModel> actualList = (List<UserModel>) userRepository.findAll();

        assertEquals(expectedList, actualList);

        int expected = 3;
        int actual = actualList.size();

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @DisplayName("running update test")
    void testUpdate() {
        createUserModel();

        UserModel updateUserModel = userRepository.findById(1L).orElse(null);
        updateUserModel.setUser("testUpdate");
        userRepository.update(updateUserModel);

        assertEquals(updateUserModel, userRepository.findById(1L).get());
    }


    @Test
    @Transactional
    @DisplayName("running remove test")
    void testRemove() {
        createUserModel();

        UserModel userModel = userRepository.findById(1L).orElse(null);
        userRepository.remove(userModel);
        UserModel removedModel = userRepository.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private UserModel createUserModel() {
        UserModel userModel = new UserModel();
        userModel.setUser("test");
        userModel.setPassword("test");
        userModel.setUserRole(UserRoles.ROLE_ADMIN);

        userRepository.save(userModel);

        return userModel;
    }

    private List<UserModel> createCollectionModels() {
        List<UserModel> collection = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            UserModel userModel = new UserModel();
            userModel.setUser("test");
            userModel.setPassword("test");
            userModel.setUserRole(UserRoles.ROLE_ADMIN);

            collection.add(userModel);
            userRepository.save(userModel);
        }
        return collection;
    }
}
