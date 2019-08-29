package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.model.AccountType;
import geekfactory.homefinance.dao.repository.AccountRepository;
import geekfactory.homefinance.dao.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Test
    public void testAccountService() {

        AccountService accountService = new AccountService();
        accountService.setAccountRepository(mock(AccountRepository.class));

        when(accountService.findById(anyLong())).thenReturn(Optional.ofNullable(createModel()));

        assertNotNull(accountService);
        assertNotNull(accountService.getAccountRepository());
        assertEquals(createModel(), accountService.findById(5L).get());

        verify(accountService.getAccountRepository(), times(1)).findById(anyLong());
        verify(accountService.getAccountRepository(), never()).findAll();
        verify(accountService.getAccountRepository(), never()).save(createModel());
        verify(accountService.getAccountRepository(), never()).remove(createModel().getId());
        verify(accountService.getAccountRepository(), never()).update(eq(createModel()), anyLong());
    }

    private AccountModel createModel() {
        AccountModel accountModel = new AccountModel();
        CurrencyRepository currencyRepository = new CurrencyRepository();
        accountModel.setId(5L);
        accountModel.setName("testModel");
        accountModel.setAmount(BigDecimal.valueOf(5.0));
        accountModel.setAccountType(AccountType.CASH);
        accountModel.setCurrencyModel(currencyRepository.findById(1L).orElse(null));

        return accountModel;
    }
}