package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.CurrencyModel;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyRepositoryTest {
    private static final String CREATE_TBL = "CREATE TABLE currency_tbl\n" +
            "(\n" +
            "    id     INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
            "    name   VARCHAR(50)                    NOT NULL,\n" +
            "    code   VARCHAR(50)                    NOT NULL,\n" +
            "    symbol VARCHAR(50)                    NOT NULL\n" +
            ")";
    private static final String REMOVE_TABLE = "DROP TABLE currency_tbl";
    private static ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    CurrencyModel model = new CurrencyModel();
    CurrencyModel model1 = new CurrencyModel();
    CurrencyModel model2 = new CurrencyModel();

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

    @AfterAll
    static void afterAll() {
        connectionSupplierTest.getDisconnection();
    }

    @BeforeEach
    void beforeEach() {
        model.setName("Dollar");
        model.setSymbol("D");
        model.setCode("USD");

        model1.setName("Dollar");
        model1.setSymbol("D");
        model1.setCode("USD");

        model2.setName("Dollar");
        model2.setSymbol("D");
        model2.setCode("USD");
    }
    @Test
    void TestContext(){
        Assertions.assertNotNull(currencyRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind() {
        currencyRepository.save(model);
        assertEquals(model, currencyRepository.findById((long) 4).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate() {
        currencyRepository.save(model);
        Assertions.assertNotNull(model);
        CurrencyModel accountUpdate = currencyRepository.findById((long) 4).orElse(null);
        accountUpdate.setName("testUpdate");
        currencyRepository.update(accountUpdate, (long) 4);
        assertEquals(accountUpdate, currencyRepository.findById((long) 4).get());
    }
    @Test
    @DisplayName("running findAll test")
    void testFindAll() {
        currencyRepository.save(model);
        currencyRepository.save(model1);
        currencyRepository.save(model2);
        List expectedList = (List<CurrencyModel>) currencyRepository.findAll();
        List<CurrencyModel> actualList = new ArrayList<>();
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
    void testRemove() {
        CurrencyModel currencyModel = currencyRepository.findById((long) 1).orElse(null);
        currencyRepository.remove(currencyModel.getId());
        CurrencyModel removedModel = currencyRepository.findById((long) 1).orElse(null);
        assertNull(removedModel);
    }
}