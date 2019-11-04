package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.model.CurrencyModel;
import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.AccountRepositoryCRUD;
import geekfactory.homefinance.dao.repository.CurrencyRepositoryCRUD;
import geekfactory.homefinance.dao.repository.TransactionRepositoryCRUD;
import geekfactory.homefinance.service.converter.CurrencyModelConverter;
import geekfactory.homefinance.service.dto.CurrencyDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("currencyService")
public class CurrencyService {

    private CurrencyModelConverter converter = new CurrencyModelConverter();
    private CurrencyRepositoryCRUD currencyRepositoryCRUD;
    private TransactionRepositoryCRUD transactionRepositoryCRUD;
    private AccountRepositoryCRUD accountRepositoryCRUD;

    @Autowired
    public CurrencyService(CurrencyRepositoryCRUD currencyRepositoryCRUD, TransactionRepositoryCRUD transactionRepositoryCRUD, AccountRepositoryCRUD accountRepositoryCRUD) {
        this.currencyRepositoryCRUD = currencyRepositoryCRUD;
        this.transactionRepositoryCRUD = transactionRepositoryCRUD;
        this.accountRepositoryCRUD = accountRepositoryCRUD;
    }

    public Optional<CurrencyDtoModel> findById(Long id) {
        return Optional.ofNullable(converter.convertToCurrencyDtoModel(currencyRepositoryCRUD.findById(id).get()));
    }

    public Optional<CurrencyDtoModel> findByName(String name) {
        return Optional.empty();
    }

    public Collection<CurrencyDtoModel> findAll() {
        return converter.convertCollectionToCurrencyDtoModel(currencyRepositoryCRUD.findAll());
    }

    public void remove(CurrencyDtoModel currencyDtoModel) {
        Collection<TransactionModel> transactionModelCollection = transactionRepositoryCRUD.findAll();
Collection<AccountModel> accountModelCollection = accountRepositoryCRUD.findAll();

        for (TransactionModel transactionModel : transactionModelCollection) {
            if (converter.convertToCurrencyModel(currencyDtoModel).equals(transactionModel.getCurrency())) {
                transactionModel.setCurrency(null);
                transactionRepositoryCRUD.update(transactionModel);
            }
        }
            for (AccountModel accountModel : accountModelCollection) {
                if (converter.convertToCurrencyModel(currencyDtoModel).equals(accountModel.getCurrencyModel())) {
                    accountModel.setCurrencyModel(null);
                    accountRepositoryCRUD.update(accountModel);
                }
        }
        currencyRepositoryCRUD.remove(converter.convertToCurrencyModel(currencyDtoModel));
    }

    public void save(CurrencyDtoModel currencyDtoModel) {
        currencyRepositoryCRUD.save(converter.convertToCurrencyModel(currencyDtoModel));
    }

    public CurrencyDtoModel update(CurrencyDtoModel currencyDtoModel) {
        currencyRepositoryCRUD.update(converter.convertToCurrencyModel(currencyDtoModel));
        return currencyDtoModel;
    }
}