package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.dao.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
public class BankService implements ServiceCRUD<BankModel, Long> {
    @Autowired
    private Repository<BankModel, Long> bankRepository;

    @Override
    public Optional<BankModel> findById(Long id) {
        return bankRepository.findById(id);
    }

    @Override
    public Collection<BankModel> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        bankRepository.remove(id);
        return true;
    }

    @Override
    public void save(BankModel model) {
        bankRepository.save(model);
    }

    @Override
    public void update(BankModel model, Long idRow) {
        bankRepository.update(model, idRow);
    }
}
