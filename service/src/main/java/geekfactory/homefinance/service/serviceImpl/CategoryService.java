package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.CategoryTransactionRepositoryCRUD;
import geekfactory.homefinance.service.converter.CategoryModelConverter;
import geekfactory.homefinance.service.dto.CategoryDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("categoryService")
public class CategoryService {
    private CategoryModelConverter converter;
    private CategoryTransactionRepositoryCRUD categoryTransactionRepositoryCRUD;
    private TransactionService transactionService;

    @Autowired
    public CategoryService(CategoryModelConverter converter, CategoryTransactionRepositoryCRUD
            categoryTransactionRepositoryCRUD, TransactionService transactionService) {
        this.converter = converter;
        this.categoryTransactionRepositoryCRUD = categoryTransactionRepositoryCRUD;
        this.transactionService = transactionService;
    }

    public Optional<CategoryDtoModel> findById(Long id) {
        return Optional.ofNullable(converter.convertToCategoryDtoModel(categoryTransactionRepositoryCRUD.findById(id).get()));
    }

    public Optional<CategoryDtoModel> findByName(String name) {

        return Optional.ofNullable(converter.convertToCategoryDtoModel(
                categoryTransactionRepositoryCRUD.findByName(name).get()));
    }

    public Collection<CategoryDtoModel> findAll() {

        return converter.convertCollectionToCategoryDtoModel(categoryTransactionRepositoryCRUD.findAll());
    }

    public void remove(CategoryDtoModel categoryDtoModel) {
        Collection<TransactionModel> transactionModelCollection = transactionService.findAll();
        Collection<CategoryTransactionModel> categoryTransactionModelCollection = categoryTransactionRepositoryCRUD.findAll();

        for (TransactionModel transactionModel : transactionModelCollection) {
            if (converter.convertToCategoryModel(categoryDtoModel).equals(transactionModel.getCategory())) {
                transactionModel.setCategory(null);
                transactionService.update(transactionModel);
            }
        }
        for (CategoryTransactionModel categoryTransactionModel : categoryTransactionModelCollection) {
            if (converter.convertToCategoryModel(categoryDtoModel).equals(categoryTransactionModel.getParentCategory())) {
                categoryTransactionModel.setParentCategory(null);
            }
        }
        categoryTransactionRepositoryCRUD.remove(converter.convertToCategoryModel(categoryDtoModel));
    }

    public void save(CategoryDtoModel categoryDtoModel) {

        categoryTransactionRepositoryCRUD.save(converter.convertToCategoryModel(categoryDtoModel));
    }

    public CategoryDtoModel update(CategoryDtoModel categoryDtoModel) {
        categoryTransactionRepositoryCRUD.update(converter.convertToCategoryModel(categoryDtoModel));
        return categoryDtoModel;
    }
}