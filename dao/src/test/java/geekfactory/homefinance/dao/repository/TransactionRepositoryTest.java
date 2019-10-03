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
class TransactionRepositoryTest {

    @Autowired
    private Repository<TransactionModel, Long> transactionModelRepository;
    @Autowired
    private Repository<BankModel, Long> bankModelRepository;
    @Autowired
    private Repository<AccountModel, Long> accountModelRepository;
    @Autowired
    private Repository<CurrencyModel, Long> currencyModelRepository;
    @Autowired
    private Repository<CategoryTransactionModel, Long> categoryTransactionModelRepository;

    @Test
    void TestContext(){
        assertNotNull(transactionModelRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        transactionModelRepository.save(createModel());

        assertEquals(createModel(), transactionModelRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate(){
        transactionModelRepository.save(createModel());

        TransactionModel transactionUpdate = transactionModelRepository.findById(1L).orElse(null);
        transactionUpdate.setSource("testUpdate");
        transactionModelRepository.update(transactionUpdate, 1L);

        assertEquals(transactionUpdate, transactionModelRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll(){
        for (int i = 0; i < createCollectionModels().size(); i++) {
            transactionModelRepository.save(createCollectionModels().get(i));
        }
        List<TransactionModel> expectedList = (List<TransactionModel>) transactionModelRepository.findAll();
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
            model.setBank(bankModelRepository.findById(1L).get());
            model.setAccount(accountModelRepository.findById(1L).get());
            model.setCurrency(currencyModelRepository.findById(1L).get());
            categoryTransactionModels.add(categoryTransactionModelRepository.findById(1L).get());
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
        model.setBank(bankModelRepository.findById(1L).get());
        model.setAccount(accountModelRepository.findById(1L).get());
        model.setCurrency(currencyModelRepository.findById(1L).get());
        categoryTransactionModels.add(categoryTransactionModelRepository.findById(1L).get());
        model.setCategory(categoryTransactionModels);

        return model;
    }

    private void saveAccountModel() {
        AccountModel accountModel = new AccountModel();
        accountModel.setId(1L);
        accountModel.setName("test");
        accountModel.setAmount(new BigDecimal("1.00"));
        accountModel.setCurrencyModel(currencyModelRepository.findById(1L).orElse(null));
        accountModel.setAccountType(CASH);

        accountModelRepository.save(accountModel);
    }

    private void saveCurrencyModel() {
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setId(1L);
        currencyModel.setName("Dollar");
        currencyModel.setSymbol("D");
        currencyModel.setCode("USD");

        currencyModelRepository.save(currencyModel);
    }

    private void saveBankModel() {
        BankModel model = new BankModel();
        model.setId(1L);
        model.setName("VTB");

        bankModelRepository.save(model);
    }

    private void saveCategoryModel() {
        CategoryTransactionModel categoryTransactionModel = new CategoryTransactionModel();
        categoryTransactionModel.setId(1l);
        categoryTransactionModel.setName("test1");
        categoryTransactionModel.setParentCategory(null);

        categoryTransactionModelRepository.save(categoryTransactionModel);
    }

    private void saveAllModels() {
        saveBankModel();
        saveCurrencyModel();
        saveAccountModel();
        saveCategoryModel();
    }
}