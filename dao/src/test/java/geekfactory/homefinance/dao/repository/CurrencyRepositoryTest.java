package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.CurrencyModel;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRepositoryTest {
    private static final String CREATE_TBL = "CREATE TABLE currency_tbl\n" +
            "(\n" +
            "    id     INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
            "    name   VARCHAR(50)                    NOT NULL,\n" +
            "    code   VARCHAR(50)                    NOT NULL,\n" +
            "    symbol VARCHAR(50)                    NOT NULL\n" +
            ")";
    private static final String REMOVE_TABLE = "DROP TABLE currency_tbl";
    private ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private CurrencyModel model = createModel();
    private CurrencyModel model2 = createModel();
    private CurrencyModel model3 = createModel();

    @BeforeEach
    void beforeEach() throws SQLException {
        Connection connection = connectionSupplierTest.getConnection();
            connection.prepareStatement(REMOVE_TABLE).executeUpdate();
            connection.prepareStatement(CREATE_TBL).executeUpdate();
    }

    @Test
    void TestContext(){
        Assertions.assertNotNull(currencyRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind() {
        currencyRepository.save(model);

        assertEquals(model, currencyRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate() {
        currencyRepository.save(model);

        CurrencyModel accountUpdate = currencyRepository.findById(1L).orElse(null);
        accountUpdate.setName("testUpdate");
        currencyRepository.update(accountUpdate, 1L);

        assertEquals(accountUpdate, currencyRepository.findById(1L).get());
    }
    @Test
    @DisplayName("running findAll test")
    void testFindAll() {
        currencyRepository.save(model);
        currencyRepository.save(model2);
        currencyRepository.save(model3);
        List expectedList = (List<CurrencyModel>) currencyRepository.findAll();

        List<CurrencyModel> actualList = new ArrayList<>();
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
    void testRemove() {
        for (int i = 0; i < 3; i++) {
            currencyRepository.save(model);
        }
        CurrencyModel currencyModel = currencyRepository.findById(1L).orElse(null);
        currencyRepository.remove(currencyModel.getId());
        CurrencyModel removedModel = currencyRepository.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private CurrencyModel createModel() {
        CurrencyModel model = new CurrencyModel();
        model.setName("Dollar");
        model.setSymbol("D");
        model.setCode("USD");

        return model;
    }
}