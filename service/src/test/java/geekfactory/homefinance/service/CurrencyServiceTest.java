package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.CurrencyModel;
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
public class CurrencyServiceTest extends Mockito {
    private static ConnectionSupplier connectionSupplier = new ConnectionSupplier();
    @Spy
    CurrencyService spy;
    private CurrencyModel currencyModel = new CurrencyModel();
    @Mock
    private CurrencyRepository currencyRepositoryMock;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeAll
    static void beforeAll() {
        connectionSupplier.getConnection();
    }

    @BeforeEach
    void beforeEach() {
        currencyModel.setId((long) 5);
        currencyModel.setName("testModel");
        currencyModel.setSymbol("T");
        currencyModel.setCode("TES");
    }

    @Test
    void testAccountService() {
        when(currencyService.findById(anyLong())).thenReturn(Optional.ofNullable(currencyModel));
        assertNotNull(currencyService);
        assertEquals(currencyModel, currencyService.findById((long) 5).get());
        // assertNotEquals(accountModel, accountService.findById((long) 6).get());

        assertNotNull(currencyRepositoryMock);
        verify(currencyRepositoryMock, times(1)).findById(anyLong());
        verify(currencyRepositoryMock, never()).findAll();
        verify(currencyRepositoryMock, never()).save(currencyModel);
        verify(currencyRepositoryMock, never()).remove(currencyModel.getId());
        // verify(accountRepositoryMock, never()).update(accountModel, anyLong());
    }

    @Test
    void testServiceMock() {
        when(currencyRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(currencyModel));
        assertNotNull(currencyRepositoryMock);
        assertEquals(currencyModel, currencyRepositoryMock.findById((long) 5).get());
        // assertNotEquals(accountModel, accountRepositoryMock.findById((long) 5).get());

        verify(currencyRepositoryMock, times(1)).findById(anyLong());
    }

    @Test
    void testWithSpy() {
        when(spy.findById(anyLong())).thenReturn(Optional.ofNullable(currencyModel));

        assertEquals(currencyModel, spy.findById((long) 5).get());
    }

}
