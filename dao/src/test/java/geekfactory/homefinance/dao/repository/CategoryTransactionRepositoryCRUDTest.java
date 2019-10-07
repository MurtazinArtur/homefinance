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

import java.util.Collection;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:delete_tables_ddl.sql")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_ddl.sql")
class CategoryTransactionRepositoryCRUDTest {

    @Autowired
    private RepositoryCRUD<CategoryTransactionModel, Long> categoryTransactionModelRepositoryCRUD;

    @Test
    void TestContext(){
        assertNotNull(categoryTransactionModelRepositoryCRUD);
    }

    @Test
    @DisplayName("running save and findById test")
    void testSaveAndFind(){
        categoryTransactionModelRepositoryCRUD.save(createModel());

        assertEquals(createModel(), categoryTransactionModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @DisplayName("running update test")
    void testUpdate(){
        categoryTransactionModelRepositoryCRUD.save(createModel());

        CategoryTransactionModel categoryTransactionUpdate = categoryTransactionModelRepositoryCRUD.findById(1L).orElse(null);
        categoryTransactionUpdate.setName("testUpdate");
        categoryTransactionModelRepositoryCRUD.update(categoryTransactionUpdate, 1L);

        assertEquals(categoryTransactionUpdate, categoryTransactionModelRepositoryCRUD.findById(1L).get());
    }

    @Test
    @DisplayName("running findAll test")
    void testFindAll(){
        for (int i = 0; i < createCollectionModels().size(); i++) {
            categoryTransactionModelRepositoryCRUD.save(createCollectionModels().iterator().next());
        }

        Collection<CategoryTransactionModel> actualList = (categoryTransactionModelRepositoryCRUD.findAll());
        Collection<CategoryTransactionModel> expectedList = createCollectionModels();

        assertEquals(expectedList, actualList);

        int expected = 3;
        int actual = actualList.size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("running remove test")
    void testRemove(){
        categoryTransactionModelRepositoryCRUD.save(createModel());
        CategoryTransactionModel categoryTransactionModel = categoryTransactionModelRepositoryCRUD.findById(1L).orElse(null);
        categoryTransactionModelRepositoryCRUD.remove(categoryTransactionModel.getId());
        CategoryTransactionModel removedModel = categoryTransactionModelRepositoryCRUD.findById(1L).orElse(null);

        assertNull(removedModel);
    }

    private CategoryTransactionModel createModel() {
        CategoryTransactionModel model = new CategoryTransactionModel();
        model.setId(1l);
        model.setName("test1");
        model.setParentCategory(null);

        return model;
    }

    private Collection<CategoryTransactionModel> createCollectionModels() {
        Collection<CategoryTransactionModel> colllection = new TreeSet<>();
        for (int i = 1; i <= 3; i++) {
            CategoryTransactionModel model = new CategoryTransactionModel();
            model.setId(Long.valueOf(i));
            model.setName("test");
            model.setParentCategory(null);

            colllection.add(model);
        }
        return colllection;
    }
}