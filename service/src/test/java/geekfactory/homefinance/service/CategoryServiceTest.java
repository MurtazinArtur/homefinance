package geekfactory.homefinance.service;

import geekfactory.homefinance.config.ServiceConfiguration;
import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.repository.CategoryTransactionRepositoryCRUD;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class CategoryServiceTest {
    private CategoryTransactionRepositoryCRUD categoryTransactionRepositoryMock = mock(CategoryTransactionRepositoryCRUD.class);
    @InjectMocks
    @Autowired
    private CategoryService categoryService;

    @Test
    public void testCategoryService() {
        when(categoryTransactionRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(createModel()));

        assertNotNull(categoryTransactionRepositoryMock);
        assertEquals(createModel(), categoryTransactionRepositoryMock.findById(2L).get());

        verify(categoryTransactionRepositoryMock, never()).findAll();
        verify(categoryTransactionRepositoryMock, never()).save(createModel());
        verify(categoryTransactionRepositoryMock, never()).remove(createModel().getId());
        verify(categoryTransactionRepositoryMock, never()).update(eq(createModel()), anyLong());
    }

    private CategoryTransactionModel createModel() {
        CategoryTransactionModel categoryTransactionModel = new CategoryTransactionModel();
        categoryTransactionModel.setId(2L);
        categoryTransactionModel.setName("testModel");
        categoryTransactionModel.setParentCategory(null);

        return categoryTransactionModel;
    }
}