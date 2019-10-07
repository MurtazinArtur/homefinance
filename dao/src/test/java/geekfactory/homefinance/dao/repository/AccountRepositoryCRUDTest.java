package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.config.DaoConfiguration;
import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.model.CurrencyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static geekfactory.homefinance.dao.model.AccountType.CASH;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:delete_tables_ddl.sql")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_ddl.sql")
class AccountRepositoryCRUDTest {

    @Autowired
    private RepositoryCRUD<AccountModel, Long> accountModelRepositoryCRUD;
    @Autowired
    private RepositoryCRUD<CurrencyModel, Long> currencyModelRepositoryCRUD;

    @Test
    void TestContext(){
        assertNotNull(accountModelRepositoryCRUD);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        accountModelRepositoryCRUD.save(createModel());

        assertEquals(createModel(), accountModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate(){
        accountModelRepositoryCRUD.save(createModel());

        AccountModel accountUpdate = accountModelRepositoryCRUD.findById(1L).orElse(null);
        accountUpdate.setName("testUpdate");
        accountModelRepositoryCRUD.update(accountUpdate, 1L);

        assertEquals(accountUpdate, accountModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll(){
        for (int i = 0; i < createCollectionModels().size(); i++) {
            accountModelRepositoryCRUD.save(createCollectionModels().get(i));
        }
        List<AccountModel> expectedList = createCollectionModels();
        List<AccountModel> actualList = (List<AccountModel>) accountModelRepositoryCRUD.findAll();

        assertEquals(expectedList, actualList);

        int expected = 3;
        int actual = actualList.size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("running remove test")
    void testRemove(){
        accountModelRepositoryCRUD.save(createModel());
        AccountModel accountModel = accountModelRepositoryCRUD.findById(1L).orElse(null);
        accountModelRepositoryCRUD.remove(accountModel.getId());
        AccountModel removedModel = accountModelRepositoryCRUD.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private void saveCurrencymodel() {
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setId(1L);
        currencyModel.setName("Dollar");
        currencyModel.setSymbol("D");
        currencyModel.setCode("USD");
        currencyModelRepositoryCRUD.save(currencyModel);
    }

    private AccountModel createModel() {
        saveCurrencymodel();
        AccountModel accountModel = new AccountModel();
        accountModel.setId(1L);
        accountModel.setName("test");
        accountModel.setAmount(new BigDecimal("1.00"));
        accountModel.setCurrencyModel(currencyModelRepositoryCRUD.findById(1L).orElse(null));
        accountModel.setAccountType(CASH);

        return accountModel;
    }

    private List<AccountModel> createCollectionModels() {
        List<AccountModel> colllection = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            saveCurrencymodel();
            AccountModel model = new AccountModel();
            model.setId(Long.valueOf(i));
            model.setName("test");
            model.setAccountType(CASH);
            model.setAmount(new BigDecimal("1.00"));
            model.setCurrencyModel(currencyModelRepositoryCRUD.findById(1L).orElse(null));
            colllection.add(model);
        }
        return colllection;
    }
}