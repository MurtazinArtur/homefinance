package geekfactory.homefinance.config;

import geekfactory.homefinance.dao.config.DaoConfiguration;
import geekfactory.homefinance.dao.model.*;
import geekfactory.homefinance.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DaoConfiguration.class)
public class ServiceConfiguration {
    @Bean("accountService")
    public ServiceCRUD<AccountModel, Long> accountService() {
        return new AccountService();
    }

    @Bean("bankService")
    public ServiceCRUD<BankModel, Long> bankService() {
        return new BankService();
    }

    @Bean("categoryTransactionService")
    public ServiceCRUD<CategoryTransactionModel, Long> categoryTransactionService() {
        return new CategoryService();
    }

    @Bean("currencyService")
    public ServiceCRUD<CurrencyModel, Long> currencyService() {
        return new CurrencyService();
    }

    @Bean("transactionService")
    public ServiceCRUD<TransactionModel, Long> transactionService() {
        return new TransactionService();
    }
}
