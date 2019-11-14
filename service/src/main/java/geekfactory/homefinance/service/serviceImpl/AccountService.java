package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.AccountRepositoryCRUD;
import geekfactory.homefinance.dao.repository.TransactionRepositoryCRUD;
import geekfactory.homefinance.service.converter.AccountModelConverter;
import geekfactory.homefinance.service.dto.AccountDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("accountService")
public class AccountService {

    private AccountModelConverter accountConverter;
    private TransactionService transactionService;
    private AccountRepositoryCRUD accountRepositoryCRUD;

    @Autowired
    public AccountService(AccountRepositoryCRUD accountRepositoryCRUD, AccountModelConverter accountConverter,
                          TransactionService transactionService) {
        this.transactionService = transactionService;
        this.accountRepositoryCRUD = accountRepositoryCRUD;
        this.accountConverter = accountConverter;
    }

    public Optional<AccountDtoModel> findById(Long id) {
        return Optional.ofNullable(accountConverter.convertToAccountDtoModel(accountRepositoryCRUD.findById(id).get()));
    }

    public Optional<AccountDtoModel> findByName(String name) {
        return Optional.ofNullable(accountConverter.convertToAccountDtoModel(accountRepositoryCRUD.findByName(name).get()));
    }

    public Collection<AccountDtoModel> findAll() {
        return accountConverter.convertCollectionToAccountDtoModel(accountRepositoryCRUD.findAll());
    }

    public void save(AccountDtoModel accountDtoModel) {

        accountRepositoryCRUD.save(accountConverter.convertToAccountModel(accountDtoModel));
    }

    public AccountDtoModel update(AccountDtoModel model) {
        accountRepositoryCRUD.update(accountConverter.convertToAccountModel(model));
        return model;
    }

    public void remove(AccountDtoModel accountDtoModel) {
        Collection<TransactionModel> transactionModelCollection = transactionService.findAll();

        for (TransactionModel transactionModel : transactionModelCollection) {
            if (accountConverter.convertToAccountModel(accountDtoModel).equals(transactionModel.getAccount())) {
                transactionModel.setAccount(null);
                transactionService.update(transactionModel);
            }
        }
        accountRepositoryCRUD.remove(accountConverter.convertToAccountModel(accountDtoModel));
    }
}