package geekfactory.homefinance.dao.repository;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountRepositoryTest {
    private static ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private AccountRepository accountRepository = new AccountRepository();
    private CurrencyRepository currencyRepository = new CurrencyRepository();

    @BeforeAll
    private static void beforeAll(){
        connectionSupplierTest.getConnection();
       // connectionSupplierTest.initDB();
    }

    @BeforeEach
    private void beforeEach(){

    }

    @Test
    private void TestContext(){
        assertNotNull(accountRepository);
    }
}
