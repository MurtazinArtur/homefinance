package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.RepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("transactionService")
public class TransactionService implements ServiceCRUD<TransactionModel, Long> {

    @Autowired
    private RepositoryCRUD<TransactionModel, Long> transactionRepositoryCRUD;

    @Override
    public Optional<TransactionModel> findById(Long id) {
        return transactionRepositoryCRUD.findById(id);
    }

    @Override
    public Collection<TransactionModel> findAll() {
        return transactionRepositoryCRUD.findAll();
    }

    @Override
    public boolean remove(Long id) {
        transactionRepositoryCRUD.remove(id);
        return true;
    }

    @Override
    public void save(TransactionModel model) {
        transactionRepositoryCRUD.save(model);
    }

    @Override
    public void update(TransactionModel model, Long idRow) {
        transactionRepositoryCRUD.update(model, idRow);
    }
}
