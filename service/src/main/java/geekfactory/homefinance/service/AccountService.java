package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.repository.AccountRepository;

import java.util.Collection;
import java.util.Optional;

public class AccountService implements Service<AccountModel> {
    AccountRepository accountRepository = new AccountRepository();

    @Override
    public Optional<AccountModel> findById(long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Collection<AccountModel> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean remove(long id) {
        accountRepository.remove(id);
        return true;
    }

    @Override
    public void save(AccountModel model) {
        accountRepository.save(model);
    }

    @Override
    public void update(AccountModel model, long idRow) {
        accountRepository.update(model, idRow);
    }
}
