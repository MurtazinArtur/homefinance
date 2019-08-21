package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTransactionRepositoryTest {
    private static final String CREATE_TBL = "CREATE TABLE category_tbl\n" +
            "(\n" +
            "    id   INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
            "    name VARCHAR(50)                    NOT NULL,\n" +
            "    parent_category INT\n" +
            ")";
    private static final String REMOVE_TABLE = "DROP TABLE category_tbl";
    private static ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private static CategoryTransactionRepository categoryTransactionRepository = new CategoryTransactionRepository();
    private static CategoryTransactionModel model = new CategoryTransactionModel();
    private static CategoryTransactionModel model1 = new CategoryTransactionModel();
    private static CategoryTransactionModel model2 = new CategoryTransactionModel();

    @BeforeAll
    static void beforeAll() {
        model.setName("test");
        model.setParentCategory(categoryTransactionRepository.findById((long) 1).orElse(null));

        model1.setName("test1");
        model1.setParentCategory(categoryTransactionRepository.findById((long) 1).orElse(null));

        model2.setName("test2");
        model2.setParentCategory(categoryTransactionRepository.findById((long) 1).orElse(null));

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
        assertNotNull(categoryTransactionRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        categoryTransactionRepository.save(model);
        assertEquals(model, categoryTransactionRepository.findById((long) 1).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate(){
        categoryTransactionRepository.save(model);
        assertNotNull(model);
        CategoryTransactionModel categoryTransactionUpdate = categoryTransactionRepository.findById((long) 1).orElse(null);
        categoryTransactionUpdate.setName("testUpdate");
        categoryTransactionRepository.update(categoryTransactionUpdate, (long) 1);
        assertEquals(categoryTransactionUpdate, categoryTransactionRepository.findById((long) 1).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll(){
        categoryTransactionRepository.save(model);
        categoryTransactionRepository.save(model1);
        categoryTransactionRepository.save(model2);
        Collection<CategoryTransactionModel> expectedList = (categoryTransactionRepository.findAll());
        Collection<CategoryTransactionModel> actualList = new HashSet<>();
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
        categoryTransactionRepository.save(model);
        categoryTransactionRepository.save(model1);
        categoryTransactionRepository.save(model2);
        CategoryTransactionModel categoryTransactionModel = categoryTransactionRepository.findById((long) 1).orElse(null);
        categoryTransactionRepository.remove(categoryTransactionModel.getId());
        CategoryTransactionModel removedModel = categoryTransactionRepository.findById((long) 1).orElse(null);
        assertNull(removedModel);
    }
}