package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.TransactionRepositoryCRUD;
import geekfactory.homefinance.service.converter.TransactionModelConverter;
import geekfactory.homefinance.service.dto.TransactionDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("transactionService")
public class TransactionService {

    private TransactionModelConverter transactionModelConverter;
    private TransactionRepositoryCRUD transactionRepositoryCRUD;

    @Autowired
    public TransactionService(TransactionModelConverter transactionModelConverter,
                              TransactionRepositoryCRUD transactionRepositoryCRUD) {
        this.transactionModelConverter = transactionModelConverter;
        this.transactionRepositoryCRUD = transactionRepositoryCRUD;
    }

    public Optional<TransactionDtoModel> findById(Long id) {
        return Optional.ofNullable(transactionModelConverter.convertToTransactionDtoModel(transactionRepositoryCRUD.findById(id).get()));
    }

    public Optional<TransactionModel> findByName(String name) {
        return null;
    }

    public Collection<TransactionModel> findAll() {
        return transactionRepositoryCRUD.findAll();
    }

    public void remove(TransactionDtoModel transactionDtoModel) {
        transactionRepositoryCRUD.remove(transactionModelConverter.convertToTransactionModel(transactionDtoModel));
    }

    public void save(TransactionDtoModel transactionDtoModel) {
        transactionRepositoryCRUD.save(transactionModelConverter.convertToTransactionModel(transactionDtoModel));
    }

    public TransactionDtoModel update(TransactionDtoModel transactionDtoModel) {
        transactionRepositoryCRUD.update(transactionModelConverter.convertToTransactionModel(transactionDtoModel));

        return transactionDtoModel;
    }
}