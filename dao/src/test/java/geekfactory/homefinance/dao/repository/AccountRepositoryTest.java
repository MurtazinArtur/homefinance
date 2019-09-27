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
class AccountRepositoryTest {

    @Autowired
    private Repository<AccountModel, Long> accountModelRepository;
    @Autowired
    private Repository<CurrencyModel, Long> currencyModelRepository;

    @Test
    void TestContext(){
        assertNotNull(accountModelRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        accountModelRepository.save(createModel());

        assertEquals(createModel(), accountModelRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate(){
        accountModelRepository.save(createModel());

        AccountModel accountUpdate = accountModelRepository.findById(1L).orElse(null);
        accountUpdate.setName("testUpdate");
        accountModelRepository.update(accountUpdate, 1L);

        assertEquals(accountUpdate, accountModelRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll(){
        for (int i = 0; i < createCollectionModels().size(); i++) {
            accountModelRepository.save(createCollectionModels().get(i));
        }
        List<AccountModel> expectedList = createCollectionModels();
        List<AccountModel> actualList = (List<AccountModel>) accountModelRepository.findAll();

        assertEquals(expectedList, actualList);

        int expected = 3;
        int actual = actualList.size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("running remove test")
    void testRemove(){
        accountModelRepository.save(createModel());
        AccountModel accountModel = accountModelRepository.findById(1L).orElse(null);
        accountModelRepository.remove(accountModel.getId());
        AccountModel removedModel = accountModelRepository.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private void saveCurrencymodel() {
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setId(1L);
        currencyModel.setName("Dollar");
        currencyModel.setSymbol("D");
        currencyModel.setCode("USD");
        currencyModelRepository.save(currencyModel);
    }

    private AccountModel createModel() {
        saveCurrencymodel();
        AccountModel accountModel = new AccountModel();
        accountModel.setId(1L);
        accountModel.setName("test");
        accountModel.setAmount(new BigDecimal("1.00"));
        accountModel.setCurrencyModel(currencyModelRepository.findById(1L).orElse(null));
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
            model.setCurrencyModel(currencyModelRepository.findById(1L).orElse(null));
            colllection.add(model);
        }
        return colllection;
    }
}