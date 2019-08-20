package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.BankModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankRepositoryTest {
    private BankModel bankModel;
    ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();

    @BeforeAll
    public void beforeAll() {
        connectionSupplierTest.getConnection();
    }

    @BeforeEach
    public void beforeEach(){
        bankModel = new BankModel();
        bankModel.setName("Dollar");
    }

    @AfterAll
    public void afterAll() {
        connectionSupplierTest.getDisconnect();
    }

    @Test
    void saveTest(){
        BankRepository bankRepository = new BankRepository();
        bankRepository.save(bankModel);
        assertEquals(bankModel, bankRepository.findById((long)1));
    }
}
