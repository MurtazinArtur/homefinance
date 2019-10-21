package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.config.DaoConfiguration;
import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:delete_tables_ddl.sql")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_ddl.sql")
class CategoryTransactionRepositoryCRUDTest {

    @Autowired
    private CategoryTransactionRepositoryCRUD categoryTransactionModelRepositoryCRUD;

    @Test
    void TestContext(){
        assertNotNull(categoryTransactionModelRepositoryCRUD);
    }

    @Test
    @Transactional
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        assertEquals(createModel(), categoryTransactionModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @Transactional
    @DisplayName("running findAll test")
    void testFindAll(){
        Collection<CategoryTransactionModel> expectedList = createCollectionModels();
        Collection<CategoryTransactionModel> actualList = (categoryTransactionModelRepositoryCRUD.findAll());

        assertEquals(expectedList, actualList);

        int expected = 3;
        int actual = actualList.size();

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @DisplayName("running update test")
    void testUpdate() {
        createModel();

        CategoryTransactionModel categoryTransactionUpdate = categoryTransactionModelRepositoryCRUD.findById(1L).orElse(null);
        categoryTransactionUpdate.setName("testUpdate");
        categoryTransactionModelRepositoryCRUD.update(categoryTransactionUpdate);

        assertEquals(categoryTransactionUpdate, categoryTransactionModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @Transactional
    @DisplayName("running remove test")
    void testRemove(){
        createModel();

        CategoryTransactionModel categoryTransactionModel = categoryTransactionModelRepositoryCRUD.findById(1L).orElse(null);
        categoryTransactionModelRepositoryCRUD.remove(categoryTransactionModel);
        CategoryTransactionModel removedModel = categoryTransactionModelRepositoryCRUD.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private CategoryTransactionModel createModel() {
        CategoryTransactionModel categoryModel = new CategoryTransactionModel();
        categoryModel.setId(1l);
        categoryModel.setName("test1");
        categoryModel.setParentCategory(null);

        categoryTransactionModelRepositoryCRUD.save(categoryModel);
        return categoryModel;
    }

    private Collection<CategoryTransactionModel> createCollectionModels() {
        Collection<CategoryTransactionModel> collection = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            CategoryTransactionModel categoryModel = new CategoryTransactionModel();
            categoryModel.setId(Long.valueOf(i));
            categoryModel.setName("test");
            categoryModel.setParentCategory(null);

            collection.add(categoryModel);
            categoryTransactionModelRepositoryCRUD.save(categoryModel);
        }
        return collection;
    }
}