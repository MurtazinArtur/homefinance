package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.CurrencyRepositoryCRUD;
import geekfactory.homefinance.service.converter.CurrencyModelConverter;
import geekfactory.homefinance.service.converter.TransactionModelConverter;
import geekfactory.homefinance.service.dto.AccountDtoModel;
import geekfactory.homefinance.service.dto.CurrencyDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("currencyService")
public class CurrencyService {

    private CurrencyModelConverter currencyConverter;
    private TransactionModelConverter transactionModelConverter;
    private CurrencyRepositoryCRUD currencyRepositoryCRUD;
    private TransactionService transactionService;
    private AccountService accountService;

    @Autowired
    public CurrencyService(CurrencyModelConverter converter, TransactionModelConverter transactionModelConverter,
                           CurrencyRepositoryCRUD currencyRepositoryCRUD, TransactionService transactionService,
                           AccountService accountService) {
        this.currencyConverter = converter;
        this.transactionModelConverter = transactionModelConverter;
        this.currencyRepositoryCRUD = currencyRepositoryCRUD;
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    public Optional<CurrencyDtoModel> findById(Long id) {
        return Optional.ofNullable(currencyConverter.convertToCurrencyDtoModel(currencyRepositoryCRUD.findById(id).get()));
    }

    public Optional<CurrencyDtoModel> findByName(String name) {
        return Optional.ofNullable(currencyConverter.convertToCurrencyDtoModel(currencyRepositoryCRUD.findByName(name).get()));
    }

    public Collection<CurrencyDtoModel> findAll() {
        return currencyConverter.convertCollectionToCurrencyDtoModel(currencyRepositoryCRUD.findAll());
    }

    public void remove(CurrencyDtoModel currencyDtoModel) {
        Collection<TransactionModel> transactionModelCollection = transactionService.findAll();
        Collection<AccountDtoModel> accountDtoModelCollection = accountService.findAll();

        for (TransactionModel transactionModel : transactionModelCollection) {
            if (currencyConverter.convertToCurrencyModel(currencyDtoModel).equals(transactionModel.getCurrency())) {
                transactionModel.setCurrency(null);
                transactionService.update(transactionModelConverter.convertToTransactionDtoModel(transactionModel));
            }
        }
        for (AccountDtoModel accountDtoModel : accountDtoModelCollection) {
            if (currencyConverter.convertToCurrencyModel(currencyDtoModel).equals(accountDtoModel.getCurrencyModel())) {
                accountDtoModel.setCurrencyModel(null);
                accountService.update(accountDtoModel);
            }
        }

        currencyRepositoryCRUD.remove(currencyConverter.convertToCurrencyModel(currencyDtoModel));
    }

    public void save(CurrencyDtoModel currencyDtoModel) {
        currencyRepositoryCRUD.save(currencyConverter.convertToCurrencyModel(currencyDtoModel));
    }

    public CurrencyDtoModel update(CurrencyDtoModel currencyDtoModel) {
        currencyRepositoryCRUD.update(currencyConverter.convertToCurrencyModel(currencyDtoModel));

        return currencyDtoModel;
    }
}