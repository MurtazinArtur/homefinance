package geekfactory.homefinance.service;

import geekfactory.homefinance.config.ServiceConfiguration;
import geekfactory.homefinance.dao.model.CurrencyModel;
import geekfactory.homefinance.dao.repository.CurrencyRepositoryCRUD;
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
public class CurrencyServiceTest {
    private CurrencyRepositoryCRUD currencyRepositoryMock = mock(CurrencyRepositoryCRUD.class);
    @InjectMocks
    @Autowired
    private CurrencyService currencyService;

    @Test
    public void testCurrencyService() {

        when(currencyRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(createModel()));

        assertNotNull(currencyRepositoryMock);
        assertEquals(createModel(), currencyRepositoryMock.findById(3L).get());

        verify(currencyRepositoryMock, never()).findAll();
        verify(currencyRepositoryMock, never()).save(createModel());
        verify(currencyRepositoryMock, never()).remove(createModel().getId());
        verify(currencyRepositoryMock, never()).update(eq(createModel()), anyLong());
    }

    private CurrencyModel createModel() {
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setId(3L);
        currencyModel.setName("testModel");
        currencyModel.setSymbol("T");
        currencyModel.setCode("TES");

        return currencyModel;
    }
}
