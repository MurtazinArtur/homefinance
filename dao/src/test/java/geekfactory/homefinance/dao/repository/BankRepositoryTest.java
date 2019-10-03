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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
@Sql(executionPhase= Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:delete_tables_ddl.sql")
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:init_ddl.sql")
class BankRepositoryTest {

    @Autowired
    private Repository<BankModel, Long> bankRepository;

    @Test
    void TestContext(){
        assertNotNull(bankRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind() {
        bankRepository.save(createModel());

        assertEquals(createModel(), bankRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate() {
        bankRepository.save(createModel());

        BankModel accountUpdate = bankRepository.findById(1L).orElse(null);
        accountUpdate.setName("testUpdate");
        bankRepository.update(accountUpdate, 1L);

        assertEquals(accountUpdate, bankRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll() {
        for (int i = 0; i < createCollectionModels().size(); i++){
            bankRepository.save(createCollectionModels().get(i));
        }
        List expectedList = (List<BankModel>) bankRepository.findAll();
        List<BankModel> actualList = createCollectionModels();
        assertEquals(expectedList, actualList);

        int expected = expectedList.size();
        int actual = 3;

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("running remove test")
    void testRemove() {
        bankRepository.save(createModel());
        BankModel bankModel = bankRepository.findById(1L).orElse(null);
        bankRepository.remove(bankModel.getId());
        BankModel removedModel = bankRepository.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private BankModel createModel() {
        BankModel model = new BankModel();
        model.setId(1L);
        model.setName("VTB");
        return model;
    }

    private List<BankModel> createCollectionModels(){
        List<BankModel> colllection = new ArrayList<>();
        for (int i = 1; i <= 3 ; i++) {
            BankModel model = new BankModel();
            model.setId(Long.valueOf(i));
            model.setName("VTB");
            colllection.add(model);
        }
        return colllection;
    }
}
