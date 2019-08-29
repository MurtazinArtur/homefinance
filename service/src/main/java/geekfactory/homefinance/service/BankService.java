package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.dao.repository.BankRepository;

import java.util.Collection;
import java.util.Optional;

public class BankService implements Service<BankModel> {
    private BankRepository bankRepository = new BankRepository();

    public void setBankRepository(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public BankRepository getBankRepository() {
        return bankRepository;
    }

    @Override
    public Optional<BankModel> findById(long id) {
        return bankRepository.findById(id);
    }

    @Override
    public Collection<BankModel> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public boolean remove(long id) {
        bankRepository.remove(id);
        return true;
    }

    @Override
    public void save(BankModel model) {
        bankRepository.save(model);
    }

    @Override
    public void update(BankModel model, long idRow) {
        bankRepository.update(model, idRow);
    }
}
