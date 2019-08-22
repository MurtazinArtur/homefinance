package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.*;
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
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest extends Mockito {
    private static ConnectionSupplier connectionSupplier = new ConnectionSupplier();
    @Spy
    TransactionService spy;
    private TransactionModel transactionModel = new TransactionModel();
    @Mock
    private TransactionRepository transactionRepositoryMock;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeAll
    static void beforeAll() {
        connectionSupplier.getConnection();
    }

    @BeforeEach
    void beforeEach() {
        BankRepository bankRepository = new BankRepository();
        AccountRepository accountRepository = new AccountRepository();
        CurrencyRepository currencyRepository = new CurrencyRepository();

        transactionModel.setId((long) 5);
        transactionModel.setAmount(BigDecimal.valueOf(3.15));
        transactionModel.setDate(LocalDate.now());
        transactionModel.setSource("TestSource");
        transactionModel.setBank(bankRepository.findById((long) 1).get());
        transactionModel.setAccount(accountRepository.findById((long) 1).get());
        transactionModel.setCurrency(currencyRepository.findById((long) 1).get());
    }

    @Test
    void testAccountService() {
        when(transactionService.findById(anyLong())).thenReturn(Optional.ofNullable(transactionModel));
        assertNotNull(transactionService);
        assertEquals(transactionModel, transactionService.findById((long) 5).get());
        // assertNotEquals(accountModel, accountService.findById((long) 6).get());

        assertNotNull(transactionRepositoryMock);
        verify(transactionRepositoryMock, times(1)).findById(anyLong());
        verify(transactionRepositoryMock, never()).findAll();
        verify(transactionRepositoryMock, never()).save(transactionModel);
        verify(transactionRepositoryMock, never()).remove(transactionModel.getId());
        // verify(accountRepositoryMock, never()).update(accountModel, anyLong());
    }

    @Test
    void testServiceMock() {
        when(transactionRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(transactionModel));
        assertNotNull(transactionRepositoryMock);
        assertEquals(transactionModel, transactionRepositoryMock.findById((long) 5).get());
        // assertNotEquals(accountModel, accountRepositoryMock.findById((long) 5).get());

        verify(transactionRepositoryMock, times(1)).findById(anyLong());
    }

    @Test
    void testWithSpy() {
        when(spy.findById(anyLong())).thenReturn(Optional.ofNullable(transactionModel));

        assertEquals(transactionModel, spy.findById((long) 5).get());
    }

}
