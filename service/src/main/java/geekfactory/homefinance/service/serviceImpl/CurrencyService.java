package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.CurrencyModel;
import geekfactory.homefinance.dao.repository.CurrencyRepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("currencyService")
public class CurrencyService {

    @Autowired
    private CurrencyRepositoryCRUD currencyRepositoryCRUD;

    public Optional<CurrencyModel> findById(Long id) {
        return Optional.ofNullable(currencyRepositoryCRUD.findById(id).get());
    }

    public Optional<CurrencyModel> findByName(String name) {
        return null;
    }

    public Collection<CurrencyModel> findAll() {
        return currencyRepositoryCRUD.findAll();
    }

    public void remove(CurrencyModel model) {
        currencyRepositoryCRUD.remove(model);
    }

    public void save(CurrencyModel model) {
        currencyRepositoryCRUD.save(model);
    }

    public CurrencyModel update(CurrencyModel model) {
        currencyRepositoryCRUD.update(model);
        return model;
    }
}