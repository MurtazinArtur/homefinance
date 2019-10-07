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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static geekfactory.homefinance.dao.model.AccountType.CASH;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:delete_tables_ddl.sql")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_ddl.sql")
class TransactionRepositoryCRUDTest {

    @Autowired
    private RepositoryCRUD<TransactionModel, Long> transactionModelRepositoryCRUD;
    @Autowired
    private RepositoryCRUD<BankModel, Long> bankModelRepositoryCRUD;
    @Autowired
    private RepositoryCRUD<AccountModel, Long> accountModelRepositoryCRUD;
    @Autowired
    private RepositoryCRUD<CurrencyModel, Long> currencyModelRepositoryCRUD;
    @Autowired
    private RepositoryCRUD<CategoryTransactionModel, Long> categoryTransactionModelRepositoryCRUD;

    @Test
    void TestContext(){
        assertNotNull(transactionModelRepositoryCRUD);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        transactionModelRepositoryCRUD.save(createModel());

        assertEquals(createModel(), transactionModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate(){
        transactionModelRepositoryCRUD.save(createModel());

        TransactionModel transactionUpdate = transactionModelRepositoryCRUD.findById(1L).orElse(null);
        transactionUpdate.setSource("testUpdate");
        transactionModelRepositoryCRUD.update(transactionUpdate, 1L);

        assertEquals(transactionUpdate, transactionModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll(){
        for (int i = 0; i < createCollectionModels().size(); i++) {
            transactionModelRepositoryCRUD.save(createCollectionModels().get(i));
        }
        List<TransactionModel> expectedList = (List<TransactionModel>) transactionModelRepositoryCRUD.findAll();
        List<TransactionModel> actualList = createCollectionModels();

        assertEquals(expectedList, actualList);

        int expected = 3;
        int actual = actualList.size();

        assertEquals(expected, actual);
    }

    private List<TransactionModel> createCollectionModels() {
        saveAllModels();
        List<TransactionModel> colllection = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            TransactionModel model = new TransactionModel();
            Collection<CategoryTransactionModel> categoryTransactionModels = new HashSet<>();
            model.setId(Long.valueOf(i));
            model.setAmount(BigDecimal.valueOf(35.18));
            model.setDate(LocalDate.now());
            model.setSource("Testing Record");
            model.setBank(bankModelRepositoryCRUD.findById(1L).get());
            model.setAccount(accountModelRepositoryCRUD.findById(1L).get());
            model.setCurrency(currencyModelRepositoryCRUD.findById(1L).get());
            categoryTransactionModels.add(categoryTransactionModelRepositoryCRUD.findById(1L).get());
            model.setCategory(categoryTransactionModels);
            colllection.add(model);
        }
        return colllection;
    }

    private TransactionModel createModel() {
        saveAllModels();
        TransactionModel model = new TransactionModel();
        Collection<CategoryTransactionModel> categoryTransactionModels = new HashSet<>();
        model.setId(1l);
        model.setAmount(BigDecimal.valueOf(35.18));
        model.setDate(LocalDate.now());
        model.setSource("Testing Record");
        model.setBank(bankModelRepositoryCRUD.findById(1L).get());
        model.setAccount(accountModelRepositoryCRUD.findById(1L).get());
        model.setCurrency(currencyModelRepositoryCRUD.findById(1L).get());
        categoryTransactionModels.add(categoryTransactionModelRepositoryCRUD.findById(1L).get());
        model.setCategory(categoryTransactionModels);

        return model;
    }

    private void saveAccountModel() {
        AccountModel accountModel = new AccountModel();
        accountModel.setId(1L);
        accountModel.setName("test");
        accountModel.setAmount(new BigDecimal("1.00"));
        accountModel.setCurrencyModel(currencyModelRepositoryCRUD.findById(1L).orElse(null));
        accountModel.setAccountType(CASH);

        accountModelRepositoryCRUD.save(accountModel);
    }

    private void saveCurrencyModel() {
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setId(1L);
        currencyModel.setName("Dollar");
        currencyModel.setSymbol("D");
        currencyModel.setCode("USD");

        currencyModelRepositoryCRUD.save(currencyModel);
    }

    private void saveBankModel() {
        BankModel model = new BankModel();
        model.setId(1L);
        model.setName("VTB");

        bankModelRepositoryCRUD.save(model);
    }

    private void saveCategoryModel() {
        CategoryTransactionModel categoryTransactionModel = new CategoryTransactionModel();
        categoryTransactionModel.setId(1l);
        categoryTransactionModel.setName("test1");
        categoryTransactionModel.setParentCategory(null);

        categoryTransactionModelRepositoryCRUD.save(categoryTransactionModel);
    }

    private void saveAllModels() {
        saveBankModel();
        saveCurrencyModel();
        saveAccountModel();
        saveCategoryModel();
    }
}