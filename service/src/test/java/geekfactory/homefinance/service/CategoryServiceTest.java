package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.repository.CategoryTransactionRepository;
import geekfactory.homefinance.dao.repository.ConnectionSupplier;
import geekfactory.homefinance.dao.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest extends Mockito {
    private static ConnectionSupplier connectionSupplier = new ConnectionSupplier();
    @Spy
    CategoryService spy;
    private CategoryTransactionModel categoryTransactionModel = new CategoryTransactionModel();
    @Mock
    private CategoryTransactionRepository categoryTransactionRepositoryMock;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeAll
    static void beforeAll() {
        connectionSupplier.getConnection();
    }

    @BeforeEach
    void beforeEach() {
        CurrencyRepository currencyRepository = new CurrencyRepository();
        categoryTransactionModel.setId((long) 5);
        categoryTransactionModel.setName("testModel");
        categoryTransactionModel.setParentCategory(new CategoryTransactionRepository().findById((long) 1).orElse(null));
    }

    @Test
    void testAccountService() {
        when(categoryService.findById(anyLong())).thenReturn(Optional.ofNullable(categoryTransactionModel));
        assertNotNull(categoryService);
        assertEquals(categoryTransactionModel, categoryService.findById((long) 5).get());
        // assertNotEquals(categoryTransactionModel, categoryService.findById((long) 6).get());

        assertNotNull(categoryTransactionRepositoryMock);
        verify(categoryTransactionRepositoryMock, times(1)).findById(anyLong());
        verify(categoryTransactionRepositoryMock, never()).findAll();
        verify(categoryTransactionRepositoryMock, never()).save(categoryTransactionModel);
        verify(categoryTransactionRepositoryMock, never()).remove(categoryTransactionModel.getId());
        // verify(categoryTransactionRepositoryMock, never()).update(categoryTransactionModel, anyLong());
    }

    @Test
    void testServiceMock() {
        when(categoryTransactionRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(categoryTransactionModel));
        assertNotNull(categoryTransactionRepositoryMock);
        assertEquals(categoryTransactionModel, categoryTransactionRepositoryMock.findById((long) 5).get());
        // assertNotEquals(categoryTransactionModel, categoryTransactionRepositoryMock.findById((long) 5).get());

        verify(categoryTransactionRepositoryMock, times(1)).findById(anyLong());
    }

    @Test
    void testWithSpy() {
        when(spy.findById(anyLong())).thenReturn(Optional.ofNullable(categoryTransactionModel));

        assertEquals(categoryTransactionModel, spy.findById((long) 5).get());
    }
}