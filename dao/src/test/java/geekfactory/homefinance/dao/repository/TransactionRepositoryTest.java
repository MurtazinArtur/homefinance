package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryTest {
    private static final String CREATE_TBL = "CREATE TABLE transaction_tbl\n" +
            "(\n" +
            "    id          INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
            "    amount      DECIMAL(15, 2)                 NOT NULL,\n" +
            "    date        DATE                           NOT NULL,\n" +
            "    source      VARCHAR(50),\n" +
            "    bank_id     INT                            NOT NULL,\n" +
            "    account_id  INT                            NOT NULL,\n" +
            "    currency_id INT                            NOT NULL,\n" +
            "\n" +
            "    CONSTRAINT bank_fk FOREIGN KEY (bank_id) REFERENCES bank_tbl (id),\n" +
            "    CONSTRAINT account_fk FOREIGN KEY (account_id) REFERENCES account_tbl (id),\n" +
            "    CONSTRAINT currency_transaction_fk FOREIGN KEY (currency_id) REFERENCES currency_tbl (id)\n" +
            ")";
    private static final String REMOVE_TABLE = "DROP TABLE transaction_tbl";
    private ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private TransactionRepository transactionRepository = new TransactionRepository();
    private TransactionModel model = createModel();
    private TransactionModel model2 = createModel();
    private TransactionModel model3 = createModel();

    @BeforeEach
    void beforeEach() throws SQLException {
        Connection connection = connectionSupplierTest.getConnection();
            connection.prepareStatement(REMOVE_TABLE).executeUpdate();
            connection.prepareStatement(CREATE_TBL).executeUpdate();
    }

    @Test
    void TestContext(){
        assertNotNull(transactionRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        transactionRepository.save(model);

        assertEquals(model, transactionRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate(){
        transactionRepository.save(model);

        TransactionModel transactionUpdate = transactionRepository.findById(1L).orElse(null);
        transactionUpdate.setSource("testUpdate");
        transactionRepository.update(transactionUpdate, 1L);

        assertEquals(transactionUpdate, transactionRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll(){
        transactionRepository.save(model);
        transactionRepository.save(model2);
        transactionRepository.save(model3);
        List<TransactionModel> expectedList = (List<TransactionModel>) transactionRepository.findAll();

        List<TransactionModel> actualList = new ArrayList<>();
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
    void testRemove(){
        for (int i = 0; i < 3; i++) {
            transactionRepository.save(model);
        }

        TransactionModel categoryTransactionModel = transactionRepository.findById(1L).orElse(null);
        transactionRepository.remove(categoryTransactionModel.getId());
        TransactionModel removedModel = transactionRepository.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private TransactionModel createModel() {
        TransactionModel model = new TransactionModel();
        BankRepository bankRepository = new BankRepository();
        AccountRepository accountRepository = new AccountRepository();
        CurrencyRepository currencyRepository = new CurrencyRepository();
        CategoryTransactionRepository categoryTransactionRepository = new CategoryTransactionRepository();
        Collection<CategoryTransactionModel> categoryTransactionModels = new HashSet<>();
        Optional<CategoryTransactionModel> categoryTransactionModel = categoryTransactionRepository.findById(1L);
        Optional<BankModel> bankModel = bankRepository.findById(1L);
        Optional<AccountModel> accountModel = accountRepository.findById(1L);
        Optional<CurrencyModel> currencyModel = currencyRepository.findById(1L);

        model.setAmount(BigDecimal.valueOf(35.18));
        model.setDate(LocalDate.now());
        model.setSource("Testing Record");
        model.setBank(bankModel.get());
        model.setAccount(accountModel.get());
        model.setCurrency(currencyModel.get());
        categoryTransactionModels.add(categoryTransactionModel.get());
        model.setCategory(categoryTransactionModels);

        return model;
    }
}