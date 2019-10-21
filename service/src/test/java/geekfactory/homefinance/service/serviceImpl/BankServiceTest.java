package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.service.config.ServiceConfiguration;
import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.dao.repository.BankRepositoryCRUD;
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
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class BankServiceTest {
    private BankRepositoryCRUD bankRepositoryMock = mock(BankRepositoryCRUD.class);
    @InjectMocks
    @Autowired
    private BankService bankService;

    @Test
    public void testBankService() {
        when(bankRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(createModel()));

        assertNotNull(bankRepositoryMock);
        assertEquals(createModel(), bankRepositoryMock.findById(1L).get());

        verify(bankRepositoryMock, times(1)).findById(anyLong());
        verify(bankRepositoryMock, never()).findAll();
        verify(bankRepositoryMock, never()).save(createModel());
        verify(bankRepositoryMock, never()).remove(createModel());
        verify(bankRepositoryMock, never()).update(eq(createModel()));
    }

    private BankModel createModel() {
        BankModel bankModel = new BankModel();
        bankModel.setId(1L);
        bankModel.setName("testUpdate");

        return bankModel;
    }
}
