package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.CurrencyModel;
import geekfactory.homefinance.dao.repository.RepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("currencyService")
public class CurrencyService implements ServiceCRUD<CurrencyModel, Long> {

    @Autowired
    private RepositoryCRUD<CurrencyModel, Long> currencyRepositoryCRUD;

    @Override
    public Optional<CurrencyModel> findById(Long id) {
        return currencyRepositoryCRUD.findById(id);
    }

    @Override
    public Collection<CurrencyModel> findAll() {
        return currencyRepositoryCRUD.findAll();
    }

    @Override
    public boolean remove(Long id) {
        currencyRepositoryCRUD.remove(id);
        return true;
    }

    @Override
    public void save(CurrencyModel model) {
        currencyRepositoryCRUD.save(model);
    }

    @Override
    public void update(CurrencyModel model, Long idRow) {
        currencyRepositoryCRUD.update(model, idRow);
    }
}
