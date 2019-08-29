package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.AccountRepository;
import geekfactory.homefinance.dao.repository.BankRepository;
import geekfactory.homefinance.dao.repository.CurrencyRepository;
import geekfactory.homefinance.dao.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Test
    public void testAccountService() {
        TransactionService transactionService = new TransactionService();
        transactionService.setTransactionRepository(mock(TransactionRepository.class));

        when(transactionService.findById(anyLong())).thenReturn(Optional.ofNullable(createModel()));

        assertNotNull(transactionService);
        assertNotNull(transactionService.getTransactionRepository());
        assertEquals(createModel(), transactionService.findById(5L).get());

        verify(transactionService.getTransactionRepository(), times(1)).findById(anyLong());
        verify(transactionService.getTransactionRepository(), never()).findAll();
        verify(transactionService.getTransactionRepository(), never()).save(createModel());
        verify(transactionService.getTransactionRepository(), never()).remove(createModel().getId());
        verify(transactionService.getTransactionRepository(), never()).update(eq(createModel()), anyLong());
    }

    private TransactionModel createModel() {
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setId(5L);
        transactionModel.setAmount(BigDecimal.valueOf(3.15));
        transactionModel.setDate(LocalDate.now());
        transactionModel.setSource("TestSource");
        transactionModel.setBank(new BankRepository().findById(1L).get());
        transactionModel.setAccount(new AccountRepository().findById(1L).get());
        transactionModel.setCurrency(new CurrencyRepository().findById(1L).get());

        return transactionModel;
    }
}
