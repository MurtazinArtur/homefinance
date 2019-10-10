package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.repository.RepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("accountService")
public class AccountService implements ServiceCRUD<AccountModel, Long> {

    @Autowired
    private RepositoryCRUD<AccountModel, Long> accountRepositoryCRUD;

    @Override
    public Optional<AccountModel> findById(Long id) {
        return accountRepositoryCRUD.findById(id);
    }

    @Override
    public Collection<AccountModel> findAll() {
        return accountRepositoryCRUD.findAll();
    }

    @Override
    public boolean remove(Long id) {
        accountRepositoryCRUD.remove(id);
        return true;
    }

    @Override
    public void save(AccountModel model) {
        accountRepositoryCRUD.save(model);
    }

    @Override
    public void update(AccountModel model, Long idRow) {
        accountRepositoryCRUD.update(model);
    }
}
