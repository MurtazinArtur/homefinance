package geekfactory.homefinance.service.converter;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.service.dto.AccountDtoModel;
import geekfactory.homefinance.service.dto.BankDtoModel;
import geekfactory.homefinance.service.dto.CurrencyDtoModel;
import geekfactory.homefinance.service.dto.TransactionDtoModel;
import geekfactory.homefinance.service.serviceImpl.AccountService;
import geekfactory.homefinance.service.serviceImpl.BankService;
import geekfactory.homefinance.service.serviceImpl.CategoryService;
import geekfactory.homefinance.service.serviceImpl.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Component
@Transactional
public class TransactionModelConverter {
    private final CurrencyService currencyService;
    private final AccountService accountService;
    private final BankService bankService;
    private final CategoryService categoryService;
    private final CurrencyModelConverter currencyModelConverter;
    private final CategoryModelConverter categoryModelConverter;
    private final AccountModelConverter accountModelConverter;
    private final BankModelConverter bankModelConverter;
    public String conditionConvert;

    @Autowired
    public TransactionModelConverter(@Lazy CurrencyService currencyService, @Lazy AccountService accountService,
                                     @Lazy BankService bankService, @Lazy CategoryService categoryService,
                                     @Lazy CurrencyModelConverter currencyModelConverter,
                                     @Lazy CategoryModelConverter categoryModelConverter,
                                     @Lazy AccountModelConverter accountModelConverter,
                                     @Lazy BankModelConverter bankModelConverter) {
        this.currencyService = currencyService;
        this.accountService = accountService;
        this.bankService = bankService;
        this.categoryService = categoryService;
        this.currencyModelConverter = currencyModelConverter;
        this.categoryModelConverter = categoryModelConverter;
        this.accountModelConverter = accountModelConverter;
        this.bankModelConverter = bankModelConverter;
    }

    public TransactionDtoModel convertToTransactionDtoModel(TransactionModel transactionModel) {
        TransactionDtoModel transactionDtoModel = new TransactionDtoModel();

        if (transactionModel != null) {
            if (transactionModel.getId() != null) {
                transactionDtoModel.setId(Math.toIntExact(transactionModel.getId()));
            } else {
                conditionConvert = "Поле id не может быть пустым";
            }
            if (transactionModel.getAmount() != null) {
                transactionDtoModel.setAmount(String.valueOf(transactionModel.getAmount()));
            } else {
                conditionConvert = "Поле amount не может быть пустым";
            }
            if (transactionModel.getDate() != null) {
                transactionDtoModel.setDate(String.valueOf(transactionModel.getDate()));
            } else {
                conditionConvert = "Поле date не может быть пустым";
            }
            if (transactionModel.getSource() != null) {
                transactionDtoModel.setSource(transactionModel.getSource());
            } else {
                conditionConvert = "Поле source не может быть пустым";
            }
            if (transactionModel.getCategory() != null) {
                transactionDtoModel.setCategory(Collections.singleton(transactionModel.getCategory().iterator().next().getName()));
            } else {
                conditionConvert = "Поле category не может быть пустым";
            }
            if (transactionModel.getBank() != null) {
                transactionDtoModel.setBank(transactionModel.getBank().getName());
            } else {
                conditionConvert = "Поле bank не может быть пустым";
            }
            if (transactionModel.getAccount() != null) {
                transactionDtoModel.setAccount(transactionModel.getAccount().getName());
            } else {
                conditionConvert = "Поле account не может быть пустым";
            }
            if (transactionModel.getCurrency() != null) {
                transactionDtoModel.setCurrency(transactionModel.getCurrency().getName());
            } else {
                conditionConvert = "Поле currency не может быть пустым";
            }
        } else {
            conditionConvert = "Ошибка конвертации модели";
        }

        return transactionDtoModel;
    }

    public Collection<TransactionDtoModel> convertCollectionToTransactionDtoModel(Collection<TransactionModel> all) {
        Collection<TransactionDtoModel> transactionDtoModels = new ArrayList<>();

        for (TransactionModel transactionModel : all) {
            transactionDtoModels.add(convertToTransactionDtoModel(transactionModel));
        }

        return transactionDtoModels;
    }

    public TransactionModel convertToTransactionModel(TransactionDtoModel transactionDtoModel) {
        TransactionModel transactionModel = new TransactionModel();

        if (transactionDtoModel != null) {
            if (transactionDtoModel.getId() != 0) {
                transactionModel.setId(Long.valueOf(transactionDtoModel.getId()));
            } else {
                conditionConvert = "Поле id не может быть пустым";
            }
            if (transactionDtoModel.getAmount() != null) {
                transactionModel.setAmount(new BigDecimal(transactionDtoModel.getAmount()));
            } else {
                conditionConvert = "Поле amount не может быть пустым";
            }
            if (transactionDtoModel.getDate() != null) {
                transactionModel.setDate(LocalDate.parse(transactionDtoModel.getDate()));
            } else {
                conditionConvert = "Поле date не может быть пустым";
            }
            if (transactionDtoModel.getSource() != null) {
                transactionModel.setSource(transactionDtoModel.getSource());
            } else {
                conditionConvert = "Поле source не может быть пустым";
            }
            if (transactionDtoModel.getCategory() != null) {
                Collection<String> collectionDtoCategories = transactionDtoModel.getCategory();
                Collection<CategoryTransactionModel> collectionCategoryModels = new ArrayList<>();

                for (String categoryModelName : collectionDtoCategories) {
                    collectionCategoryModels.add(categoryModelConverter.convertToCategoryModel(
                            categoryService.findByName(categoryModelName).get()));
                }

                transactionModel.setCategory(collectionCategoryModels);
            } else {
                conditionConvert = "Поле category не может быть пустым";
            }
            if (transactionDtoModel.getBank() != null) {
                BankDtoModel bankDtoModel = bankService.findByName(transactionDtoModel.getBank()).get();
                transactionModel.setBank(bankModelConverter.convertToBankModel(bankDtoModel));
            } else {
                conditionConvert = "Поле bank не может быть пустым";
            }
            if (transactionDtoModel.getAccount() != null) {
                AccountDtoModel accountDtoModel = accountService.findByName(transactionDtoModel.getAccount()).get();
                transactionModel.setAccount(accountModelConverter.convertToAccountModel(accountDtoModel));
            } else {
                conditionConvert = "Поле account не может быть пустым";
            }
            if (transactionDtoModel.getCurrency() != null) {
                CurrencyDtoModel currencyDtoModel = currencyService.findByName(transactionDtoModel.getCurrency()).get();
                transactionModel.setCurrency(currencyModelConverter.convertToCurrencyModel(currencyDtoModel));
            } else {
                conditionConvert = "Поле accountModel не может быть пустым";
            }
        } else {
            conditionConvert = "Ошибка конвертации модели";
        }

        return transactionModel;
    }

    public Collection<TransactionModel> convertCollectionToTransactionModel(Collection<TransactionDtoModel> all) {
        Collection<TransactionModel> transactionModels = new ArrayList<>();

        for (TransactionDtoModel transactionDtoModel : all) {
            transactionModels.add(convertToTransactionModel(transactionDtoModel));
        }

        return transactionModels;
    }
}