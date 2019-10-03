package geekfactory.homefinance.service;

import geekfactory.homefinance.config.ServiceConfiguration;
import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.model.AccountType;
import geekfactory.homefinance.dao.model.CurrencyModel;
import geekfactory.homefinance.dao.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class AccountServiceTest {
    private AccountRepository accountRepositoryMock = mock(AccountRepository.class);
    @InjectMocks
    @Autowired
    private ServiceCRUD<AccountModel, Long> accountService;

    @Test
    public void testAccountService() {

        when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(createAccountModel()));

        assertNotNull(accountRepositoryMock);
        assertEquals(createAccountModel(), accountRepositoryMock.findById(1L).get());

        verify(accountRepositoryMock, times(1)).findById(anyLong());
        verify(accountRepositoryMock, never()).findAll();
        verify(accountRepositoryMock, never()).save(createAccountModel());
        verify(accountRepositoryMock, never()).remove(createAccountModel().getId());
        verify(accountRepositoryMock, never()).update(eq(createAccountModel()), anyLong());
    }

    private AccountModel createAccountModel() {
        AccountModel accountModel = new AccountModel();
        accountModel.setId(1L);
        accountModel.setName("testModel");
        accountModel.setAmount(new BigDecimal("5.00"));
        accountModel.setAccountType(AccountType.CASH);
        accountModel.setCurrencyModel(createCurrencyModel());

        return accountModel;
    }

    private CurrencyModel createCurrencyModel() {
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setId(1L);
        currencyModel.setName("testCurrency");
        currencyModel.setCode("TC");
        currencyModel.setSymbol("T");
        return currencyModel;
    }
}