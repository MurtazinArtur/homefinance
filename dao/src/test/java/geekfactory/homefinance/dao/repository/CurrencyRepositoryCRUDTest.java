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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfiguration.class})

@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:delete_tables_ddl.sql")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_ddl.sql")
class CurrencyRepositoryCRUDTest {

    @Autowired
    private RepositoryCRUD<CurrencyModel, Long> currencyModelRepositoryCRUD;

    @Test
    void TestContext(){
        assertNotNull(currencyModelRepositoryCRUD);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind() {
        currencyModelRepositoryCRUD.save(createModel());

        assertEquals(createModel(), currencyModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate() {
        currencyModelRepositoryCRUD.save(createModel());

        CurrencyModel accountUpdate = currencyModelRepositoryCRUD.findById(1L).orElse(null);
        accountUpdate.setName("testUpdate");
        currencyModelRepositoryCRUD.update(accountUpdate, 1L);

        assertEquals(accountUpdate, currencyModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll() {
        for (int i = 0; i < createCollectionModels().size(); i++) {
            currencyModelRepositoryCRUD.save(createCollectionModels().get(i));
        }
        List expectedList = (List<CurrencyModel>) currencyModelRepositoryCRUD.findAll();

        List<CurrencyModel> actualList = createCollectionModels();

        assertEquals(expectedList, actualList);

        int expected = expectedList.size();
        int actual = 3;

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("running remove test")
    void testRemove() {
        currencyModelRepositoryCRUD.save(createModel());
        CurrencyModel currencyModel = currencyModelRepositoryCRUD.findById(1L).orElse(null);
        currencyModelRepositoryCRUD.remove(currencyModel.getId());
        CurrencyModel removedModel = currencyModelRepositoryCRUD.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private CurrencyModel createModel() {
        CurrencyModel model = new CurrencyModel();
        model.setId(1L);
        model.setName("Dollar");
        model.setSymbol("D");
        model.setCode("USD");

        return model;
    }


    private List<CurrencyModel> createCollectionModels() {
        List<CurrencyModel> colllection = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            CurrencyModel model = new CurrencyModel();
            model.setId(Long.valueOf(i));
            model.setName("Dollar");
            model.setSymbol("D");
            model.setCode("USD");
            colllection.add(model);
        }
        return colllection;
    }
}