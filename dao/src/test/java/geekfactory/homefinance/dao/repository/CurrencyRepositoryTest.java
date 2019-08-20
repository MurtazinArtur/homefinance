package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.CurrencyModel;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyRepositoryTest {
    private static final String CONNECTION_FILE = "C:\\Users\\Work\\IdeaProjects\\GeekFactory_Web04_Murtazin\\dao\\test\\resources\\dbConnectionProperties";
    private static ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    CurrencyModel model = new CurrencyModel();
    CurrencyModel model1 = new CurrencyModel();
    CurrencyModel model2 = new CurrencyModel();

    @BeforeAll
    public void beforeAll() {
        connectionSupplierTest.getConnection();
    }

    @BeforeEach
    public void beforeEach(){
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
    @AfterAll
    public void afterAll(){
        connectionSupplierTest.getDisconnect();
    }
    @Test
    void TestContext(){
        Assertions.assertNotNull(currencyRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    public void testSaveAndFind(){
        currencyRepository.save(model);
        assertEquals(model, currencyRepository.findById((long) 7).get());
    }

    @Test
    @DisplayName("running update test")
    public void testUpdate(){
        Assertions.assertNotNull(model);
        CurrencyModel accountUpdate = currencyRepository.findById((long) 7).orElse(null);
        accountUpdate.setName("testUpdate");
        currencyRepository.update(accountUpdate,(long) 7);
        assertEquals(accountUpdate, currencyRepository.findById((long) 7).get());
    }
    @Test
    @DisplayName("running findAll test")
    public void testFindAll(){
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
    public void testRemove(){
        CurrencyModel currencyModel = currencyRepository.findById(11L).orElse(null);
        currencyRepository.remove(currencyModel.getId());
        CurrencyModel removedModel = currencyRepository.findById(11L).orElse(null);
        assertNull(removedModel);
    }
}