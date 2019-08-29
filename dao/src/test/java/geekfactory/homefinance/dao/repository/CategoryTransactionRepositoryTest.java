package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTransactionRepositoryTest {
    private static final String CREATE_TBL = "CREATE TABLE category_tbl\n" +
            "(\n" +
            "    id   INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
            "    name VARCHAR(50)                    NOT NULL,\n" +
            "    parent_category INT\n" +
            ")";
    private static final String REMOVE_TABLE = "DROP TABLE category_tbl";
    private ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private CategoryTransactionRepository categoryTransactionRepository = new CategoryTransactionRepository();
    private CategoryTransactionModel model = createModel();
    private CategoryTransactionModel model2 = createModel();
    private CategoryTransactionModel model3 = createModel();

    @BeforeEach
    void beforeEach() throws SQLException {
        Connection connection = connectionSupplierTest.getConnection();
            connection.prepareStatement(REMOVE_TABLE).executeUpdate();
            connection.prepareStatement(CREATE_TBL).executeUpdate();
    }

    @Test
    void TestContext(){
        assertNotNull(categoryTransactionRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        categoryTransactionRepository.save(model);

        assertEquals(model, categoryTransactionRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate(){
        categoryTransactionRepository.save(model);

        CategoryTransactionModel categoryTransactionUpdate = categoryTransactionRepository.findById(1L).orElse(null);
        categoryTransactionUpdate.setName("testUpdate");
        categoryTransactionRepository.update(categoryTransactionUpdate, 1L);

        assertEquals(categoryTransactionUpdate, categoryTransactionRepository.findById(1L).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll(){
        categoryTransactionRepository.save(model);
        categoryTransactionRepository.save(model2);
        categoryTransactionRepository.save(model3);
        Collection<CategoryTransactionModel> expectedList = (categoryTransactionRepository.findAll());

        Collection<CategoryTransactionModel> actualList = new HashSet<>();
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
            categoryTransactionRepository.save(model);
        }

        CategoryTransactionModel categoryTransactionModel = categoryTransactionRepository.findById(1L).orElse(null);
        categoryTransactionRepository.remove(categoryTransactionModel.getId());
        CategoryTransactionModel removedModel = categoryTransactionRepository.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private CategoryTransactionModel createModel() {
        CategoryTransactionModel model = new CategoryTransactionModel();
        model.setName("test1");
        model.setParentCategory(null);

        return model;
    }
}