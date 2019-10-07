package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.dao.repository.RepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("bankService")
public class BankService implements ServiceCRUD<BankModel, Long> {
    @Autowired
    private RepositoryCRUD<BankModel, Long> bankRepositoryCRUD;

    @Override
    public Optional<BankModel> findById(Long id) {
        return bankRepositoryCRUD.findById(id);
    }

    @Override
    public Collection<BankModel> findAll() {
        return bankRepositoryCRUD.findAll();
    }

    @Override
    public boolean remove(Long id) {
        bankRepositoryCRUD.remove(id);
        return true;
    }

    @Override
    public void save(BankModel model) {
        bankRepositoryCRUD.save(model);
    }

    @Override
    public void update(BankModel model, Long idRow) {
        bankRepositoryCRUD.update(model, idRow);
    }
}
