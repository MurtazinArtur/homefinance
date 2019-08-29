package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.CurrencyModel;
import geekfactory.homefinance.dao.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Test
    public void testAccountService() {
        CurrencyService currencyService = new CurrencyService();
        currencyService.setCurrencyRepository(mock(CurrencyRepository.class));

        when(currencyService.findById(anyLong())).thenReturn(Optional.ofNullable(createModel()));

        assertNotNull(currencyService);
        assertNotNull(currencyService.getCurrencyRepository());
        assertEquals(createModel(), currencyService.findById(3L).get());

        verify(currencyService.getCurrencyRepository(), never()).findAll();
        verify(currencyService.getCurrencyRepository(), never()).save(createModel());
        verify(currencyService.getCurrencyRepository(), never()).remove(createModel().getId());
        verify(currencyService.getCurrencyRepository(), never()).update(eq(createModel()), anyLong());
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
