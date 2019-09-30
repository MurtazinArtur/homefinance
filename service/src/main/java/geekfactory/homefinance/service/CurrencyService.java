package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.CurrencyModel;
import geekfactory.homefinance.dao.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
public class CurrencyService implements ServiceCRUD<CurrencyModel, Long> {

    @Autowired
    private Repository<CurrencyModel, Long> currencyRepository;

    @Override
    public Optional<CurrencyModel> findById(Long id) {
        return currencyRepository.findById(id);
    }

    @Override
    public Collection<CurrencyModel> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        currencyRepository.remove(id);
        return true;
    }

    @Override
    public void save(CurrencyModel model) {
        currencyRepository.save(model);
    }

    @Override
    public void update(CurrencyModel model, Long idRow) {
        currencyRepository.update(model, idRow);
    }
}
