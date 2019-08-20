package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CategoryTransactionRepositoryTest {
    private static final String CREATE_TBL = "CREATE TABLE category_tbl\n" +
            "(\n" +
            "    id   INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
            "    name VARCHAR(50)                    NOT NULL,\n" +
            "    parent_category INT\n" +
            ")";
    private static final String REMOVE_TABLE = "DROP TABLE category_tbl";
    private static ConnectionSupplier connectionSupplierTest = new ConnectionSupplier();
    private CategoryTransactionRepository categoryTransactionRepository = new CategoryTransactionRepository();
    CategoryTransactionModel model = new CategoryTransactionModel();
    CategoryTransactionModel model1 = new CategoryTransactionModel();
    CategoryTransactionModel model2 = new CategoryTransactionModel();

    @BeforeAll
    static void beforeAll() {
        Connection connection = connectionSupplierTest.getConnection();
        try {
            connection.prepareStatement(REMOVE_TABLE).executeUpdate();
            connection.prepareStatement(CREATE_TBL).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void beforeEach(){
        model.setId((long)1);
        model.setName("test");
        model.setParentCategory(categoryTransactionRepository.findById((long) 1).orElse(null));

        model1.setName("test1");
        model1.setParentCategory(categoryTransactionRepository.findById((long) 1).orElse(null));

        model2.setName("test2");
        model2.setParentCategory(categoryTransactionRepository.findById((long) 1).orElse(null));
    }

    @Test
    void TestContext(){
        assertNotNull(categoryTransactionRepository);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        categoryTransactionRepository.save(model);
        System.out.println(model.toString());
        assertEquals(model, categoryTransactionRepository.findById((long) 4).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate(){
        assertNotNull(model);
        CategoryTransactionModel categoryTransactionUpdate = categoryTransactionRepository.findById((long) 2).orElse(null);
        categoryTransactionUpdate.setName("testUpdate");
        categoryTransactionRepository.update(categoryTransactionUpdate,(long) 2);
        assertEquals(categoryTransactionUpdate, categoryTransactionRepository.findById((long) 2).get());
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
        CategoryTransactionModel categoryTransactionModel = categoryTransactionRepository.findById((long) 1).orElse(null);
        categoryTransactionRepository.remove(categoryTransactionModel.getId());
        CategoryTransactionModel removedModel = categoryTransactionRepository.findById((long) 1).orElse(null);
        assertNull(removedModel);
    }
}