package geekfactory.homefinance.service.converter;

import geekfactory.homefinance.dao.model.CurrencyModel;
import geekfactory.homefinance.service.dto.CurrencyDtoModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Transactional
public class CurrencyModelConverter {
    public String conditionConvert;

    public CurrencyDtoModel convertToCurrencyDtoModel(CurrencyModel currencyModel) {
        CurrencyDtoModel currencyDtoModel = new CurrencyDtoModel();

        if (currencyModel != null) {
            if (currencyModel.getId() != null) {
                currencyDtoModel.setId(Math.toIntExact(currencyModel.getId()));
            } else {
                conditionConvert = "Поле id не может быть пустым";
            }
            if (currencyModel.getName() != null) {
                currencyDtoModel.setName(currencyModel.getName());
            } else {
                conditionConvert = "Поле name не может быть пустым";
            }
            if (currencyModel.getCode() != null) {
                currencyDtoModel.setCode(currencyModel.getCode());
            } else {
                conditionConvert = "Поле code не может быть пустым";
            }
            if (currencyModel.getSymbol() != null) {
                currencyDtoModel.setSymbol(currencyModel.getSymbol());
            } else {
                conditionConvert = "Поле symbol не может быть пустым";
            }

        } else {
            conditionConvert = "Ошибка конвертации модели";
        }

        return currencyDtoModel;
    }

    public Collection<CurrencyDtoModel> convertCollectionToCurrencyDtoModel(Collection<CurrencyModel> all) {
        Collection<CurrencyDtoModel> currencyDtoModels = new ArrayList<>();

        for (CurrencyModel currencyModel : all) {
            currencyDtoModels.add(convertToCurrencyDtoModel(currencyModel));
        }

        return currencyDtoModels;
    }

    public CurrencyModel convertToCurrencyModel(CurrencyDtoModel currencyDtoModel) {
        CurrencyModel currencyModel = new CurrencyModel();

        if (currencyDtoModel != null) {
            if (currencyDtoModel.getId() != 0) {
                currencyModel.setId(Long.valueOf(currencyDtoModel.getId()));
            } else {
                conditionConvert = "Поле id не может быть пустым";
            }
            if (currencyDtoModel.getName() != null) {
                currencyModel.setName(currencyDtoModel.getName());
            } else {
                conditionConvert = "Поле name не может быть пустым";
            }
            if (currencyDtoModel.getCode() != null) {
                currencyModel.setCode(currencyDtoModel.getCode());
            } else {
                conditionConvert = "Поле code не может быть пустым";
            }
            if (currencyDtoModel.getSymbol() != null) {
                currencyModel.setSymbol(currencyDtoModel.getSymbol());
            } else {
                conditionConvert = "Поле symbol не может быть пустым";
            }
        } else {
            conditionConvert = "Ошибка конвертации модели";
        }

        return currencyModel;
    }

    public Collection<CurrencyModel> convertCollectionToCurrencyModel(Collection<CurrencyDtoModel> all) {
        Collection<CurrencyModel> currencyModels = new ArrayList<>();

        for (CurrencyDtoModel currencyDtoModel : all) {
            currencyModels.add(convertToCurrencyModel(currencyDtoModel));
        }
        return currencyModels;

    }
}