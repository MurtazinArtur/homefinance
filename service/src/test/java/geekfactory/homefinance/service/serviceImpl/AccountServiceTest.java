package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.*;
import geekfactory.homefinance.dao.repository.AccountRepositoryCRUD;
import geekfactory.homefinance.service.config.ServiceConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class AccountServiceTest {
    private AccountRepositoryCRUD accountRepositoryMock = mock(AccountRepositoryCRUD.class);
    @InjectMocks
    @Autowired
    private AccountService accountService;

    @Test
    public void testAccountService() {

        when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(createAccountModel()));

        assertNotNull(accountRepositoryMock);
        Assertions.assertEquals(createAccountModel(), accountRepositoryMock.findById(1L).get());

        verify(accountRepositoryMock, times(1)).findById(anyLong());
        verify(accountRepositoryMock, never()).findAll();
        verify(accountRepositoryMock, never()).save(createAccountModel());
        verify(accountRepositoryMock, never()).remove(createAccountModel());
        verify(accountRepositoryMock, never()).update(eq(createAccountModel()));
    }

    private AccountModel createAccountModel() {
        AccountModel accountModel = new AccountModel();
        accountModel.setName("testModel");
        accountModel.setAmount(new BigDecimal("5.00"));
        accountModel.setAccountType(AccountType.CASH);
        accountModel.setCurrencyModel(createCurrencyModel());
        accountModel.setUserModel(createUserModel());

        return accountModel;
    }

    private CurrencyModel createCurrencyModel() {
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setName("testCurrency");
        currencyModel.setCode("TC");
        currencyModel.setSymbol("T");

        return currencyModel;
    }

    private UserModel createUserModel(){
        UserModel userModel = new UserModel();
        userModel.setUser("test");
        userModel.setPassword("test");
        userModel.setUserRole(UserRoles.USER);

        return userModel;
    }
}