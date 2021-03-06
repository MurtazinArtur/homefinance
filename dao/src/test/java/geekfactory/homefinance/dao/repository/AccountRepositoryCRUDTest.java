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
class AccountRepositoryCRUDTest {

    private AccountRepositoryCRUD accountModelRepositoryCRUD;
    private CurrencyRepositoryCRUD currencyModelRepositoryCRUD;
    private UserRepository userRepository;

    @Autowired
    AccountRepositoryCRUDTest(AccountRepositoryCRUD accountModelRepositoryCRUD,
                              CurrencyRepositoryCRUD currencyModelRepositoryCRUD, UserRepository userRepository) {
        this.accountModelRepositoryCRUD = accountModelRepositoryCRUD;
        this.currencyModelRepositoryCRUD = currencyModelRepositoryCRUD;
        this.userRepository = userRepository;
    }

    @Test
    void TestContext() {
        assertNotNull(accountModelRepositoryCRUD);
    }

    @Test
    @Transactional
    @DisplayName("running save and findById test")
    void testSaveAndFind() {
        assertEquals(createModel(), accountModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @Transactional
    @DisplayName("running findAll test")
    void testFindAll() {
        List<AccountModel> expectedList = createCollectionModels();
        List<AccountModel> actualList = (List<AccountModel>) accountModelRepositoryCRUD.findAll();

        assertEquals(expectedList, actualList);

        int expected = 3;
        int actual = actualList.size();

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @DisplayName("running update test")
    void testUpdate() {
        createModel();

        AccountModel accountUpdate = accountModelRepositoryCRUD.findById(1L).orElse(null);
        accountUpdate.setName("testUpdate");
        accountModelRepositoryCRUD.update(accountUpdate);

        assertEquals(accountUpdate, accountModelRepositoryCRUD.findById(1L).get());
    }


    @Test
    @Transactional
    @DisplayName("running remove test")
    void testRemove() {
        createModel();

        AccountModel accountModel = accountModelRepositoryCRUD.findById(1L).orElse(null);
        accountModelRepositoryCRUD.remove(accountModel);
        AccountModel removedModel = accountModelRepositoryCRUD.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private void saveCurrencyModel() {
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setName("Dollar");
        currencyModel.setSymbol("D");
        currencyModel.setCode("USD");

        currencyModelRepositoryCRUD.save(currencyModel);
    }

    private AccountModel createModel() {
        saveCurrencyModel();
        saveUserModel();
        AccountModel accountModel = new AccountModel();
        accountModel.setName("test");
        accountModel.setAmount(new BigDecimal("1.00"));
        accountModel.setCurrencyModel(currencyModelRepositoryCRUD.findById(1L).orElse(null));
        accountModel.setAccountType(AccountType.CASH);
        accountModel.setUserModel(userRepository.findById(1L).orElse(null));

        accountModelRepositoryCRUD.save(accountModel);

        return accountModel;
    }

    private void saveUserModel() {
        UserModel userModel = new UserModel();
        userModel.setUser("test");
        userModel.setPassword("test");
        userModel.setUserRole(UserRoles.ROLE_ADMIN);

        userRepository.save(userModel);
    }

    private List<AccountModel> createCollectionModels() {
        List<AccountModel> collection = new ArrayList<>();
        saveCurrencyModel();
        saveUserModel();

        for (int i = 1; i <= 3; i++) {
            AccountModel accountModel = new AccountModel();
            accountModel.setName("test");
            accountModel.setAccountType(AccountType.CASH);
            accountModel.setAmount(new BigDecimal("1.00"));
            accountModel.setCurrencyModel(currencyModelRepositoryCRUD.findById(1L).orElse(null));
            accountModel.setUserModel(userRepository.findById(1L).orElse(null));

            collection.add(accountModel);
            accountModelRepositoryCRUD.save(accountModel);
        }

        return collection;
    }
}