import geekfactory.homefinance.dao.model.*;
import geekfactory.homefinance.dao.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        createTransaction();
    }
    private static void createTransaction(){

        BankRepository bankRepository = new BankRepository();
        Optional<BankModel> bankModel = bankRepository.findById(1L);
        AccountRepository accountRepository = new AccountRepository();
        Optional<AccountModel> accountModel = accountRepository.findById(1L);
        TransactionRepository transactionRepository= new TransactionRepository();
        TransactionModel transactionModel = new TransactionModel();
        CurrencyRepository currencyRepository = new CurrencyRepository();
        Optional<CurrencyModel> currencyModel = currencyRepository.findById(1L);
        transactionModel.setAmount(BigDecimal.valueOf(35.18));
        transactionModel.setDate(LocalDate.now());
        transactionModel.setSource("Testing Record");
        transactionModel.setBank(bankModel.get());
        transactionModel.setAccount(accountModel.get());
        transactionModel.setCurrency(currencyModel.get());
        CategoryTransactionRepository categoryTransactionRepository = new CategoryTransactionRepository();
        Optional<CategoryTransactionModel> categoryTransactionModel = categoryTransactionRepository.findById(3L);
        Collection<CategoryTransactionModel> categoryTransactionModels= new HashSet<>();
        categoryTransactionModels.add(categoryTransactionModel.get());
        transactionModel.setCategory(categoryTransactionModels);
        transactionRepository.save(transactionModel);
    }
}
