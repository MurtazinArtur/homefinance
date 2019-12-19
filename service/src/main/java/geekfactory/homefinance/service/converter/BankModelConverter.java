package geekfactory.homefinance.service.converter;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.service.dto.BankDtoModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Transactional
public class BankModelConverter {
    public String conditionConvert;

    public BankDtoModel convertToBankDtoModel(BankModel bankModel) {
        BankDtoModel bankDtoModel = new BankDtoModel();

        if (bankModel != null) {
            if (bankModel.getId() != null) {
                bankDtoModel.setId(Math.toIntExact(bankModel.getId()));
            } else {
                conditionConvert = "Поле id не может быть пустым";
            }
            if (bankModel.getName() != null) {
                bankDtoModel.setName(bankModel.getName());
            } else {
                conditionConvert = "Поле name не может быть пустым";
            }
        } else {
            conditionConvert = "Ошибка конвертации модели";
        }

        return bankDtoModel;
    }

    public Collection<BankDtoModel> convertCollectionToBankDtoModel(Collection<BankModel> all) {
        Collection<BankDtoModel> collectionBankDtoModels = new ArrayList<>();

        for (BankModel bankModel : all) {
            collectionBankDtoModels.add(convertToBankDtoModel(bankModel));
        }

        return collectionBankDtoModels;
    }

    public BankModel convertToBankModel(BankDtoModel bankDtoModel) {
        BankModel bankModel = new BankModel();

        if (bankDtoModel != null) {
            Long id = Long.valueOf(bankDtoModel.getId());
            String name = bankDtoModel.getName();
            if (id != 0) {
                bankModel.setId(id);
            } else {
                conditionConvert = "Поле id не может быть пустым";
            }
            if (name != null) {
                bankModel.setName(name);
            } else {
                conditionConvert = "Поле name не может быть пустым";
            }
        } else {
            conditionConvert = "Ошибка конвертации модели";
        }

        return bankModel;
    }

    public Collection<BankModel> convertCollectionToBankModel(Collection<BankDtoModel> all) {
        Collection<BankModel> collectionBankModels = new ArrayList<>();

        for (BankDtoModel bankDtoModel : all) {
            collectionBankModels.add(convertToBankModel(bankDtoModel));
        }

        return collectionBankModels;
    }
}