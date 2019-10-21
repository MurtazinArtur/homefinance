package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.TransactionRepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("transactionService")
public class TransactionService {

    @Autowired
    private TransactionRepositoryCRUD transactionRepositoryCRUD;

    public Optional<TransactionModel> findById(Long id) {
        return Optional.ofNullable(transactionRepositoryCRUD.findById(id).get());
    }

    public Optional<TransactionModel> findByName(String name) {
        return null;
    }

    public Collection<TransactionModel> findAll() {
        return transactionRepositoryCRUD.findAll();
    }

    public void remove(TransactionModel model) {
        transactionRepositoryCRUD.remove(model);
    }

    public void save(TransactionModel model) {
        transactionRepositoryCRUD.save(model);
    }

    public TransactionModel update(TransactionModel model) {
        transactionRepositoryCRUD.update(model);
        return model;
    }
}