package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.AccountRepositoryCRUD;
import geekfactory.homefinance.service.converter.AccountModelConverter;
import geekfactory.homefinance.service.converter.TransactionModelConverter;
import geekfactory.homefinance.service.converter.UserModelConverter;
import geekfactory.homefinance.service.dto.AccountDtoModel;
import geekfactory.homefinance.service.dto.UserDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("accountService")
public class AccountService {

    private AccountModelConverter accountConverter;
    private TransactionModelConverter transactionModelConverter;
    private TransactionService transactionService;
    private AccountRepositoryCRUD accountRepositoryCRUD;

    @Autowired
    public AccountService(AccountRepositoryCRUD accountRepositoryCRUD, AccountModelConverter accountConverter,
                          TransactionModelConverter transactionModelConverter, TransactionService transactionService){
        this.transactionModelConverter = transactionModelConverter;
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
                transactionService.update(transactionModelConverter.convertToTransactionDtoModel(transactionModel));
            }
        }
        accountRepositoryCRUD.remove(accountConverter.convertToAccountModel(accountDtoModel));
    }
}