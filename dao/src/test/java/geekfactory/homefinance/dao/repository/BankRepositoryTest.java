package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.BankModel;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankRepositoryTest {
    private static final String CREATE_TBL = "CREATE TABLE bank_tbl\n" +
            "(\n" +
            "    id   INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
            "    name VARCHAR(50)\n" +
            ")";
    private static final String REMOVE_TABLE = "DROP TABLE bank_tbl";
    private ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private BankRepository bankRepository = new BankRepository();
    private BankModel model = createModel();
    private BankModel model2 = createModel();
    private BankModel model3 = createModel();

    @BeforeEach
    void beforeEach() throws SQLException {
        Connection connection = connectionSupplierTest.getConnection();
            connection.prepareStatement(REMOVE_TABLE).executeUpdate();
            connection.prepareStatement(CREATE_TBL).executeUpdate();
    }

    @Test
    void TestContext(){
        assertNotNull(bankRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind() {
        bankRepository.save(model);

        assertEquals(model, bankRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate() {
        bankRepository.save(model);

        BankModel accountUpdate = bankRepository.findById(1L).orElse(null);
        accountUpdate.setName("testUpdate");
        bankRepository.update(accountUpdate, 1L);

        assertEquals(accountUpdate, bankRepository.findById(1L).get());
    }
    @Test
    @DisplayName("running findAll test")
    void testFindAll() {
        bankRepository.save(model);
        bankRepository.save(model2);
        bankRepository.save(model3);
        List expectedList = (List<BankModel>) bankRepository.findAll();
        List<BankModel> actualList = new ArrayList<>();
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
    void testRemove() {
        for (int i = 0; i < 3; i++) {
            bankRepository.save(createModel());
        }

        BankModel bankModel = bankRepository.findById((long) 1).orElse(null);
        bankRepository.remove(bankModel.getId());
        BankModel removedModel = bankRepository.findById((long) 1).orElse(null);

        assertNull(removedModel);
    }

    private BankModel createModel() {
        BankModel model = new BankModel();
        model.setName("VTB");
        return model;
    }
}
