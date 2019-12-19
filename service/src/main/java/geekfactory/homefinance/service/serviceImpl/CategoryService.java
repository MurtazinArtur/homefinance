package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.CategoryTransactionRepositoryCRUD;
import geekfactory.homefinance.service.converter.CategoryModelConverter;
import geekfactory.homefinance.service.converter.TransactionModelConverter;
import geekfactory.homefinance.service.dto.CategoryDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("categoryService")
public class CategoryService {
    private CategoryModelConverter categoryModelConverter;
    private CategoryTransactionRepositoryCRUD categoryTransactionRepositoryCRUD;
    private TransactionService transactionService;
    private TransactionModelConverter transactionModelConverter;

    @Autowired
    public CategoryService(CategoryModelConverter categoryModelConverter, CategoryTransactionRepositoryCRUD
            categoryTransactionRepositoryCRUD, TransactionService transactionService,
                           TransactionModelConverter transactionModelConverter) {
        this.categoryModelConverter = categoryModelConverter;
        this.categoryTransactionRepositoryCRUD = categoryTransactionRepositoryCRUD;
        this.transactionService = transactionService;
        this.transactionModelConverter = transactionModelConverter;
    }

    public Optional<CategoryDtoModel> findById(Long id) {
        return Optional.ofNullable(categoryModelConverter.convertToCategoryDtoModel(categoryTransactionRepositoryCRUD.findById(id).get()));
    }

    public Optional<CategoryDtoModel> findByName(String name) {

        return Optional.ofNullable(categoryModelConverter.convertToCategoryDtoModel(
                categoryTransactionRepositoryCRUD.findByName(name).get()));
    }

    public Collection<CategoryDtoModel> findAll() {

        return categoryModelConverter.convertCollectionToCategoryDtoModel(categoryTransactionRepositoryCRUD.findAll());
    }

    public void remove(CategoryDtoModel categoryDtoModel) {
        Collection<TransactionModel> transactionModelCollection = transactionService.findAll();
        Collection<CategoryTransactionModel> categoryTransactionModelCollection = categoryTransactionRepositoryCRUD.findAll();

        for (TransactionModel transactionModel : transactionModelCollection) {
            if (categoryModelConverter.convertToCategoryModel(categoryDtoModel).equals(transactionModel.getCategory())) {
                transactionModel.setCategory(null);
                transactionService.update(transactionModelConverter.convertToTransactionDtoModel(transactionModel));
            }
        }
        for (CategoryTransactionModel categoryTransactionModel : categoryTransactionModelCollection) {
            if (categoryModelConverter.convertToCategoryModel(categoryDtoModel).equals(categoryTransactionModel.getParentCategory())) {
                categoryTransactionModel.setParentCategory(null);
            }
        }

        categoryTransactionRepositoryCRUD.remove(categoryModelConverter.convertToCategoryModel(categoryDtoModel));
    }

    public void save(CategoryDtoModel categoryDtoModel) {
        categoryTransactionRepositoryCRUD.save(categoryModelConverter.convertToCategoryModel(categoryDtoModel));
    }

    public CategoryDtoModel update(CategoryDtoModel categoryDtoModel) {
        categoryTransactionRepositoryCRUD.update(categoryModelConverter.convertToCategoryModel(categoryDtoModel));

        return categoryDtoModel;
    }
}