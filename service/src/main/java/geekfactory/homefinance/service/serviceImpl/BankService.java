package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.dao.repository.BankRepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service("bankService")
public class BankService {

    @Autowired
    private BankRepositoryCRUD bankRepositoryCRUD;

    @Transactional
    public Optional<BankModel> findById(Long id) {
        return Optional.ofNullable(bankRepositoryCRUD.findById(id).get());
    }

    public Optional<BankModel> findByName(String name) {
        return Optional.empty();
    }

    @Transactional
    public Collection<BankModel> findAll() {
        return bankRepositoryCRUD.findAll();
    }

    @Transactional()
    public void remove(BankModel model) {
        bankRepositoryCRUD.remove(model);
    }

    @Transactional
    public void save(BankModel model) {
        bankRepositoryCRUD.save(model);
    }

    @Transactional
    public BankModel update(BankModel model) {
        bankRepositoryCRUD.update(model);
        return model;
    }
}