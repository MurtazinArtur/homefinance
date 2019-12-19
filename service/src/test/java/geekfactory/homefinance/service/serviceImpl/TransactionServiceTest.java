package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.*;
import geekfactory.homefinance.dao.repository.TransactionRepositoryCRUD;
import geekfactory.homefinance.service.config.ServiceConfiguration;
import geekfactory.homefinance.service.converter.TransactionModelConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class TransactionServiceTest {
    private TransactionRepositoryCRUD transactionRepositoryMock = mock(TransactionRepositoryCRUD.class);

    @InjectMocks
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionModelConverter transactionModelConverter;

    @Test
    public void testTransactionService() {

        when(transactionRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(createTransactionModel()));

        assertNotNull(transactionRepositoryMock);
        assertEquals(createTransactionModel(), transactionRepositoryMock.findById(1L).get());

        verify(transactionRepositoryMock, times(1)).findById(anyLong());
        verify(transactionRepositoryMock, never()).findAll();
        verify(transactionRepositoryMock, never()).save(createTransactionModel());
        verify(transactionRepositoryMock, never()).remove(createTransactionModel());
        verify(transactionRepositoryMock, never()).update(eq(createTransactionModel()));
    }

    private TransactionModel createTransactionModel() {
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setAmount(new BigDecimal("3.15"));
        transactionModel.setDate(LocalDate.now());
        transactionModel.setSource("TestSource");
        transactionModel.setBank(createBankModel());
        transactionModel.setAccount(createAccountModel());
        transactionModel.setCurrency(createCurrencyModel());

        return transactionModel;
    }

    private BankModel createBankModel() {
        BankModel bankModel = new BankModel();
        bankModel.setName("testUpdate");

        return bankModel;
    }

    private AccountModel createAccountModel() {
        AccountModel accountModel = new AccountModel();
        accountModel.setName("testModel");
        accountModel.setAmount(new BigDecimal("5.00"));
        accountModel.setAccountType(AccountType.CASH);
        accountModel.setCurrencyModel(createCurrencyModel());

        return accountModel;
    }

    private CurrencyModel createCurrencyModel() {
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setName("testCurrency");
        currencyModel.setCode("TC");
        currencyModel.setSymbol("T");

        return currencyModel;
    }
}
