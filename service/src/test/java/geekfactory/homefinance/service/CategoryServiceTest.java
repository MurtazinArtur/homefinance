package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.repository.CategoryTransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Test
    public void testCategoryService() {
        CategoryService categoryService = new CategoryService();
        categoryService.setCategoryTransactionRepository(mock(CategoryTransactionRepository.class));

        when(categoryService.findById(anyLong())).thenReturn(Optional.ofNullable(createModel()));

        assertNotNull(categoryService);
        assertNotNull(categoryService.getCategoryTransactionRepository());
        assertEquals(createModel(), categoryService.findById(2L).get());

        verify(categoryService.getCategoryTransactionRepository(), never()).findAll();
        verify(categoryService.getCategoryTransactionRepository(), never()).save(createModel());
        verify(categoryService.getCategoryTransactionRepository(), never()).remove(createModel().getId());
        verify(categoryService.getCategoryTransactionRepository(), never()).update(eq(createModel()), anyLong());
    }

    private CategoryTransactionModel createModel() {
        CategoryTransactionModel model = new CategoryTransactionModel();
        model.setId(2L);
        model.setName("testModel");
        model.setParentCategory(new CategoryTransactionRepository().findById(1L).orElse(null));

        return model;
    }
}