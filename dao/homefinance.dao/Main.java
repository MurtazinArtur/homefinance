import geekfactory.homefinance.dao.model.*;
import geekfactory.homefinance.dao.repository.*;

import java.math.BigDecimal;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        TransactionTypeRepository transactionRepository = new TransactionTypeRepository();
        CategoryTransactionModel model = new CategoryTransactionModel();
        CategoryTransactionRepository categoryTransactionRepository = new CategoryTransactionRepository();
        model.setName("Лейкопластырь");
        model.setParentCategory(null);
       // System.out.println(categoryTransactionRepository.findAll());
        categoryTransactionRepository.update(model,4);
       // categoryTransactionRepository.update(model,3);
       // transactionRepository.update(model,1);
    }

    public static void getCurrencyUpdate() {
        CurrencyRepository currencyRepository = new CurrencyRepository();
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setName("RUBL'");
        currencyModel.setCode("RUB");
        currencyModel.setSymbol("R");
        currencyRepository.update(currencyModel, 3);
    }

    public static void getAccountsCommands() {
        AccountRepository accountRepository = new AccountRepository();
        CurrencyRepository currencyRepository = new CurrencyRepository();
        AccountModel accountModel = new AccountModel();
        accountModel.setName("Бензин");
        accountModel.setAmount(BigDecimal.valueOf(40.15));
        Optional<CurrencyModel> currencyModel = currencyRepository.findById(3);
        accountModel.setCurrencyModel(currencyModel.get());
        accountModel.setAccountType(AccountType.CASH);
        //accountRepository.update(accountModel, 2);
        System.out.println(accountRepository.findAll());
        //System.out.println(accountRepository.findById(2));
        //getCurrencyUpdate();
    }
}