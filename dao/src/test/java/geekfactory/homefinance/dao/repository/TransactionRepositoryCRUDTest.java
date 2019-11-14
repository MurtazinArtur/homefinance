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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static geekfactory.homefinance.dao.model.AccountType.CASH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:delete_tables_ddl.sql")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_ddl.sql")
@Transactional
class TransactionRepositoryCRUDTest {

    @Autowired
    private TransactionRepositoryCRUD transactionModelRepositoryCRUD;
    @Autowired
    private BankRepositoryCRUD bankModelRepositoryCRUD;
    @Autowired
    private AccountRepositoryCRUD accountModelRepositoryCRUD;
    @Autowired
    private CurrencyRepositoryCRUD currencyModelRepositoryCRUD;
    @Autowired
    private CategoryTransactionRepositoryCRUD categoryTransactionModelRepositoryCRUD;

    @Test
    void TestContext(){
        assertNotNull(transactionModelRepositoryCRUD);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        assertEquals(createModel(), transactionModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll(){
        List<TransactionModel> expectedList = createCollectionModels();
        List<TransactionModel> actualList = (List<TransactionModel>) transactionModelRepositoryCRUD.findAll();

        assertEquals(expectedList, actualList);

        int expected = 3;
        int actual = actualList.size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("running update test")
    void testUpdate() {
        createModel();

        TransactionModel transactionUpdate = transactionModelRepositoryCRUD.findById(1L).orElse(null);
        transactionUpdate.setSource("testUpdate");
        transactionModelRepositoryCRUD.update(transactionUpdate);

        assertEquals(transactionUpdate, transactionModelRepositoryCRUD.findById(1L).get());
    }

    private List<TransactionModel> createCollectionModels() {
        saveAllModels();
        List<TransactionModel> collection = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            TransactionModel transactionModel = new TransactionModel();
            Collection<CategoryTransactionModel> categoryTransactionModels = new HashSet<>();
            transactionModel.setId(Long.valueOf(i));
            transactionModel.setAmount(BigDecimal.valueOf(35.18));
            transactionModel.setDate(LocalDate.now());
            transactionModel.setSource("Testing Record");
            transactionModel.setBank(bankModelRepositoryCRUD.findById(1L).get());
            transactionModel.setAccount(accountModelRepositoryCRUD.findById(1L).get());
            transactionModel.setCurrency(currencyModelRepositoryCRUD.findById(1L).get());
            categoryTransactionModels.add(categoryTransactionModelRepositoryCRUD.findById(1L).get());
            transactionModel.setCategory(categoryTransactionModels);
            collection.add(transactionModel);

            transactionModelRepositoryCRUD.save(transactionModel);
        }
        return collection;
    }

    private TransactionModel createModel() {
        saveAllModels();
        TransactionModel transactionModel = new TransactionModel();
        Collection<CategoryTransactionModel> categoryTransactionModels = new HashSet<>();
        transactionModel.setId(1L);
        transactionModel.setAmount(BigDecimal.valueOf(35.18));
        transactionModel.setDate(LocalDate.now());
        transactionModel.setSource("Testing Record");
        transactionModel.setBank(bankModelRepositoryCRUD.findById(1L).get());
        transactionModel.setAccount(accountModelRepositoryCRUD.findById(1L).get());
        transactionModel.setCurrency(currencyModelRepositoryCRUD.findById(1L).get());
        categoryTransactionModels.add(categoryTransactionModelRepositoryCRUD.findById(1L).get());
        transactionModel.setCategory(categoryTransactionModels);

        transactionModelRepositoryCRUD.save(transactionModel);

        return transactionModel;
    }

    private void saveAccountModel() {
        AccountModel accountModel = new AccountModel();
        accountModel.setName("test");
        accountModel.setAmount(new BigDecimal("1.00"));
        accountModel.setCurrencyModel(currencyModelRepositoryCRUD.findById(1L).orElse(null));
        accountModel.setAccountType(CASH);

        accountModelRepositoryCRUD.save(accountModel);
    }

    private void saveCurrencyModel() {
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setName("Dollar");
        currencyModel.setSymbol("D");
        currencyModel.setCode("USD");

        currencyModelRepositoryCRUD.save(currencyModel);
    }

    private void saveBankModel() {
        BankModel bankModel = new BankModel();
        bankModel.setName("VTB");

        bankModelRepositoryCRUD.save(bankModel);
    }

    private void saveCategoryModel() {
        CategoryTransactionModel categoryTransactionModel = new CategoryTransactionModel();
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
