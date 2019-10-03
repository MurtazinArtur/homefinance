package geekfactory.homefinance.dao.config;

import geekfactory.homefinance.dao.model.*;
import geekfactory.homefinance.dao.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:dbConnectionProperties.properties")
public class DaoConfiguration {

    @Value("${dbdriver}")
    private String driver;

    @Value("${dburl}")
    private String url;

    @Value("${dbuser}")
    private String username;

    @Value("${dbpassword}")
    private String password;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean("accountRepository")
    public Repository<AccountModel, Long> accountModelRepository(){return new AccountRepository(); }

    @Bean("bankRepository")
    public Repository<BankModel, Long> bankModelRepository(){return new BankRepository(); }

    @Bean("categoryTransactionRepository")
    public Repository<CategoryTransactionModel, Long> categoryTransactionModelRepository(){return new CategoryTransactionRepository(); }

    @Bean("currencyRepository")
    public Repository<CurrencyModel, Long> currencyModelRepository(){return new CurrencyRepository(); }

    @Bean("transactionRepository")
    public Repository<TransactionModel, Long> transactionModelRepository(){return new TransactionRepository(); }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        if(dataSource() != null) {
            return new DataSourceTransactionManager(dataSource());
        }else {
            System.out.println("Error Connection on Database");
        }
        return null;
    }
}