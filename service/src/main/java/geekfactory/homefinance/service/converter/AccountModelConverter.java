package geekfactory.homefinance.service.converter;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.model.AccountType;
import geekfactory.homefinance.service.dto.AccountDtoModel;
import geekfactory.homefinance.service.dto.CurrencyDtoModel;
import geekfactory.homefinance.service.serviceImpl.CurrencyService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Component
@Transactional
public class AccountModelConverter {

    public String conditionConvert;
    private final CurrencyModelConverter converter;
    private final CurrencyService currencyService;

    @Autowired
    public AccountModelConverter(@Lazy CurrencyModelConverter converter, @Lazy CurrencyService currencyService) {
        this.converter = converter;
        this.currencyService = currencyService;
    }

    public AccountDtoModel convertToAccountDtoModel(AccountModel accountModel) {
        AccountDtoModel accountDtoModel = new AccountDtoModel();

        if (accountModel != null) {
            if (accountModel.getId() != null) {
                accountDtoModel.setId(Math.toIntExact(accountModel.getId()));
            } else {
                conditionConvert = "Поле id не может быть пустым";
            }
            if (accountModel.getName() != null) {
                accountDtoModel.setName(accountModel.getName());
            } else {
                conditionConvert = "Поле name не может быть пустым";
            }
            if (accountModel.getAmount() != null) {
                accountDtoModel.setAmount(String.valueOf(accountModel.getAmount()));
            } else {
                conditionConvert = "Поле amount не может быть пустым";
            }
            if (accountModel.getCurrencyModel() != null) {
                accountDtoModel.setCurrencyModel(accountModel.getCurrencyModel().getName());
            } else {
                conditionConvert = "Поле currencyModel не может быть пустым";
            }
            if (accountModel.getAccountType() != null) {
                accountDtoModel.setAccountType(String.valueOf(accountModel.getAccountType()));
            } else {
                conditionConvert = "Поле accountType не может быть пустым";
            }
        } else {
            conditionConvert = "Ошибка конвертации модели";
        }
        return accountDtoModel;
    }

    public Collection<AccountDtoModel> convertCollectionToAccountDtoModel(Collection<AccountModel> all) {
        Collection<AccountDtoModel> accountDtoModels = new ArrayList<>();
        for (AccountModel accountModel : all) {
            accountDtoModels.add(convertToAccountDtoModel(accountModel));
        }
        return accountDtoModels;
    }

    public AccountModel convertToAccountModel(AccountDtoModel accountDtoModel) {
        AccountModel accountModel = new AccountModel();

        if (accountDtoModel != null) {
            if (accountDtoModel.getId() != 0) {
                accountModel.setId(Long.valueOf(accountDtoModel.getId()));
            } else {
                conditionConvert = "Поле id не может быть пустым";
            }
            if (accountDtoModel.getName() != null) {
                accountModel.setName(accountDtoModel.getName());
            } else {
                conditionConvert = "Поле name не может быть пустым";
            }
            if (accountDtoModel.getAmount() != null) {
                accountModel.setAmount(new BigDecimal(accountDtoModel.getAmount()));
            } else {
                conditionConvert = "Поле amount не может быть пустым";
            }
            if (accountDtoModel.getCurrencyModel() != null) {
                CurrencyDtoModel currencyDtoModel;
                currencyDtoModel = currencyService.findByName(accountDtoModel.getCurrencyModel()).get();
                accountModel.setCurrencyModel(converter.convertToCurrencyModel(currencyDtoModel));
            } else {
                conditionConvert = "Поле accountModel не может быть пустым";
            }
            if (accountDtoModel.getAccountType() != null) {
                accountModel.setAccountType(AccountType.valueOf(accountDtoModel.getAccountType()));
            } else {
                conditionConvert = "Поле accountType не может быть пустым";
            }
        } else {
            conditionConvert = "Ошибка конвертации модели";
        }
        return accountModel;
    }

    public Collection<AccountModel> convertCollectionToCurrencyModel(Collection<AccountDtoModel> all) {
        Collection<AccountModel> accountModels = new ArrayList<>();
        for (AccountDtoModel accountDtoModel : all) {
            accountModels.add(convertToAccountModel(accountDtoModel));
        }
        return accountModels;
    }
}