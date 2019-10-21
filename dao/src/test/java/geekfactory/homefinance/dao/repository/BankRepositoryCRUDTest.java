package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.config.DaoConfiguration;
import geekfactory.homefinance.dao.model.BankModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
@Sql(executionPhase= Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:delete_tables_ddl.sql")
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:init_ddl.sql")
class BankRepositoryCRUDTest {

    @Autowired
    private BankRepositoryCRUD bankRepositoryCRUD;

    @Test
    void TestContext(){
        assertNotNull(bankRepositoryCRUD);
    }

    @Test
    @Transactional
    @DisplayName("running save and findById test")
    void testSaveAndFind() {
        assertEquals(createModel(), bankRepositoryCRUD.findById(1L).get());
    }

    @Test
    @Transactional
    @DisplayName("running findAll test")
    void testFindAll() {
        List<BankModel> actualList = createCollectionModels();
        List expectedList = (List<BankModel>) bankRepositoryCRUD.findAll();

        assertEquals(expectedList, actualList);

        int expected = expectedList.size();
        int actual = 3;

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @DisplayName("running update test")
    void testUpdate() {
        createModel();

        BankModel bankUpdate = bankRepositoryCRUD.findById(1L).orElse(null);
        bankUpdate.setName("testUpdate");
        bankRepositoryCRUD.update(bankUpdate);

        assertEquals(bankUpdate, bankRepositoryCRUD.findById(1L).get());
    }

    @Test
    @Transactional
    @DisplayName("running remove test")
    void testRemove() {
        createModel();

        BankModel bankModel = bankRepositoryCRUD.findById(1L).orElse(null);
        bankRepositoryCRUD.remove(bankModel);
        BankModel removedModel = bankRepositoryCRUD.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private BankModel createModel() {
        BankModel bankModel = new BankModel();
        bankModel.setName("VTB");

        bankRepositoryCRUD.save(bankModel);
        return bankModel;
    }

    private List<BankModel> createCollectionModels(){
        List<BankModel> collection = new ArrayList<>();
        for (int i = 1; i <= 3 ; i++) {
            BankModel bankModel = new BankModel();
            bankModel.setName("VTB");

            collection.add(bankModel);
            bankRepositoryCRUD.save(bankModel);
        }
        return collection;
    }
}
