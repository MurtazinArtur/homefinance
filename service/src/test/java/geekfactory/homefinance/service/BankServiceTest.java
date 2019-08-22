package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.dao.repository.BankRepository;
import geekfactory.homefinance.dao.repository.ConnectionSupplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest extends Mockito {
    private static ConnectionSupplier connectionSupplier = new ConnectionSupplier();
    @Spy
    BankService spy;
    private BankModel bankModel = new BankModel();
    @Mock
    private BankRepository bankRepositoryMock;

    @InjectMocks
    private BankService bankService;

    @BeforeAll
    static void beforeAll() {
        connectionSupplier.getConnection();
    }

    @BeforeEach
    void beforeEach() {
        bankModel.setId((long) 5);
        bankModel.setName("testModel");
    }

    @Test
    void testAccountService() {
        when(bankService.findById(anyLong())).thenReturn(Optional.ofNullable(bankModel));
        assertNotNull(bankService);
        assertEquals(bankModel, bankService.findById((long) 5).get());
        // assertNotEquals(accountModel, accountService.findById((long) 6).get());

        assertNotNull(bankRepositoryMock);
        verify(bankRepositoryMock, times(1)).findById(anyLong());
        verify(bankRepositoryMock, never()).findAll();
        verify(bankRepositoryMock, never()).save(bankModel);
        verify(bankRepositoryMock, never()).remove(bankModel.getId());
        // verify(accountRepositoryMock, never()).update(accountModel, anyLong());
    }

    @Test
    void testServiceMock() {
        when(bankRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(bankModel));
        assertNotNull(bankRepositoryMock);
        assertEquals(bankModel, bankRepositoryMock.findById((long) 5).get());
        // assertNotEquals(accountModel, accountRepositoryMock.findById((long) 5).get());

        verify(bankRepositoryMock, times(1)).findById(anyLong());
    }

    @Test
    void testWithSpy() {
        when(spy.findById(anyLong())).thenReturn(Optional.ofNullable(bankModel));

        assertEquals(bankModel, spy.findById((long) 5).get());
    }
}
