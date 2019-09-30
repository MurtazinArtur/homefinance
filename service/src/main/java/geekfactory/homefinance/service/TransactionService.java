package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
public class TransactionService implements ServiceCRUD<TransactionModel, Long> {

    @Autowired
    private Repository<TransactionModel, Long> transactionRepository;

    @Override
    public Optional<TransactionModel> findById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Collection<TransactionModel> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        transactionRepository.remove(id);
        return true;
    }

    @Override
    public void save(TransactionModel model) {
        transactionRepository.save(model);
    }

    @Override
    public void update(TransactionModel model, Long idRow) {
        transactionRepository.update(model, idRow);
    }
}
