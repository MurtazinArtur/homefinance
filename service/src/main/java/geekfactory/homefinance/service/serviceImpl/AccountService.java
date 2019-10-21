package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.repository.AccountRepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("accountService")
public class AccountService {

    @Autowired
    private AccountRepositoryCRUD accountRepositoryCRUD;

    public Optional<AccountModel> findById(Long id) {
        return Optional.ofNullable(accountRepositoryCRUD.findById(id).get());
    }

    public Optional<AccountModel> findByName(String name) {
        return Optional.empty();
    }

    public Collection<AccountModel> findAll() {
        return accountRepositoryCRUD.findAll();
    }

    public void remove(AccountModel model) {
        accountRepositoryCRUD.remove(model);
    }

    public void save(AccountModel model) {
        accountRepositoryCRUD.save(model);
    }

    public AccountModel update(AccountModel model) {
        accountRepositoryCRUD.update(model);
        return model;
    }
}