package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.BankModel;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BankRepositoryTest {
    private static final String CREATE_TBL = "CREATE TABLE bank_tbl\n" +
            "(\n" +
            "    id     INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
            "    name   VARCHAR(50)                    NOT NULL\n" +
            ")";
    private static final String REMOVE_TABLE = "DROP TABLE bank_tbl";
    private static ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private BankRepository bankRepository = new BankRepository();
    private static BankModel model = new BankModel();
    private static BankModel model1 = new BankModel();
    private static BankModel model2 = new BankModel();
    @BeforeAll
    static void beforeAll() {

        model.setName("VTB");
        model1.setName("VTB");
        model2.setName("VTB");
    }

    @BeforeEach
    void beforeEach(){
        Connection connection = connectionSupplierTest.getConnection();
        try {
            connection.prepareStatement(REMOVE_TABLE).executeUpdate();
            connection.prepareStatement(CREATE_TBL).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void afterAll() {
        connectionSupplierTest.getDisconnection();
    }

    @Test
    void TestContext(){
        assertNotNull(bankRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind() {
        bankRepository.save(model);
        assertEquals(model, bankRepository.findById((long) 1).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate() {
        bankRepository.save(model);
        assertNotNull(model);
        BankModel accountUpdate = bankRepository.findById((long) 1).orElse(null);
        accountUpdate.setName("testUpdate");
        bankRepository.update(accountUpdate, (long) 1);
        assertEquals(accountUpdate, bankRepository.findById((long) 1).get());
    }
    @Test
    @DisplayName("running findAll test")
    void testFindAll() {
        bankRepository.save(model);
        bankRepository.save(model1);
        bankRepository.save(model2);
        List expectedList = (List<BankModel>) bankRepository.findAll();
        List<BankModel> actualList = new ArrayList<>();
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
    void testRemove() {
        bankRepository.save(model);
        bankRepository.save(model1);
        bankRepository.save(model2);
        BankModel bankModel = bankRepository.findById((long) 1).orElse(null);
        bankRepository.remove(bankModel.getId());
        BankModel removedModel = bankRepository.findById((long) 1).orElse(null);
        assertNull(removedModel);
    }
}
