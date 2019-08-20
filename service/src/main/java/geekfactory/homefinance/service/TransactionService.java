package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.TransactionRepository;

import java.util.Collection;
import java.util.Optional;

public class TransactionService implements Service<TransactionModel> {
    TransactionRepository transactionRepository = new TransactionRepository();

    @Override
    public Optional<TransactionModel> findById(long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Collection<TransactionModel> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public boolean remove(long id) {
        transactionRepository.remove(id);
        return true;
    }

    @Override
    public void save(TransactionModel model) {
        transactionRepository.save(model);
    }

    @Override
    public void update(TransactionModel model, long idRow) {
        transactionRepository.update(model, idRow);
    }
}
