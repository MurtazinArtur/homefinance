package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.model.AccountType;
import geekfactory.homefinance.dao.repository.AccountRepository;
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

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest extends Mockito{
    private static ConnectionSupplier connectionSupplier = new ConnectionSupplier();
    private AccountModel accountModel = new AccountModel();

    @Spy
    AccountService spy;

    @Mock
    private AccountRepository accountRepositoryMock;

    @InjectMocks
    private AccountService accountService;

    @BeforeAll
    static void beforeAll() {
        connectionSupplier.getConnection();
    }

    @BeforeEach
    void beforeEach() {
        CurrencyRepository currencyRepository = new CurrencyRepository();
        accountModel.setId((long) 5);
        accountModel.setName("testModel");
        accountModel.setAmount(BigDecimal.valueOf(1));
        accountModel.setAccountType(AccountType.CASH);
        accountModel.setCurrencyModel(currencyRepository.findById((long) 1).orElse(null));


    }

    @Test
    void testAccountService() {
        when(accountService.findById(anyLong())).thenReturn(Optional.ofNullable(accountModel));
        assertNotNull(accountService);
        assertEquals(accountModel, accountService.findById((long) 5).get());
       // assertNotEquals(accountModel, accountService.findById((long) 6).get());

        assertNotNull(accountRepositoryMock);
        verify(accountRepositoryMock, times(1)).findById(anyLong());
        verify(accountRepositoryMock, never()).findAll();
        verify(accountRepositoryMock, never()).save(accountModel);
        verify(accountRepositoryMock, never()).remove(accountModel.getId());
       // verify(accountRepositoryMock, never()).update(accountModel, anyLong());
    }

    @Test
    void testServiceMock() {
        when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(accountModel));
        assertNotNull(accountRepositoryMock);
        assertEquals(accountModel, accountRepositoryMock.findById((long) 5).get());
       // assertNotEquals(accountModel, accountRepositoryMock.findById((long) 5).get());

        verify(accountRepositoryMock, times(1)).findById(anyLong());
    }

    @Test
    void testWithSpy(){
        when(spy.findById(anyLong())).thenReturn(Optional.ofNullable(accountModel));

        assertEquals(accountModel, spy.findById((long) 5).get());
    }
}
