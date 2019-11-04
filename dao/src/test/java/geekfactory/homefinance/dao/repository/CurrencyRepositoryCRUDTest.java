package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.config.DaoConfiguration;
import geekfactory.homefinance.dao.model.CurrencyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfiguration.class})

@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:delete_tables_ddl.sql")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_ddl.sql")
class CurrencyRepositoryCRUDTest {

    @Autowired
    private CurrencyRepositoryCRUD currencyModelRepositoryCRUD;

    @Test
    void TestContext(){
        assertNotNull(currencyModelRepositoryCRUD);
    }

    @Test
    @Transactional
    @DisplayName("running save and findById test")
    void testSaveAndFind() {
        assertEquals(createModel(), currencyModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @Transactional
    @DisplayName("running findAll test")
    void testFindAll() {
        List<CurrencyModel> actualList = createCollectionModels();
        List expectedList = (List<CurrencyModel>) currencyModelRepositoryCRUD.findAll();

        assertEquals(expectedList, actualList);

        int expected = expectedList.size();
        int actual = 3;

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @DisplayName("running update test")
    void testUpdate() {
        createModel();

        CurrencyModel accountUpdate = currencyModelRepositoryCRUD.findById(1L).orElse(null);
        accountUpdate.setName("testUpdate");
        currencyModelRepositoryCRUD.update(accountUpdate);

        assertEquals(accountUpdate, currencyModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @Transactional
    @DisplayName("running remove test")
    void testRemove() {
        createModel();
        CurrencyModel currencyModel = currencyModelRepositoryCRUD.findById(1L).orElse(null);
        currencyModelRepositoryCRUD.remove(currencyModel);
        CurrencyModel removedModel = currencyModelRepositoryCRUD.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private CurrencyModel createModel() {
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setName("Dollar");
        currencyModel.setSymbol("D");
        currencyModel.setCode("USD");

        currencyModelRepositoryCRUD.save(currencyModel);
        return currencyModel;
    }


    private List<CurrencyModel> createCollectionModels() {
        List<CurrencyModel> colllection = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            CurrencyModel currencyModel = new CurrencyModel();
            currencyModel.setName("Dollar");
            currencyModel.setSymbol("D");
            currencyModel.setCode("USD");
            colllection.add(currencyModel);

            currencyModelRepositoryCRUD.save(currencyModel);
        }
        return colllection;
    }
}
