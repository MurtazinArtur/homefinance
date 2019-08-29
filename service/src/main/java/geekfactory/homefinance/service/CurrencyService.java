package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.CurrencyModel;
import geekfactory.homefinance.dao.repository.CurrencyRepository;

import java.util.Collection;
import java.util.Optional;

public class CurrencyService implements Service<CurrencyModel> {
    private CurrencyRepository currencyRepository = new CurrencyRepository();

    public CurrencyRepository getCurrencyRepository() {
        return currencyRepository;
    }

    public void setCurrencyRepository(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Optional<CurrencyModel> findById(long id) {
        return currencyRepository.findById(id);
    }

    @Override
    public Collection<CurrencyModel> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public boolean remove(long id) {
        currencyRepository.remove(id);
        return true;
    }

    @Override
    public void save(CurrencyModel model) {
        currencyRepository.save(model);
    }

    @Override
    public void update(CurrencyModel model, long idRow) {
        currencyRepository.update(model, idRow);
    }
}
