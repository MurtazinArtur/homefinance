package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.model.AccountType;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    private static final String CREATE_TBL = "CREATE TABLE account_tbl\n" +
            "(\n" +
            "    id           INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
            "    name         VARCHAR(50)                    NOT NULL,\n" +
            "    amount       DECIMAL(15, 1)                 NOT NULL,\n" +
            "    currency_id  INT                            NOT NULL,\n" +
            "    account_type VARCHAR(50)                    NOT NULL,\n" +
            "\n" +
            "    CONSTRAINT currency_fk FOREIGN KEY (currency_id) REFERENCES currency_tbl (id)\n" +
            ")";
    private static final String REMOVE_TABLE = "DROP TABLE account_tbl";
    private ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private AccountRepository accountRepository = new AccountRepository();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private AccountModel model = createModel();
    private AccountModel model2 = createModel();
    private AccountModel model3 = createModel();
    @BeforeEach
    void beforeEach() throws SQLException {
        Connection connection = connectionSupplierTest.getConnection();
            connection.prepareStatement(REMOVE_TABLE).executeUpdate();
            connection.prepareStatement(CREATE_TBL).executeUpdate();
    }

    @Test
    void TestContext(){
        assertNotNull(accountRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        accountRepository.save(model);

        assertEquals(model, accountRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate(){
        accountRepository.save(model);

        AccountModel accountUpdate = accountRepository.findById(1L).orElse(null);
        accountUpdate.setName("testUpdate");
        accountRepository.update(accountUpdate, 1L);

        assertEquals(accountUpdate, accountRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll(){
        accountRepository.save(model);
        accountRepository.save(model2);
        accountRepository.save(model3);
        List<AccountModel> expectedList = (List<AccountModel>) accountRepository.findAll();

        List<AccountModel> actualList = new ArrayList<>();
        actualList.add(model);
        actualList.add(model2);
        actualList.add(model3);

        assertEquals(expectedList, actualList);

        int expected = expectedList.size();
        int actual = 3;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("running remove test")
    void testRemove(){
        for (int i = 0; i < 3; i++) {
            accountRepository.save(model);
        }

        AccountModel accountModel = accountRepository.findById(1L).orElse(null);
        accountRepository.remove(accountModel.getId());
        AccountModel removedModel = accountRepository.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private AccountModel createModel() {
        AccountModel model = new AccountModel();
        model.setName("test");
        model.setAccountType(AccountType.CASH);
        model.setAmount(BigDecimal.valueOf(1.00));
        model.setCurrencyModel(currencyRepository.findById(1L).orElse(null));

        return model;
    }
}