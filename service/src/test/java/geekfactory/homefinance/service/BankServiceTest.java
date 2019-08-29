package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.dao.repository.BankRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {
    @Test
    public void testBankService() {
        BankService bankService = new BankService();
        bankService.setBankRepository(mock(BankRepository.class));

        when(bankService.findById(anyLong())).thenReturn(Optional.ofNullable(createModel()));

        assertNotNull(bankService);
        assertNotNull(bankService.getBankRepository());
        assertEquals(createModel(), bankService.findById(10L).get());

        verify(bankService.getBankRepository(), times(1)).findById(anyLong());
        verify(bankService.getBankRepository(), never()).findAll();
        verify(bankService.getBankRepository(), never()).save(createModel());
        verify(bankService.getBankRepository(), never()).remove(createModel().getId());
        verify(bankService.getBankRepository(), never()).update(eq(createModel()), anyLong());
    }

    private BankModel createModel() {
        BankModel bankModel = new BankModel();
        bankModel.setId(10L);
        bankModel.setName("testUpdate");

        return bankModel;
    }
}