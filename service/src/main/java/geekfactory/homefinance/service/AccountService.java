package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
public class AccountService implements ServiceCRUD<AccountModel, Long> {

    @Autowired
    private Repository<AccountModel, Long> accountRepository;

    @Override
    public Optional<AccountModel> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Collection<AccountModel> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        accountRepository.remove(id);
        return true;
    }

    @Override
    public void save(AccountModel model) {
        accountRepository.save(model);
    }

    @Override
    public void update(AccountModel model, Long idRow) {
        accountRepository.update(model, idRow);
    }
}
