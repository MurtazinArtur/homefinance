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

public class AccountRepositoryTest  {

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
    private static ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private AccountRepository accountRepository = new AccountRepository();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    AccountModel model = new AccountModel();
    AccountModel model1 = new AccountModel();
    AccountModel model2 = new AccountModel();

    @BeforeAll
   static void beforeAll() {
        Connection connection = connectionSupplierTest.getConnection();
        try {
            connection.prepareStatement(REMOVE_TABLE).executeUpdate();
            connection.prepareStatement(CREATE_TBL).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void beforeEach(){

        model.setName("test");
        model.setAccountType(AccountType.CASH);
        model.setAmount(BigDecimal.valueOf(1.00));
        model.setCurrencyModel(currencyRepository.findById((long) 2).orElse(null));

        model1.setName("test1");
        model1.setAccountType(AccountType.CASH);
        model1.setAmount(BigDecimal.valueOf(1.00));
        model1.setCurrencyModel(currencyRepository.findById((long) 2).orElse(null));

        model2.setName("test2");
        model2.setAccountType(AccountType.CASH);
        model2.setAmount(BigDecimal.valueOf(1.00));
        model2.setCurrencyModel(currencyRepository.findById((long) 2).orElse(null));
    }

    @Test
    void TestContext(){
        assertNotNull(accountRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        accountRepository.save(model);
        assertEquals(model, accountRepository.findById((long) 4).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate(){
        Assertions.assertNotNull(model);
        AccountModel accountUpdate = accountRepository.findById((long) 2).orElse(null);
        accountUpdate.setName("testUpdate");
        accountRepository.update(accountUpdate,(long) 2);
        assertEquals(accountUpdate, accountRepository.findById((long) 2).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll(){
        accountRepository.save(model);
        accountRepository.save(model1);
        accountRepository.save(model2);
        List<AccountModel> expectedList = (List<AccountModel>) accountRepository.findAll();
        List<AccountModel> actualList = new ArrayList<>();
        actualList.add(model);
        actualList.add(model1);
        actualList.add(model2);
        assertEquals(expectedList, actualList);

        int expected = expectedList.size();
        int actual = 3;
        assertEquals(expected, actual);
        assertNotNull(expectedList);
    }

    @Test
    @DisplayName("running remove test")
    void testRemove(){
        AccountModel accountModel = accountRepository.findById((long) 1).orElse(null);
        accountRepository.remove(accountModel.getId());
        AccountModel removedModel = accountRepository.findById((long) 1).orElse(null);
        assertNull(removedModel);
    }
}