package geekfactory.homefinance.service.converter;


import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.service.dto.CategoryDtoModel;
import geekfactory.homefinance.service.serviceImpl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Transactional
public class CategoryModelConverter {
    private final CategoryService categoryService;
    public String conditionConvert;

    @Autowired
    public CategoryModelConverter(@Lazy CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CategoryDtoModel convertToCategoryDtoModel(CategoryTransactionModel categoryTransactionModel) {
        CategoryDtoModel categoryDtoModel = new CategoryDtoModel();

        if (categoryTransactionModel != null) {
            if (categoryTransactionModel.getId() != null) {
                categoryDtoModel.setId(Math.toIntExact(categoryTransactionModel.getId()));
            } else {
                conditionConvert = "Поле id не может быть пустым";
            }
            if (categoryTransactionModel.getName() != null) {
                categoryDtoModel.setName(categoryTransactionModel.getName());
            } else {
                conditionConvert = "Поле name не может быть пустым";
            }
            if (categoryTransactionModel.getParentCategory() != null) {
                categoryDtoModel.setParentCategory(String.valueOf(categoryTransactionModel.getParentCategory().getName()));
            } else {
                categoryDtoModel.setParentCategory(null);
            }
        } else {
            conditionConvert = "Ошибка конвертации модели";
        }
        return categoryDtoModel;
    }

    public Collection<CategoryDtoModel> convertCollectionToCategoryDtoModel(Collection<CategoryTransactionModel> all) {
        Collection<CategoryDtoModel> categoryDtoModels = new ArrayList<>();
        for (CategoryTransactionModel categoryTransactionModel : all) {
            categoryDtoModels.add(convertToCategoryDtoModel(categoryTransactionModel));
        }
        return categoryDtoModels;
    }

    public CategoryTransactionModel convertToCategoryModel(CategoryDtoModel categoryDtoModel) {
        CategoryTransactionModel categoryTransactionModel = new CategoryTransactionModel();
        if (categoryDtoModel != null) {
            if (categoryDtoModel.getId() != 0) {
                categoryTransactionModel.setId(Long.valueOf(categoryDtoModel.getId()));
            } else {
                conditionConvert = "Поле id не может быть пустым";
            }
            if (categoryDtoModel.getName() != null) {
                categoryTransactionModel.setName(categoryDtoModel.getName());
            } else {
                conditionConvert = "Поле name не может быть пустым";
            }
            if ("0".equals(categoryDtoModel.getParentCategory()) || categoryDtoModel.getParentCategory() == null) {
                categoryTransactionModel.setParentCategory(null);
            } else {
                categoryTransactionModel.setParentCategory(convertToCategoryModel(categoryService.findByName(
                        categoryDtoModel.getParentCategory()).get()));
            }
        } else {
            conditionConvert = "Ошибка конвертации модели";
        }
        return categoryTransactionModel;
    }

    public Collection<CategoryTransactionModel> convertCollectionToCategoryModel(Collection<CategoryDtoModel> all) {
        Collection<CategoryTransactionModel> categoryTransactionModels = new ArrayList<>();
        for (CategoryDtoModel categoryDtoModel : all) {
            categoryTransactionModels.add(convertToCategoryModel(categoryDtoModel));
        }
        return categoryTransactionModels;
    }
}