package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionRepositoryTest {
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
    private static ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private static TransactionRepository transactionRepository = new TransactionRepository();
    private static BankRepository bankRepository = new BankRepository();
    private static AccountRepository accountRepository = new AccountRepository();
    private static CurrencyRepository currencyRepository = new CurrencyRepository();
    private static CategoryTransactionRepository categoryTransactionRepository = new CategoryTransactionRepository();
    private static Collection<CategoryTransactionModel> categoryTransactionModels= new HashSet<>();
    private static TransactionModel model = new TransactionModel();
    private static TransactionModel model1 = new TransactionModel();
    private static TransactionModel model2 = new TransactionModel();


    @BeforeAll
    static void beforeAll() {

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

        model1.setAmount(BigDecimal.valueOf(35.18));
        model1.setDate(LocalDate.now());
        model1.setSource("Testing Record");
        model1.setBank(bankModel.get());
        model1.setAccount(accountModel.get());
        model1.setCurrency(currencyModel.get());
        categoryTransactionModels.add(categoryTransactionModel.get());
        model1.setCategory(categoryTransactionModels);

        model2.setAmount(BigDecimal.valueOf(35.18));
        model2.setDate(LocalDate.now());
        model2.setSource("Testing Record");
        model2.setBank(bankModel.get());
        model2.setAccount(accountModel.get());
        model2.setCurrency(currencyModel.get());
        categoryTransactionModels.add(categoryTransactionModel.get());
        model2.setCategory(categoryTransactionModels);
    }

    @BeforeEach
    void beforeEach() {
        Connection connection = connectionSupplierTest.getConnection();
        try {
            connection.prepareStatement(REMOVE_TABLE).executeUpdate();
            connection.prepareStatement(CREATE_TBL).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void TestContext(){
        assertNotNull(transactionRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        transactionRepository.save(model);
        assertEquals(model, transactionRepository.findById((long) 1).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate(){
        transactionRepository.save(model);
        assertNotNull(model);
        TransactionModel transactionUpdate = transactionRepository.findById((long) 1).orElse(null);
        transactionUpdate.setSource("testUpdate");
        transactionRepository.update(transactionUpdate, (long) 1);
        assertEquals(transactionUpdate, transactionRepository.findById((long) 1).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll(){
        transactionRepository.save(model);
        transactionRepository.save(model1);
        transactionRepository.save(model2);
        List<TransactionModel> expectedList = (List<TransactionModel>) transactionRepository.findAll();
        List<TransactionModel> actualList = new ArrayList<>();
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
    void testRemove(){
        transactionRepository.save(model);
        transactionRepository.save(model1);
        transactionRepository.save(model2);
        TransactionModel categoryTransactionModel = transactionRepository.findById((long) 1).orElse(null);
        transactionRepository.remove(categoryTransactionModel.getId());
        TransactionModel removedModel = transactionRepository.findById((long) 1).orElse(null);
        assertNull(removedModel);
    }
}
